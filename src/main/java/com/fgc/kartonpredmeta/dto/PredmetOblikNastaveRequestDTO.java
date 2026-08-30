package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredmetOblikNastaveRequestDTO {

    @NotNull(message = "ID oblika nastaave je obavezan")
    private Long oblikId;

    @NotNull(message = "Fond casova je obavezan")
    private Integer fondCasova;




}
