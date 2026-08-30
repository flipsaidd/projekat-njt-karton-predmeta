package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.JPARepo.OblikNastaveRepository;
import com.fgc.kartonpredmeta.dto.OblikNastaveDTO;
import com.fgc.kartonpredmeta.mapper.OblikNastaveMapper;
import com.fgc.kartonpredmeta.model.OblikNastave;
import com.fgc.kartonpredmeta.service.OblikNastaveService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OblikNastaveServiceImpl implements OblikNastaveService {

    private final OblikNastaveRepository oblikNastaveRepository;
    private final OblikNastaveMapper oblikNastaveMapper;

    @Override
    @Transactional
    public OblikNastaveDTO createOblikNastave(OblikNastaveDTO oblikNastaveDTO) {
        return oblikNastaveMapper.toDTO(oblikNastaveRepository
                .save(oblikNastaveMapper.toEntity(oblikNastaveDTO)));
    }

    @Override
    public OblikNastaveDTO getOblikNastave(Long id) {
        return oblikNastaveRepository.findById(id).map(oblikNastaveMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem"));
    }

    @Override
    public List<OblikNastaveDTO> getAllOblikNastave() {
        return oblikNastaveRepository.findAll()
                .stream().map(oblikNastaveMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public OblikNastaveDTO updateOblikNastave(Long id, OblikNastaveDTO oblikNastaveDTO) {
        OblikNastave oblik = oblikNastaveRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem"));
        oblikNastaveMapper.updateEntityfromDTO(oblikNastaveDTO,oblik);
        return oblikNastaveMapper.toDTO(oblikNastaveRepository.save(oblik));
    }

    @Override
    @Transactional
    public void deleteOblikNastave(Long id) {
        OblikNastave oblikNastave = oblikNastaveRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Oblik nastave ne postoji sa datim ID-jem"));
        oblikNastaveRepository.delete(oblikNastave);
    }
}
