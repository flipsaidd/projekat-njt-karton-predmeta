package com.fgc.kartonpredmeta.service;


import com.fgc.kartonpredmeta.dto.ModulDTO;

import java.util.List;

public interface ModulService {
    ModulDTO createModul(ModulDTO modulDTO);
    ModulDTO getModulById(Long id);
    List<ModulDTO> getAllModuli();
    ModulDTO updateModul(Long id, ModulDTO modulDTO);
    void deleteModul(Long id);
}
