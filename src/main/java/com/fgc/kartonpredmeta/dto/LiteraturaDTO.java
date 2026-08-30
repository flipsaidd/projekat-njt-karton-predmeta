package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotBlank;
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
public class LiteraturaDTO implements DomainDTO {

    private Long id;

    @NotBlank(message = "Naslov je obavezan")
    private String naslov;

    @NotBlank(message = "Autori su obavezni")
    private String autori;

    private Integer godinaIzdanja;

    private String izdavac;

    private String isbn;

}
