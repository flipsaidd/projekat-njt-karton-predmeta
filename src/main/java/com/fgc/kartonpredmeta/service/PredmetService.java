package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;

import java.util.List;

public interface PredmetService {
    List<PredmetResponseDTO> findAll();
    PredmetResponseDTO findById(Long id);
    PredmetResponseDTO create(PredmetRequestDTO requestDTO);
    PredmetResponseDTO update(Long id, PredmetRequestDTO requestDTO);
    void delete(Long id);
}
