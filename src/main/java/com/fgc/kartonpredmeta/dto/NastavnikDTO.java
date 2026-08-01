package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NastavnikDTO implements DomainDTO {

    private Long id;

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @NotBlank(message = "Zvanje je obavezno")
    private String zvanje;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Email mora biti u ispravnom formatu")
    private String email;
}
