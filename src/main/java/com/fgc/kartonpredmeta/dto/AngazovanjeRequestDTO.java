package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.UlogaNastavnika;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AngazovanjeRequestDTO implements DomainDTO{

    @NotNull(message = "ID nastavnika je obavezan")
    private Long nastavnikId;

    @NotNull(message = "Uloga nastavnika (npr. NOSILAC, ASISTENT) je obavezna")
    private UlogaNastavnika ulogaNastavnika;
}

