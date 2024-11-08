package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.DtoGallerist;
import com.star.galeri_web_app.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
