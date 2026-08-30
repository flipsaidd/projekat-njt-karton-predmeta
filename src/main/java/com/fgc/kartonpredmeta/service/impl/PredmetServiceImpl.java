package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.*;
import com.fgc.kartonpredmeta.dto.*;
import com.fgc.kartonpredmeta.mapper.PredmetMapper;
import com.fgc.kartonpredmeta.model.Predmet;
import com.fgc.kartonpredmeta.service.PredmetService;
import com.fgc.kartonpredmeta.specification.PredmetSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredmetServiceImpl implements PredmetService {

    private final PredmetRepository predmetRepository;
    private final PredmetMapper predmetMapper;

    private final NastavnikRepository nastavnikRepository;
    private final LiteraturaRepository literaturaRepository;
    private final ModulRepository modulRepository;
    private final OblikNastaveRepository oblikNastaveRepository;

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

    public Page<PredmetResponseDTO> findByFilter(PredmetFilterDTO filter, Pageable pageable) {
        Specification<Predmet> specification = PredmetSpecification.build(filter);
        Page<Predmet> predmetPage=predmetRepository.findAll(specification, pageable);
        return predmetPage.map(predmetMapper::toResponseDTO);
    }


    @Override
    @Transactional
    public PredmetResponseDTO create(PredmetRequestDTO requestDTO) {
        Predmet predmet = predmetMapper.toEntity(requestDTO);

        proveraIntegriteta(requestDTO);

        Predmet savedPredmet = predmetRepository.save(predmet);
        return predmetMapper.toResponseDTO(savedPredmet);
    }

    @Override
    @Transactional
    public PredmetResponseDTO update(Long id, PredmetRequestDTO requestDTO) {
        Predmet existingPredmet = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));

        if(!existingPredmet.getSifra().equals(requestDTO.getSifra()) && predmetRepository.existsBySifra(requestDTO.getSifra()))
            throw new IllegalArgumentException("Predmet sa novom šifrom već postoji.");

        predmetMapper.updateEntityFromDTO(
                requestDTO,
                existingPredmet
        );

        Predmet savedPredmet = predmetRepository.save(existingPredmet);
        return predmetMapper.toResponseDTO(savedPredmet);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Predmet ne postoji sa datim ID-jem"));
        predmetRepository.delete(predmet);
    }

    private void proveraIntegriteta(PredmetRequestDTO requestDTO){
        boolean imaNastavnika = requestDTO.getAngazovanja().stream()
                .allMatch((a)->nastavnikRepository.existsById(a.getNastavnikId()));
        if(!imaNastavnika) throw new EntityNotFoundException("Nastavnik ne postoji sa datim ID-jem");

        boolean imaLiterature = requestDTO.getLiterature().stream()
                .allMatch((l)->literaturaRepository.existsById(l.getLiteraturaId()));
        if(!imaLiterature) throw new EntityNotFoundException("Literatura ne postoji sa datim ID-jem");

        boolean imaModula = requestDTO.getModuli().stream()
                .allMatch((m)->modulRepository.existsById(m.getModulId()));
        if(!imaModula) throw new EntityNotFoundException("Modul ne postoji sa datim ID-jem");

        boolean imaOblika = requestDTO.getOblici().stream()
                .allMatch((o)->oblikNastaveRepository.existsById(o.getOblikId()));
        if(!imaOblika) throw new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem");

        long uniqueNastavnici = requestDTO.getAngazovanja().stream()
                .map(AngazovanjeRequestDTO::getNastavnikId)
                .distinct().count();
        if(uniqueNastavnici<requestDTO.getAngazovanja().size()) throw new IllegalArgumentException("Isti nastavnik je više puta angažovan na predmetu.");

        long uniqueLiterature = requestDTO.getLiterature().stream()
                .map(PredmetLiteraturaRequestDTO::getLiteraturaId)
                .distinct().count();
        if(uniqueLiterature<requestDTO.getLiterature().size()) throw new IllegalArgumentException("Isti literatura je više puta dodata na predmetu.");

        long uniqueModula = requestDTO.getModuli().stream()
                .map(PredmetModulRequestDTO::getModulId)
                .distinct().count();
        if(uniqueModula<requestDTO.getModuli().size()) throw new IllegalArgumentException("Isti modul je više puta dodeljen predmetu.");

        long uniqueOblika = requestDTO.getOblici().stream()
                .map(PredmetOblikNastaveRequestDTO::getOblikId)
                .distinct().count();
        if(uniqueOblika<requestDTO.getOblici().size()) throw new IllegalArgumentException("Isti oblik nastave je više puta evidentiran predmetu.");

    }
}
