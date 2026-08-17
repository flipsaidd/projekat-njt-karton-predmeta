package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetFilterDTO;
import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PredmetService {
    List<PredmetResponseDTO> findAll();
    PredmetResponseDTO findById(Long id);
    Page<PredmetResponseDTO> findByFilter(PredmetFilterDTO filter, Pageable pageable);
    PredmetResponseDTO create(PredmetRequestDTO requestDTO);
    PredmetResponseDTO update(Long id, PredmetRequestDTO requestDTO);
    void delete(Long id);
}
