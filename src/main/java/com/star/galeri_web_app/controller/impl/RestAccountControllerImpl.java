package com.star.galeri_web_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.galeri_web_app.controller.IRestAccountController;
import com.star.galeri_web_app.controller.RestBaseController;
import com.star.galeri_web_app.controller.RootEntity;
import com.star.galeri_web_app.dto.DtoAccount;
import com.star.galeri_web_app.dto.DtoAccountIU;
import com.star.galeri_web_app.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {

        return ok(accountService.saveAccount(dtoAccountIU));
    }
}
