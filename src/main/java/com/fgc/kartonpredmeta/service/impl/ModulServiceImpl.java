package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.ModulRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetModulRepository;
import com.fgc.kartonpredmeta.JPARepo.StudijskiProgramRepository;
import com.fgc.kartonpredmeta.dto.ModulDTO;
import com.fgc.kartonpredmeta.mapper.ModulMapper;
import com.fgc.kartonpredmeta.model.Modul;
import com.fgc.kartonpredmeta.model.StudijskiProgram;
import com.fgc.kartonpredmeta.service.ModulService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModulServiceImpl implements ModulService {

    private final ModulRepository modulRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final ModulMapper modulMapper;
    private final PredmetModulRepository predmetModulRepository;

    @Override
    @Transactional
    public ModulDTO createModul(ModulDTO modulDTO) {
        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(modulDTO.getStudijskiProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Studijski program ne postoji sa datim ID-jem"));
        Modul modul = modulMapper.toEntity(modulDTO);
        modul.setStudijskiProgram(studijskiProgram);

        return modulMapper.toDTO(modulRepository.save(modul));
    }

    @Override
    public ModulDTO getModulById(Long id) {
        Modul modul = modulRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modul ne postoji sa datim ID-jem"));
        return modulMapper.toDTO(modul);
    }

    @Override
    public List<ModulDTO> getAllModuli() {
        return modulRepository.findAll().stream()
                .map(modulMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public ModulDTO updateModul(Long id, ModulDTO modulDTO) {
        Modul modul = modulRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modul ne postoji sa datim ID-jem"));

        if(modulDTO.getStudijskiProgramId() != null) {
            StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(modulDTO.getStudijskiProgramId())
                    .orElseThrow(() -> new EntityNotFoundException("Studijski program ne postoji sa datim ID-jem"));
            modul.setStudijskiProgram(studijskiProgram);
        }

        modulMapper.updateEntityFromDTO(modulDTO, modul);
        return modulMapper.toDTO(modulRepository.save(modul));
    }

    @Override
    @Transactional
    public void deleteModul(Long id) {
        if(!modulRepository.existsById(id)) {
            throw new EntityNotFoundException("Modul ne postoji sa datim ID-jem");
        }

        if(predmetModulRepository.existsByModulId(id)) {
            throw new IllegalStateException("Modul ne može biti obrisan jer je vezan za predmete");
        }

        modulRepository.deleteById(id);
    }
}
