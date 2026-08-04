package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.StudijskiProgramRepository;
import com.fgc.kartonpredmeta.dto.StudijskiProgramDTO;
import com.fgc.kartonpredmeta.mapper.StudijskiProgramMapper;
import com.fgc.kartonpredmeta.model.StudijskiProgram;
import com.fgc.kartonpredmeta.service.StudijskiProgramService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudijskiProgramServiceImpl implements StudijskiProgramService {

    private final StudijskiProgramRepository studijskiProgramRepository;
    private final StudijskiProgramMapper studijskiProgramMapper;

    @Override
    @Transactional
    public StudijskiProgramDTO createStudijskiProgram(StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgram sp=studijskiProgramRepository.save(studijskiProgramMapper.toEntity(studijskiProgramDTO));
        return studijskiProgramMapper.toDTO(sp);
    }

    @Override
    public StudijskiProgramDTO getStudijskiProgram(Long id) {
        return studijskiProgramRepository.findById(id).map(studijskiProgramMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Studijski program ne postoji sa datim ID-jem"));
    }

    @Override
    public List<StudijskiProgramDTO> getAllStudijskiProgrami() {
        List<StudijskiProgram> studijskiProgrami=studijskiProgramRepository.findAll();
        return studijskiProgrami.stream().map(studijskiProgramMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public StudijskiProgramDTO updateStudijskiProgram(Long id, StudijskiProgramDTO studijskiProgramDTO) {
        StudijskiProgram sp=studijskiProgramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Studijski program ne postoji sa datim ID-jem"));
        studijskiProgramMapper.updateEntityFromDTO(studijskiProgramDTO, sp);
        StudijskiProgram updatedSP=studijskiProgramRepository.save(sp);
        return studijskiProgramMapper.toDTO(updatedSP);
    }

    @Override
    @Transactional
    public void deleteStudijskiProgram(Long id) {
        StudijskiProgram sp=studijskiProgramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Studijski program ne postoji sa datim ID-jem"));
        studijskiProgramRepository.delete(sp);
    }
}
