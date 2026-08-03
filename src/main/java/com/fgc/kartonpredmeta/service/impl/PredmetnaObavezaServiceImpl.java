package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetnaObavezaRepository;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaResponseDTO;
import com.fgc.kartonpredmeta.mapper.PredmetnaObavezaMapper;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.model.PredmetnaObaveza;
import com.fgc.kartonpredmeta.service.PredmetnaObavezaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredmetnaObavezaServiceImpl implements PredmetnaObavezaService {

    private final PredmetnaObavezaRepository predmetnaObavezaRepository;
    private final PredmetRepository predmetRepository;
    private final PredmetnaObavezaMapper predmetnaObavezaMapper;

    @Override
    @Transactional
    public PredmetnaObavezaResponseDTO addPredmetnaObaveza(Long predmetId, PredmetnaObavezaRequestDTO requestDTO) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetnaObaveza po=predmetnaObavezaMapper.toEntity(requestDTO);
        po.setPredmet(predmet);
        return predmetnaObavezaMapper.toResponseDTO(predmetnaObavezaRepository.save(po));
    }

    @Override
    public List<PredmetnaObavezaResponseDTO> getAllPredmetneObavezeByPredmetId(Long predmetId) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));
        return predmet.getObaveze().stream()
                .map(predmetnaObavezaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deletePredmetnaObaveza(Long predmetId, Long obavezaId) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetnaObaveza obavezaZaBrisanje=predmet.getObaveze().stream()
                .filter(po->po.getId().equals(obavezaId))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Predmetna obaveza ne postoji sa datim ID-jem"));
        predmetnaObavezaRepository.delete(obavezaZaBrisanje);
    }

    @Override
    @Transactional
    public PredmetnaObavezaResponseDTO updatePredmetnaObaveza(Long predmetId, Long obavezaId, PredmetnaObavezaRequestDTO requestDTO) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetnaObaveza obavezaZaUpdate=predmetnaObavezaRepository.findById(obavezaId)
                .orElseThrow(()->new RuntimeException("Predmetna obaveza ne postoji sa datim ID-jem"));
        if(!obavezaZaUpdate.getPredmet().getId().equals(predmet.getId()))
        {
            throw new RuntimeException("Predmetna obaveza ne pripada datom predmetu");
        }

        predmetnaObavezaMapper.updateEntityFromDTO(requestDTO, obavezaZaUpdate);

        return predmetnaObavezaMapper.toResponseDTO(predmetnaObavezaRepository.save(obavezaZaUpdate));
    }
}
