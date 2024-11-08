package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoSaledCar;
import com.star.galeri_web_app.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
