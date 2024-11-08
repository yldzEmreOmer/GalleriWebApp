package com.star.galeri_web_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.galeri_web_app.controller.IRestCarController;
import com.star.galeri_web_app.controller.RestBaseController;
import com.star.galeri_web_app.controller.RootEntity;
import com.star.galeri_web_app.dto.DtoCar;
import com.star.galeri_web_app.dto.DtoCarIU;
import com.star.galeri_web_app.service.ICarService;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarController extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
