package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.AngazovanjeRequestDTO;
import com.fgc.kartonpredmeta.dto.AngazovanjeResponseDTO;

import java.util.List;

public interface AngazovanjeService {
    AngazovanjeResponseDTO dodajAngazovanje(Long predmetId, AngazovanjeRequestDTO angazovanjeRequestDTO);
    List<AngazovanjeResponseDTO> getAllAngazovanjaByPredmetId(Long predmetId);
    AngazovanjeResponseDTO updateAngazovanje(Long predmetId, Long angazovanjeId, AngazovanjeRequestDTO angazovanjeRequestDTO);
    void deleteAngazovanje(Long predmetId, Long angazovanjeId);
}
