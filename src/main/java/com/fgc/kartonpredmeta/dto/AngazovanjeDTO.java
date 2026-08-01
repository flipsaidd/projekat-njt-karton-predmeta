package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.UlogaNastavnika;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AngazovanjeDTO implements DomainDTO {

    private Long id;

    private NastavnikDTO nastavnik;

    @NotNull(message = "Uloga nastavnika je obavezna")
    private UlogaNastavnika ulogaNastavnika;
}
