package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetnaObavezaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaResponseDTO;

import java.util.List;

public interface PredmetnaObavezaService {
    PredmetnaObavezaResponseDTO addPredmetnaObaveza(Long predmetId, PredmetnaObavezaRequestDTO requestDTO);
    List<PredmetnaObavezaResponseDTO> getAllPredmetneObavezeByPredmetId(Long predmetId);
    void deletePredmetnaObaveza(Long predmetId, Long obavezaId);
    PredmetnaObavezaResponseDTO updatePredmetnaObaveza(Long predmetId, Long obavezaId, PredmetnaObavezaRequestDTO requestDTO);
}
