package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.LiteraturaDTO;
import com.fgc.kartonpredmeta.model.Literatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LiteraturaMapper {

    LiteraturaDTO toDTO(Literatura entitet);

    @Mapping(target = "id", ignore = true)
    Literatura toEntity(LiteraturaDTO dto);

}
