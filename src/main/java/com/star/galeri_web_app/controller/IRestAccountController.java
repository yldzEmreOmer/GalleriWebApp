package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoAccount;
import com.star.galeri_web_app.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
