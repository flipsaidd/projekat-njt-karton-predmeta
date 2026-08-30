package com.fgc.kartonpredmeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredmetOblikNastaveResponseDTO {

    private Long id;

    private Long oblikId;
    private String naziv;

    private Integer fondCasova;
}
