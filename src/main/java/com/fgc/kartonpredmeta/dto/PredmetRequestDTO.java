package com.fgc.kartonpredmeta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredmetRequestDTO implements DomainDTO {

    @NotBlank(message = "Naziv predmeta je obavezan")
    private String naziv;

    @NotBlank(message = "Šifra predmeta je obavezna")
    private String sifra;

    @NotNull(message = "ESPB je obavezan")
    private Integer espb;

    @NotNull(message = "Godina studija je obavezna")
    private Integer godinaStudija;

    @NotNull(message = "Semestar je obavezan")
    private Integer semestar;

    private String cilj;
    private String ishodi;
    private String sadrzajPredavanja;
    private String sadrzajVezbi;
    private String nacinPolaganja;

    @Valid
    private List<PredmetModulRequestDTO> moduli;

    @Valid
    private List<AngazovanjeRequestDTO> angazovanja;

    @Valid
    private List<PredmetLiteraturaRequestDTO> literature;

    @Valid
    private List<PredmetnaObavezaRequestDTO> obaveze;

    @Valid
    private List<PredmetOblikNastaveRequestDTO> oblici;
}
