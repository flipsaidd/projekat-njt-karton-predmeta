package com.fgc.kartonpredmeta.dto;

import com.fgc.kartonpredmeta.model.enums.UlogaNastavnika;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AngazovanjeResponseDTO implements DomainDTO{

    private Long id;

    private Long nastavnikId;
    private String ime;
    private String prezime;
    private String zvanje;

    private UlogaNastavnika ulogaNastavnika;

}
