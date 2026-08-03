package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.ModulRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetModulRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.dto.PredmetModulRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetModulResponseDTO;
import com.fgc.kartonpredmeta.mapper.PredmetModulMapper;
import com.fgc.kartonpredmeta.model.Modul;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.model.PredmetModul;
import com.fgc.kartonpredmeta.service.PredmetModulService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredmetModulServiceImpl implements PredmetModulService {

    private final PredmetModulRepository predmetModulRepository;
    private final PredmetModulMapper predmetModulMapper;
    private final PredmetRepository predmetRepository;
    private final ModulRepository modulRepository;

    @Override
    @Transactional
    public PredmetModulResponseDTO dodajPredmetModul(Long predmetId, PredmetModulRequestDTO requestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        Modul modul = modulRepository.findById(requestDTO.getModulId())
                .orElseThrow(() -> new RuntimeException("Modul ne postoji sa datim ID-jem"));

        if (predmet.getModuli().stream().anyMatch(m -> m.getId().equals(modul.getId()))) {
            throw new RuntimeException("Modul je već povezan sa ovim predmetom");
        }

        PredmetModul predmetModul=predmetModulMapper.toEntity(requestDTO);
        predmetModul.setPredmet(predmet);
        predmetModul.setModul(modul);

        return predmetModulMapper.toResponseDTO(predmetModulRepository.save(predmetModul));
    }

    @Override
    public List<PredmetModulResponseDTO> getAllModuliByPredmetId(Long predmetId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        return predmet.getModuli().stream()
                .map(predmetModulMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public PredmetModulResponseDTO updatePredmetModul(Long predmetId, Long predmetModulId, PredmetModulRequestDTO requestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetModul predmetModul = predmetModulRepository.findById(predmetModulId)
                .orElseThrow(() -> new RuntimeException("PredmetModul ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetModul.getPredmet() != null ? predmetModul.getPredmet().getId() : null)) {
            throw new RuntimeException("PredmetModul ne pripada datom predmetu");
        }

        Modul modul = modulRepository.findById(requestDTO.getModulId())
                .orElseThrow(() -> new RuntimeException("Modul ne postoji sa datim ID-jem"));

        boolean postojiDuplikat = predmet.getModuli().stream()
                .anyMatch(pm -> !pm.getId().equals(predmetModulId) && pm.getModul().getId().equals(modul.getId()));
        if (postojiDuplikat) {
            throw new RuntimeException("Modul je već povezan sa ovim predmetom");
        }

        predmetModulMapper.updateEntityFromDTO(requestDTO, predmetModul);
        predmetModul.setModul(modul);

        return predmetModulMapper.toResponseDTO(predmetModulRepository.save(predmetModul));
    }

    @Override
    @Transactional
    public void deletePredmetModul(Long predmetId, Long predmetModulId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji sa datim ID-jem"));

        PredmetModul predmetModul = predmetModulRepository.findById(predmetModulId)
                .orElseThrow(() -> new RuntimeException("PredmetModul ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(predmetModul.getPredmet() != null ? predmetModul.getPredmet().getId() : null)) {
            throw new RuntimeException("PredmetModul ne pripada datom predmetu");
        }

        predmetModulRepository.delete(predmetModul);
    }
}
