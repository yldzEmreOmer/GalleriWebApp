package com.star.galeri_web_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCar extends DtoBase {

    private DtoCustomer customer;

    private DtoGallerist gallerist;

    private DtoCar car;

}
