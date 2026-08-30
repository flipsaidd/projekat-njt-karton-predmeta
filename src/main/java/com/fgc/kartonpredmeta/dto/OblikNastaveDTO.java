package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OblikNastaveDTO implements DomainDTO{

    @NotBlank(message = "Naziv oblika je obavezan")
    private Long id;

    private String naziv;
}
