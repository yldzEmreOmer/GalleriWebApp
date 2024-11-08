package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoCustomer;
import com.star.galeri_web_app.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
