package com.star.galeri_web_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.galeri_web_app.controller.IRestAuthenticationController;
import com.star.galeri_web_app.controller.RestBaseController;
import com.star.galeri_web_app.controller.RootEntity;
import com.star.galeri_web_app.dto.AuthRequest;
import com.star.galeri_web_app.dto.AuthResponse;
import com.star.galeri_web_app.dto.DtoUser;
import com.star.galeri_web_app.dto.RefreshTokenRequest;
import com.star.galeri_web_app.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {

        return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {

        return ok(authenticationService.authenticate(input));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticationService.refreshToken(input));
    }

}
