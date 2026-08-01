package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.VrstaObaveze;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetnaObavezaRequestDTO implements DomainDTO{

    @NotBlank(message = "Naziv obaveze je obavezan")
    private String naziv;

    @NotNull(message = "Maksimalan broj poena je obavezan")
    private Integer maxPoena;

    @NotNull(message = "Vrsta obaveze je obavezna")
    private VrstaObaveze vrstaObaveze;
}
