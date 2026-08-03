package com.fgc.kartonpredmeta.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO implements DomainDTO{
    private String token;
    private KorisnikDTO korisnik;
}
