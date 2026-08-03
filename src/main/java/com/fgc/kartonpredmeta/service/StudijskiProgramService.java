package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.LiteraturaDTO;
import com.fgc.kartonpredmeta.dto.StudijskiProgramDTO;

import java.util.List;

public interface StudijskiProgramService {
    StudijskiProgramDTO createStudijskiProgram(StudijskiProgramDTO studijskiProgramDTO);
    StudijskiProgramDTO getStudijskiProgram(Long id);
    List<StudijskiProgramDTO> getAllStudijskiProgrami();
    StudijskiProgramDTO updateStudijskiProgram(Long id, StudijskiProgramDTO studijskiProgramDTO);
    void deleteStudijskiProgram(Long id);
}
