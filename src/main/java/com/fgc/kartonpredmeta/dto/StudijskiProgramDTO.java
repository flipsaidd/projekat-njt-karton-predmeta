package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudijskiProgramDTO implements DomainDTO {

    private Long id;

    @NotBlank(message = "Naziv studijskog programa je obavezan")
    private String naziv;

    @NotBlank(message = "Skraćenica studijskog programa je obavezna")
    private String skracenica;

    private List<ModulDTO> moduli;
}