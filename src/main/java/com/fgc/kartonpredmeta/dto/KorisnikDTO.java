package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.Uloga;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KorisnikDTO implements DomainDTO{
    private Long id;
    private String username;
    private String ime;
    private String prezime;
    private Uloga uloga;
}
