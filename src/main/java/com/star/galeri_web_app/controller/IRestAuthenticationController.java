package com.star.galeri_web_app.controller;

import com.star.galeri_web_app.dto.AuthRequest;
import com.star.galeri_web_app.dto.AuthResponse;
import com.star.galeri_web_app.dto.DtoUser;
import com.star.galeri_web_app.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
