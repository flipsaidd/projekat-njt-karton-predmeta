package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.mapper.PredmetMapper;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.service.PredmetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredmetServiceImpl implements PredmetService {

    private final PredmetRepository predmetRepository;
    private final PredmetMapper predmetMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PredmetResponseDTO> findAll() {
        return predmetRepository.findAll().stream()
                .map(predmetMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PredmetResponseDTO findById(Long id) {
        Predmet predmet=predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));
        return predmetMapper.toResponseDTO(predmet);
    }

    @Override
    @Transactional
    public PredmetResponseDTO create(PredmetRequestDTO requestDTO) {
        Predmet predmet = predmetMapper.toEntity(requestDTO);
        Predmet savedPredmet = predmetRepository.save(predmet);
        return predmetMapper.toResponseDTO(savedPredmet);
    }

    @Override
    @Transactional
    public PredmetResponseDTO update(Long id, PredmetRequestDTO requestDTO) {
        Predmet existingPredmet = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        Predmet updatedPredmet = predmetMapper.toEntity(requestDTO);
        updatedPredmet.setId(existingPredmet.getId());

        if(updatedPredmet.getLiterature()!=null)
        {
            updatedPredmet.getLiterature().forEach(pl->pl.setPredmet(updatedPredmet));
        }

        if(updatedPredmet.getAngazovanja()!=null)
        {
            updatedPredmet.getAngazovanja().forEach(a->a.setPredmet(updatedPredmet));
        }

        if(updatedPredmet.getObaveze()!=null)
        {
            updatedPredmet.getObaveze().forEach(po->po.setPredmet(updatedPredmet));
        }

        if(updatedPredmet.getModuli()!=null)
        {
            updatedPredmet.getModuli().forEach(pm->pm.setPredmet(updatedPredmet));
        }

        Predmet savedPredmet = predmetRepository.save(updatedPredmet);
        return predmetMapper.toResponseDTO(savedPredmet);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));
        predmetRepository.delete(predmet);
    }
}
