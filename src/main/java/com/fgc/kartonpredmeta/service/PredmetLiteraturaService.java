package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetLiteraturaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetLiteraturaResponseDTO;

import java.util.List;

public interface PredmetLiteraturaService {
    PredmetLiteraturaResponseDTO dodajLiteraturuNaPredmet(Long predmetId, PredmetLiteraturaRequestDTO requestDTO);
    List<PredmetLiteraturaResponseDTO> getAllLiteraturaByPredmetId(Long predmetId);
    PredmetLiteraturaResponseDTO updatePredmetLiteratura(Long predmetId, Long predmetLiteraturaId, PredmetLiteraturaRequestDTO requestDTO);
    void deleteLiteraturaFromPredmet(Long predmetId, Long predmetLiteraturaId);
}
