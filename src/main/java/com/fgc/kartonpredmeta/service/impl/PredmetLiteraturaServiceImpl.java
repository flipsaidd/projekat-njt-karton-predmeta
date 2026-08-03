package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.LiteraturaRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetLiteraturaRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.dto.PredmetLiteraturaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetLiteraturaResponseDTO;
import com.fgc.kartonpredmeta.mapper.PredmetLiteraturaMapper;
import com.fgc.kartonpredmeta.model.Literatura;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.model.PredmetLiteratura;
import com.fgc.kartonpredmeta.service.PredmetLiteraturaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PredmetLiteraturaServiceImpl implements PredmetLiteraturaService {

    private final PredmetLiteraturaRepository predmetLiteraturaRepository;
    private final PredmetLiteraturaMapper predmetLiteraturaMapper;
    private final PredmetRepository predmetRepository;
    private final LiteraturaRepository literaturaRepository;

    @Override
    @Transactional
    public PredmetLiteraturaResponseDTO dodajLiteraturuNaPredmet(Long predmetId, PredmetLiteraturaRequestDTO requestDTO) {
        Predmet predmet= predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));
        Literatura literatura= literaturaRepository.findById(requestDTO.getLiteraturaId())
                .orElseThrow(()->new RuntimeException("Literatura ne postoji sa datim ID-jem"));

        if(predmet.getLiterature().stream().anyMatch(pl->pl.getLiteratura().getId().equals(literatura.getId()))){
            throw new RuntimeException("Literatura je već dodata na predmet");
        }

        PredmetLiteratura predmetLiteratura= predmetLiteraturaMapper.toEntity(requestDTO);
        predmetLiteratura.setPredmet(predmet);
        predmetLiteratura.setLiteratura(literatura);

        return predmetLiteraturaMapper.toResponseDTO(predmetLiteraturaRepository.save(predmetLiteratura));
    }

    @Override
    public List<PredmetLiteraturaResponseDTO> getAllLiteraturaByPredmetId(Long predmetId) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));
        return predmet.getLiterature().stream().map(predmetLiteraturaMapper::toResponseDTO).toList();
    }

    @Override
    @Transactional
    public PredmetLiteraturaResponseDTO updatePredmetLiteratura(Long predmetId, Long predmetLiteraturaId, PredmetLiteraturaRequestDTO requestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetLiteratura predmetLiteratura = predmetLiteraturaRepository.findById(predmetLiteraturaId)
                .orElseThrow(() -> new RuntimeException("PredmetLiteratura ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetLiteratura.getPredmet() != null ? predmetLiteratura.getPredmet().getId() : null)) {
            throw new RuntimeException("Literatura ne pripada datom predmetu");
        }

        Literatura literatura = literaturaRepository.findById(requestDTO.getLiteraturaId())
                .orElseThrow(() -> new RuntimeException("Literatura ne postoji sa datim ID-jem"));

        boolean postojiDuplikat = predmet.getLiterature().stream()
                .anyMatch(pl -> !pl.getId().equals(predmetLiteraturaId) && pl.getLiteratura().getId().equals(literatura.getId()));
        if (postojiDuplikat) {
            throw new RuntimeException("Literatura je već dodata na predmet");
        }

        predmetLiteraturaMapper.updateEntityFromDTO(requestDTO, predmetLiteratura);
        predmetLiteratura.setLiteratura(literatura);

        return predmetLiteraturaMapper.toResponseDTO(predmetLiteraturaRepository.save(predmetLiteratura));
    }

    @Override
    @Transactional
    public void deleteLiteraturaFromPredmet(Long predmetId, Long predmetLiteraturaId) {
        Predmet predmet=predmetRepository.findById(predmetId)
                .orElseThrow(()->new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetLiteratura predmetLiteratura=predmetLiteraturaRepository.findById(predmetLiteraturaId)
                .orElseThrow(()->new RuntimeException("PredmetLiteratura ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetLiteratura.getPredmet() != null ? predmetLiteratura.getPredmet().getId() : null)) {
            throw new RuntimeException("Literatura ne pripada datom predmetu");
        }

        predmetLiteraturaRepository.delete(predmetLiteratura);

    }
}
