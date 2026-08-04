package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.KorisnikRepository;
import com.fgc.kartonpredmeta.dto.AuthResponseDTO;
import com.fgc.kartonpredmeta.dto.LoginRequestDTO;
import com.fgc.kartonpredmeta.mapper.KorisnikMapper;
import com.fgc.kartonpredmeta.model.Korisnik;
import com.fgc.kartonpredmeta.security.JwtService;
import com.fgc.kartonpredmeta.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KorisnikRepository korisnikRepository;
    private final KorisnikMapper korisnikMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        Korisnik korisnik = korisnikRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Korisnik ne postoji sa datim korisničkim imenom"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), korisnik.getPassword())) {
            throw new BadCredentialsException("Pogrešna lozinka");
        }

        String token = jwtService.generateToken(korisnik.getUsername(), korisnik.getUloga().name());

        return AuthResponseDTO.builder()
                .token(token)
                .korisnik(korisnikMapper.toDTO(korisnik))
                .build();
    }

}
