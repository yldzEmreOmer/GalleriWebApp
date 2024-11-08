package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoCustomer;
import com.star.galeri_web_app.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
