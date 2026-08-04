package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.AngazovanjeRepository;
import com.fgc.kartonpredmeta.JPARepo.NastavnikRepository;
import com.fgc.kartonpredmeta.dto.NastavnikDTO;
import com.fgc.kartonpredmeta.mapper.NastavnikMapper;
import com.fgc.kartonpredmeta.model.Nastavnik;
import com.fgc.kartonpredmeta.service.NastavnikService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NastavnikServiceImpl implements NastavnikService {

    private final NastavnikRepository nastavnikRepository;
    private final NastavnikMapper nastavnikMapper;
    private final AngazovanjeRepository angazovanjeRepository;

    @Override
    @Transactional
    public NastavnikDTO createNastavnik(NastavnikDTO nastavnikDTO) {
        Nastavnik nastavnik= nastavnikMapper.toEntity(nastavnikDTO);
        Nastavnik savedNastavnik = nastavnikRepository.save(nastavnik);
        return nastavnikMapper.toDTO(savedNastavnik);
    }

    @Override
    public NastavnikDTO getNastavnikById(Long id) {
        return nastavnikRepository.findById(id)
                .map(nastavnikMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem"));
    }

    @Override
    public List<NastavnikDTO> getAllNastavnici() {
        List<Nastavnik> nastavnici = nastavnikRepository.findAll();
        return nastavnici.stream()
                .map(nastavnikMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public NastavnikDTO updateNastavnik(Long id, NastavnikDTO nastavnikDTO) {
        Nastavnik existingNastavnik = nastavnikRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem"));

        nastavnikMapper.updateEntityFromDTO(nastavnikDTO, existingNastavnik);

        Nastavnik updatedNastavnik = nastavnikRepository.save(existingNastavnik);//moze a ne mora zbog dirty checking
        return nastavnikMapper.toDTO(updatedNastavnik);
    }

    @Override
    @Transactional
    public void deleteNastavnik(Long id) {
        if(!nastavnikRepository.existsById(id)) {
            throw new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem");
        }
        if(angazovanjeRepository.existsByNastavnikId(id)) {
            throw new IllegalStateException("Nastavnik ne može biti obrisan jer je vezan za angazovanja");
        }
        nastavnikRepository.deleteById(id);
    }
}
