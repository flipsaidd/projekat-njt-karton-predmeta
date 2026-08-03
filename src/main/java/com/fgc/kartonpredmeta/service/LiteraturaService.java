package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.LiteraturaDTO;

import java.util.List;

public interface LiteraturaService {

    LiteraturaDTO createLiteratura(LiteraturaDTO literaturaDTO);
    LiteraturaDTO getLiteraturaById(Long id);
    List<LiteraturaDTO> getAllLiteratura();
    LiteraturaDTO updateLiteratura(Long id, LiteraturaDTO literaturaDTO);
    void deleteLiteratura(Long id);
}
