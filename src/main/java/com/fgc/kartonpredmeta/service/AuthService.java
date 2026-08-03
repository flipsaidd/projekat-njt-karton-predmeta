package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.AuthResponseDTO;
import com.fgc.kartonpredmeta.dto.KorisnikDTO;
import com.fgc.kartonpredmeta.dto.LoginRequestDTO;

public interface AuthService {

    AuthResponseDTO login(LoginRequestDTO loginRequest);
}
