package com.star.galeri_web_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.galeri_web_app.controller.IRestGalleristCarController;
import com.star.galeri_web_app.controller.RestBaseController;
import com.star.galeri_web_app.controller.RootEntity;
import com.star.galeri_web_app.dto.DtoGalleristCar;
import com.star.galeri_web_app.dto.DtoGalleristCarIU;
import com.star.galeri_web_app.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }

}
