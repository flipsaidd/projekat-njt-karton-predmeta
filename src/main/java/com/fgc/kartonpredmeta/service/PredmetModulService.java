package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetModulRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetModulResponseDTO;

import java.util.List;

public interface PredmetModulService {
    PredmetModulResponseDTO dodajPredmetModul(Long predmetId, PredmetModulRequestDTO requestDTO);
    List<PredmetModulResponseDTO> getAllModuliByPredmetId(Long predmetId);
    PredmetModulResponseDTO updatePredmetModul(Long predmetId, Long predmetModulId, PredmetModulRequestDTO requestDTO);
    void deletePredmetModul(Long predmetId, Long modulId);
}
