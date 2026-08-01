package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.StatusPredmeta;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetModulRequestDTO implements DomainDTO {

    @NotNull(message = "ID modula je obavezan")
    private Long modulId;

    @NotNull(message = "Status predmeta (OBAVEZAN/IZBORNI) je obavezan")
    private StatusPredmeta statusPredmeta;
}
