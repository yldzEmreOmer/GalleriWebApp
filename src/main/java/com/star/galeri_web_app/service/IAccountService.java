package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoAccount;
import com.star.galeri_web_app.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
