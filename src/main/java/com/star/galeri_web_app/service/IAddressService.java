package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoAddress;
import com.star.galeri_web_app.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
