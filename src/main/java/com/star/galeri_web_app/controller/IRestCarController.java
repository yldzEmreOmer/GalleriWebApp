package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoCar;
import com.star.galeri_web_app.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
