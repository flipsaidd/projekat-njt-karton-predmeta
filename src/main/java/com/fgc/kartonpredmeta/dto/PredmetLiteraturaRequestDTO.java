package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.VrstaLiterature;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetLiteraturaRequestDTO implements DomainDTO{

    @NotNull(message = "ID literature je obavezan")
    private Long literaturaId;

    @NotNull(message = "Vrsta literature je obavezna")
    private VrstaLiterature vrstaLiterature;


}
