package com.star.galeri_web_app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.galeri_web_app.dto.CurrencyRatesResponse;
import com.star.galeri_web_app.dto.DtoCar;
import com.star.galeri_web_app.dto.DtoCustomer;
import com.star.galeri_web_app.dto.DtoGallerist;
import com.star.galeri_web_app.dto.DtoSaledCar;
import com.star.galeri_web_app.dto.DtoSaledCarIU;
import com.star.galeri_web_app.enums.CarStatusType;
import com.star.galeri_web_app.exception.BaseException;
import com.star.galeri_web_app.exception.ErrorMessage;
import com.star.galeri_web_app.exception.MessageType;
import com.star.galeri_web_app.model.Car;
import com.star.galeri_web_app.model.Customer;
import com.star.galeri_web_app.model.SaledCar;
import com.star.galeri_web_app.repository.CarRepository;
import com.star.galeri_web_app.repository.CustomerRepository;
import com.star.galeri_web_app.repository.GalleristRepository;
import com.star.galeri_web_app.repository.SaledCarRepository;
import com.star.galeri_web_app.service.ICurrencyRatesService;
import com.star.galeri_web_app.service.ISaledCarService;
import com.star.galeri_web_app.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRatesResponse(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;

    }

    public boolean checkCarStatus(Long carId) {
        Optional<Car> optCar = carRepository.findById(carId);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {

            return false;

        }
        return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRatesResponse(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        return remainingCustomerUSDAmount.multiply(usd);

    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
        // 0 eşitse 1 fazlaysa -1 azsa
        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
                || customerUSDAmount.compareTo(optCar.get().getPrice()) == 1) {

            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(
                    new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
        }
        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);
        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);

    }

    public DtoSaledCar toDTO(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);

        return dtoSaledCar;

    }

}
