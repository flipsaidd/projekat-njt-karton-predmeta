package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModulDTO implements DomainDTO {

    private Long id;

    @NotBlank(message = "Naziv modula ne smije biti prazan")
    private String naziv;

    @NotNull(message = "ID studijskog programa je obavezan")
    private Long studijskiProgramId;

    private String studijskiProgramNaziv;

}
