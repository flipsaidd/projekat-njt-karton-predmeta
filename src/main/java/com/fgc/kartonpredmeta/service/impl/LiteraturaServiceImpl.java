package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.LiteraturaRepository;
import com.fgc.kartonpredmeta.JPARepo.PredmetLiteraturaRepository;
import com.fgc.kartonpredmeta.dto.LiteraturaDTO;
import com.fgc.kartonpredmeta.mapper.LiteraturaMapper;
import com.fgc.kartonpredmeta.model.Literatura;
import com.fgc.kartonpredmeta.service.LiteraturaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LiteraturaServiceImpl implements LiteraturaService {

    private final LiteraturaRepository literaturaRepository;
    private final LiteraturaMapper literaturaMapper;
    private final PredmetLiteraturaRepository predmetLiteraturaRepository;

    @Override
    @Transactional
    public LiteraturaDTO createLiteratura(LiteraturaDTO literaturaDTO) {
        Literatura literatura = literaturaMapper.toEntity(literaturaDTO);
        Literatura savedLiteratura = literaturaRepository.save(literatura);
        return literaturaMapper.toDTO(savedLiteratura);
    }

    @Override
    public LiteraturaDTO getLiteraturaById(Long id) {
        return literaturaRepository.findById(id).map(literaturaMapper::toDTO).orElseThrow(()->new RuntimeException("Literatura ne postoji sa datim ID-jem"));
    }

    @Override
    public List<LiteraturaDTO> getAllLiteratura() {
        List<Literatura> literaturaList= literaturaRepository.findAll();
        return literaturaList.stream().map(literaturaMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public LiteraturaDTO updateLiteratura(Long id, LiteraturaDTO literaturaDTO) {
        Literatura literatura= literaturaRepository.findById(id).orElseThrow(()->new RuntimeException("Literatura ne postoji sa datim ID-jem"));

        literaturaMapper.updateEntityFromDTO(literaturaDTO, literatura);

        Literatura updatedLiteratura = literaturaRepository.save(literatura);
        return literaturaMapper.toDTO(updatedLiteratura);
    }

    @Override
    @Transactional
    public void deleteLiteratura(Long id) {
        if(!literaturaRepository.existsById(id)){
            throw new RuntimeException("Literatura ne postoji sa datim ID-jem");
        }
        if(predmetLiteraturaRepository.existsByLiteraturaId(id)){
            throw new RuntimeException("Literatura ne može biti obrisana jer je vezana za predmete");
        }
        literaturaRepository.deleteById(id);
    }
}
