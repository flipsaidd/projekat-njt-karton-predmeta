package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveResponseDTO;

import java.util.List;

public interface PredmetOblikNastaveService {

    PredmetOblikNastaveResponseDTO dodajPredmetOblik(Long predmetId, PredmetOblikNastaveRequestDTO requestDTO);
    List<PredmetOblikNastaveResponseDTO> getAllObliciNastaveByPredmetId(Long predmetId);
    PredmetOblikNastaveResponseDTO updatePredmetOblikNastave(Long predmetId, Long predmetOblikId, PredmetOblikNastaveRequestDTO requestDTO);
    void deletePredmetOblikNastave(Long predmetId, Long predmetOblikNastaveId);
}
