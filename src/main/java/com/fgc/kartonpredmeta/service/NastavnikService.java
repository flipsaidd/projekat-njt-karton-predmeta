package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.NastavnikDTO;

import java.util.List;

public interface NastavnikService {
    NastavnikDTO createNastavnik(NastavnikDTO nastavnikDTO);
    NastavnikDTO getNastavnikById(Long id);
    List<NastavnikDTO> getAllNastavnici();
    NastavnikDTO updateNastavnik(Long id, NastavnikDTO nastavnikDTO);
    void deleteNastavnik(Long id);
}
