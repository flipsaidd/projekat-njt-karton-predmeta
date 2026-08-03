package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetnaObavezaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetnaObavezaResponseDTO;
import com.fgc.kartonpredmeta.model.PredmetnaObaveza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PredmetnaObavezaMapper {

    PredmetnaObavezaResponseDTO toResponseDTO(PredmetnaObaveza entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    PredmetnaObaveza toEntity(PredmetnaObavezaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    void updateEntityFromDTO(PredmetnaObavezaRequestDTO dto, @MappingTarget PredmetnaObaveza entitet);

}
