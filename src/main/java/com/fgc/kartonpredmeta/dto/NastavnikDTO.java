package com.fgc.kartonpredmeta.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NastavnikDTO implements DomainDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String zvanje;
    private String email;
}
