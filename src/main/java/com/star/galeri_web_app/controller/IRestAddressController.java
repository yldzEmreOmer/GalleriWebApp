package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoAddress;
import com.star.galeri_web_app.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
