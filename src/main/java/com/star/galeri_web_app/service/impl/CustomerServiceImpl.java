package com.star.galeri_web_app.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.galeri_web_app.dto.DtoAccount;
import com.star.galeri_web_app.dto.DtoAddress;
import com.star.galeri_web_app.dto.DtoCustomer;
import com.star.galeri_web_app.dto.DtoCustomerIU;
import com.star.galeri_web_app.exception.BaseException;
import com.star.galeri_web_app.exception.ErrorMessage;
import com.star.galeri_web_app.exception.MessageType;
import com.star.galeri_web_app.model.Account;
import com.star.galeri_web_app.model.Address;
import com.star.galeri_web_app.model.Customer;
import com.star.galeri_web_app.repository.AccountRepository;
import com.star.galeri_web_app.repository.AddressRepository;
import com.star.galeri_web_app.repository.CustomerRepository;
import com.star.galeri_web_app.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());

        if (optAddress.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoCustomerIU.getAccountId().toString()));
        }

        Customer customer = new Customer();

        customer.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCustomerIU, customer);

        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        return customer;

    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }

}
