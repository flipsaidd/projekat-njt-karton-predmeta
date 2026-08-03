package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.model.Predmet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses={PredmetModulMapper.class, AngazovanjeMapper.class, PredmetnaObavezaMapper.class, PredmetLiteraturaMapper.class})
public interface PredmetMapper {

    PredmetResponseDTO toResponseDTO(Predmet entitet);

    @Mapping(target = "id", ignore = true)
    Predmet toEntity(PredmetRequestDTO dto);
}
