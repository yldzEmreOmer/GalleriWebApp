package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.AuthRequest;
import com.star.galeri_web_app.dto.AuthResponse;
import com.star.galeri_web_app.dto.DtoUser;
import com.star.galeri_web_app.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
