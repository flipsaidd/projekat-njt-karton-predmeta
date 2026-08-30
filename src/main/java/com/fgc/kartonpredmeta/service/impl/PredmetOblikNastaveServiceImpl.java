package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.OblikNastaveRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetOblikNastaveRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveResponseDTO;
import com.fgc.kartonpredmeta.mapper.PredmetOblikNastaveMapper;
import com.fgc.kartonpredmeta.model.*;
import com.fgc.kartonpredmeta.service.PredmetOblikNastaveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PredmetOblikNastaveServiceImpl implements PredmetOblikNastaveService {

    private final PredmetOblikNastaveRepository predmetOblikNastaveRepository;
    private final PredmetOblikNastaveMapper predmetOblikNastaveMapper;
    private final PredmetRepository predmetRepository;
    private final OblikNastaveRepository oblikNastaveRepository;

    @Override
    @Transactional
    public PredmetOblikNastaveResponseDTO dodajPredmetOblik(Long predmetId, PredmetOblikNastaveRequestDTO requestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(()->new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));
        OblikNastave oblik = oblikNastaveRepository.findById(requestDTO.getOblikId())
                .orElseThrow(()->new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem"));
        if (predmet.getOblici().stream().anyMatch(o -> o.getOblikNastave().getId().equals(oblik.getId()))) {
            throw new IllegalArgumentException("Ovaj oblik nastave je već postavljen na ovom predmetu");
        }
        PredmetOblikNastave predmetOblikNastave = predmetOblikNastaveMapper.toEntity(requestDTO);
        predmetOblikNastave.setPredmet(predmet);
        predmetOblikNastave.setOblikNastave(oblik);
        return predmetOblikNastaveMapper.toResponseDTO(predmetOblikNastaveRepository.save(predmetOblikNastave));
    }

    @Override
    public List<PredmetOblikNastaveResponseDTO> getAllObliciNastaveByPredmetId(Long predmetId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        return predmet.getOblici().stream()
                .map(predmetOblikNastaveMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public PredmetOblikNastaveResponseDTO updatePredmetOblikNastave(Long predmetId, Long predmetOblikId, PredmetOblikNastaveRequestDTO requestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        PredmetOblikNastave predmetOblikNastave = predmetOblikNastaveRepository.findById(predmetOblikId)
                .orElseThrow(() -> new EntityNotFoundException("Oblik vezan za dati predmet ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetOblikNastave.getPredmet() != null ? predmetOblikNastave.getPredmet().getId() : null)) {
            throw new IllegalArgumentException("Oblik ne pripada datom predmetu");
        }

        OblikNastave oblik = oblikNastaveRepository.findById(requestDTO.getOblikId())
                .orElseThrow(() -> new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem"));

        boolean postojiDuplikat = predmet.getOblici().stream()
                .anyMatch(o -> !o.getId().equals(predmetOblikId) && o.getOblikNastave().getId().equals(oblik.getId()));
        if (postojiDuplikat) {
            throw new IllegalArgumentException("Oblik nastave je već dodat na ovom predmetu");
        }

        predmetOblikNastaveMapper.updateEntityFromDTO(requestDTO, predmetOblikNastave);
        predmetOblikNastave.setOblikNastave(oblik);

        return predmetOblikNastaveMapper.toResponseDTO(predmetOblikNastaveRepository.save(predmetOblikNastave));
    }

    @Override
    @Transactional
    public void deletePredmetOblikNastave(Long predmetId, Long predmetOblikNastaveId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        PredmetOblikNastave predmetOblikNastave = predmetOblikNastaveRepository.findById(predmetOblikNastaveId)
                .orElseThrow(() -> new EntityNotFoundException("Oblik vezan za dati predmet ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetOblikNastave.getPredmet() != null ? predmetOblikNastave.getPredmet().getId() : null)) {
            throw new IllegalArgumentException("Oblik ne pripada datom predmetu");
        }

        predmetOblikNastaveRepository.delete(predmetOblikNastave);
    }
}
