package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoCar;
import com.star.galeri_web_app.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
