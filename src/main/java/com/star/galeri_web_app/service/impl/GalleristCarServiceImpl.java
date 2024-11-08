package com.star.galeri_web_app.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.galeri_web_app.dto.DtoAddress;
import com.star.galeri_web_app.dto.DtoCar;
import com.star.galeri_web_app.dto.DtoGallerist;
import com.star.galeri_web_app.dto.DtoGalleristCar;
import com.star.galeri_web_app.dto.DtoGalleristCarIU;
import com.star.galeri_web_app.exception.BaseException;
import com.star.galeri_web_app.exception.ErrorMessage;
import com.star.galeri_web_app.exception.MessageType;
import com.star.galeri_web_app.model.Car;
import com.star.galeri_web_app.model.Gallerist;
import com.star.galeri_web_app.model.GalleristCar;
import com.star.galeri_web_app.repository.CarRepository;
import com.star.galeri_web_app.repository.GalleristCarRepository;
import com.star.galeri_web_app.repository.GalleristRepository;
import com.star.galeri_web_app.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar creatGealleristCar(DtoGalleristCarIU dtoGalleristCarIU) {

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());

        if (optGallerist.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());

        if (optCar.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoCar dtoCar = new DtoCar();

        DtoAddress dtoAddress = new DtoAddress();
        DtoGallerist dtoGallerist = new DtoGallerist();

        GalleristCar savedGalleristCar = galleristCarRepository.save(creatGealleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }
}
