package com.star.galeri_web_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.galeri_web_app.controller.IRestCustomerController;
import com.star.galeri_web_app.controller.RestBaseController;
import com.star.galeri_web_app.controller.RootEntity;
import com.star.galeri_web_app.dto.DtoCustomer;
import com.star.galeri_web_app.dto.DtoCustomerIU;
import com.star.galeri_web_app.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {

        return ok(customerService.saveCustomer(dtoCustomerIU));
    }

}
