package com.fgc.kartonpredmeta.service;

import com.fgc.kartonpredmeta.dto.OblikNastaveDTO;

import java.util.List;

public interface OblikNastaveService {

    OblikNastaveDTO createOblikNastave(OblikNastaveDTO oblikNastaveDTO);
    OblikNastaveDTO getOblikNastave(Long id);
    List<OblikNastaveDTO> getAllOblikNastave();
    OblikNastaveDTO updateOblikNastave(Long id, OblikNastaveDTO oblikNastaveDTO);
    void deleteOblikNastave(Long id);
}
