package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.DtoGallerist;
import com.star.galeri_web_app.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
