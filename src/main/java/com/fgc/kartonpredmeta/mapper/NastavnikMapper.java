package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.NastavnikDTO;
import com.fgc.kartonpredmeta.model.Nastavnik;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NastavnikMapper {

    NastavnikDTO toDTO(Nastavnik entitet);

    @Mapping(target = "id", ignore = true)
    Nastavnik toEntity(NastavnikDTO dto);
}
