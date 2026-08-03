package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.AngazovanjeRequestDTO;
import com.fgc.kartonpredmeta.dto.AngazovanjeResponseDTO;
import com.fgc.kartonpredmeta.model.Angazovanje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface AngazovanjeMapper {

    @Mapping(source="nastavnik.id", target="nastavnikId")
    @Mapping(source="nastavnik.ime",target="ime")
    @Mapping(source="nastavnik.prezime",target="prezime")
    @Mapping(source="nastavnik.zvanje",target="zvanje")
    AngazovanjeResponseDTO toResponseDTO(Angazovanje entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(source="nastavnikId", target="nastavnik.id")
    Angazovanje toEntity(AngazovanjeRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(target = "nastavnik", ignore = true)
    void updateEntityFromDTO(AngazovanjeRequestDTO dto, @org.mapstruct.MappingTarget Angazovanje entitet);
}
