package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.StatusPredmeta;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetModulResponseDTO implements DomainDTO {

    private Long id;
    private Long modulId;
    private String modulNaziv;
    private StatusPredmeta statusPredmeta;
}
