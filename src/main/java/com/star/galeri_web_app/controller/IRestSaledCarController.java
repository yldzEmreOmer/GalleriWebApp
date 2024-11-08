package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoSaledCar;
import com.star.galeri_web_app.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
