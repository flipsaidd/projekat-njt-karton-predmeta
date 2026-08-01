package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.VrstaObaveze;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetnaObavezaResponseDTO implements DomainDTO{

    private Long id;
    private String naziv;
    private Integer maxPoena;
    private VrstaObaveze vrstaObaveze;
}
