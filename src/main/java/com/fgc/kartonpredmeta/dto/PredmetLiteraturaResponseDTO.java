package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.VrstaLiterature;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetLiteraturaResponseDTO implements DomainDTO{

    private Long id;

    private Long literaturaId;
    private String naslov;
    private String autori;
    private Integer godinaIzdanja;
    private String izdavac;
    private String isbn;

    private VrstaLiterature vrstaLiterature;
}
