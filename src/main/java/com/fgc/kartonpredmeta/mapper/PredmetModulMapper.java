package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetModulRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetModulResponseDTO;
import com.fgc.kartonpredmeta.model.PredmetModul;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PredmetModulMapper {

    @Mapping(target = "modulId", source = "modul.id")
    @Mapping(target = "modulNaziv", source = "modul.naziv")
    PredmetModulResponseDTO toResponseDTO(PredmetModul entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(source="modulId", target="modul.id")
    PredmetModul toEntity(PredmetModulRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(target = "modul", ignore = true)
    void updateEntityFromDTO(PredmetModulRequestDTO dto, @org.mapstruct.MappingTarget PredmetModul entitet);
}
