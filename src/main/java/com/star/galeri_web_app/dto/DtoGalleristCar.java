package com.star.galeri_web_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoGalleristCar extends DtoBase {

    private DtoGallerist gallerist;

    private DtoCar car;
}
