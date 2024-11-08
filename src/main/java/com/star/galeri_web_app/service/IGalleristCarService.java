package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoGalleristCar;
import com.star.galeri_web_app.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
