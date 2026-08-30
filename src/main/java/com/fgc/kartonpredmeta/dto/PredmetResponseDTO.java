package com.fgc.kartonpredmeta.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetResponseDTO implements DomainDTO{

    private Long id;

    private String naziv;
    private String sifra;
    private Integer espb;

    private Integer godinaStudija;
    private Integer semestar;

    private String cilj;
    private String ishodi;
    private String sadrzajPredavanja;
    private String sadrzajVezbi;
    private String nacinPolaganja;

    private List<PredmetModulResponseDTO> moduli;

    private List<AngazovanjeResponseDTO> angazovanja;

    private List<PredmetnaObavezaResponseDTO> obaveze;

    private List<PredmetLiteraturaResponseDTO> literature;

    private List<PredmetOblikNastaveResponseDTO> oblici;


}
