package com.fgc.kartonpredmeta.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PredmetFilterDTO implements DomainDTO{

    private String naziv;
    private Integer godinaStudija;
    private Integer semestar;
    private Integer espb;
    private Long nastavnikId;
    private Long modulId;
}
