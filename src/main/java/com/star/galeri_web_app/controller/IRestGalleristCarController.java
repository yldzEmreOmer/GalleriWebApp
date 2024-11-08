package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoGalleristCar;
import com.star.galeri_web_app.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
