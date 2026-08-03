package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.LiteraturaDTO;
import com.fgc.kartonpredmeta.model.Literatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface LiteraturaMapper {

    LiteraturaDTO toDTO(Literatura entitet);

    @Mapping(target = "id", ignore = true)
    Literatura toEntity(LiteraturaDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(LiteraturaDTO dto, @MappingTarget Literatura entitet);

}
