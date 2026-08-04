package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.AngazovanjeRepository;
import com.fgc.kartonpredmeta.JPARepo.NastavnikRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetRepository;
import com.fgc.kartonpredmeta.dto.AngazovanjeRequestDTO;
import com.fgc.kartonpredmeta.dto.AngazovanjeResponseDTO;
import com.fgc.kartonpredmeta.mapper.AngazovanjeMapper;
import com.fgc.kartonpredmeta.model.Angazovanje;
import com.fgc.kartonpredmeta.model.Nastavnik;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.service.AngazovanjeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AngazovanjeServiceImpl implements AngazovanjeService {

    private final AngazovanjeRepository angazovanjeRepository;
    private final PredmetRepository predmetRepository;
    private final NastavnikRepository nastavnikRepository;
    private final AngazovanjeMapper angazovanjeMapper;

    @Override
    @Transactional
    public AngazovanjeResponseDTO dodajAngazovanje(Long predmetId, AngazovanjeRequestDTO angazovanjeRequestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        Nastavnik nastavnik = nastavnikRepository.findById(angazovanjeRequestDTO.getNastavnikId())
                .orElseThrow(() -> new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem"));

        if (predmet.getAngazovanja().stream().anyMatch(a -> a.getNastavnik().getId().equals(nastavnik.getId()))) {
            throw new IllegalArgumentException("Nastavnik je već angažovan na ovom predmetu");
        }

        Angazovanje angazovanje = angazovanjeMapper.toEntity(angazovanjeRequestDTO);
        angazovanje.setPredmet(predmet);
        angazovanje.setNastavnik(nastavnik);

        return angazovanjeMapper.toResponseDTO(angazovanjeRepository.save(angazovanje));
    }

    @Override
    public List<AngazovanjeResponseDTO> getAllAngazovanjaByPredmetId(Long predmetId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        return predmet.getAngazovanja().stream()
                .map(angazovanjeMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public AngazovanjeResponseDTO updateAngazovanje(Long predmetId, Long angazovanjeId, AngazovanjeRequestDTO angazovanjeRequestDTO) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        Angazovanje angazovanje = angazovanjeRepository.findById(angazovanjeId)
                .orElseThrow(() -> new EntityNotFoundException("Angažovanje ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(angazovanje.getPredmet() != null ? angazovanje.getPredmet().getId() : null)) {
            throw new IllegalArgumentException("Angažovanje ne pripada datom predmetu");
        }

        Nastavnik nastavnik = nastavnikRepository.findById(angazovanjeRequestDTO.getNastavnikId())
                .orElseThrow(() -> new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem"));

        boolean postojiDuplikat = predmet.getAngazovanja().stream()
                .anyMatch(a -> !a.getId().equals(angazovanjeId) && a.getNastavnik().getId().equals(nastavnik.getId()));
        if (postojiDuplikat) {
            throw new IllegalArgumentException("Nastavnik je već angažovan na ovom predmetu");
        }

        angazovanjeMapper.updateEntityFromDTO(angazovanjeRequestDTO, angazovanje);
        angazovanje.setNastavnik(nastavnik);

        return angazovanjeMapper.toResponseDTO(angazovanjeRepository.save(angazovanje));
    }

    @Override
    @Transactional
    public void deleteAngazovanje(Long predmetId, Long angazovanjeId) {
        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        Angazovanje angazovanje = angazovanjeRepository.findById(angazovanjeId)
                .orElseThrow(() -> new EntityNotFoundException("Angažovanje ne postoji sa datim ID-jem"));

        if (!predmet.getId().equals(angazovanje.getPredmet() != null ? angazovanje.getPredmet().getId() : null)) {
            throw new IllegalArgumentException("Angažovanje ne pripada datom predmetu");
        }

        angazovanjeRepository.delete(angazovanje);
    }
}
