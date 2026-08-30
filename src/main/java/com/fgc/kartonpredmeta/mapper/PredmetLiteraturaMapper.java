package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetLiteraturaRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetLiteraturaResponseDTO;
import com.fgc.kartonpredmeta.model.PredmetLiteratura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PredmetLiteraturaMapper {

    @Mapping(source="literatura.id", target="literaturaId")
    @Mapping(source="literatura.naslov", target="naslov")
    @Mapping(source="literatura.autori", target="autori")
    @Mapping(source="literatura.godinaIzdanja", target="godinaIzdanja")
    @Mapping(source="literatura.izdavac", target="izdavac")
    @Mapping(source="literatura.isbn", target="isbn")
    PredmetLiteraturaResponseDTO toResponseDTO(PredmetLiteratura entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(source="literaturaId", target="literatura.id")
    PredmetLiteratura toEntity(PredmetLiteraturaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(target = "literatura", ignore = true)
    void updateEntityFromDTO(PredmetLiteraturaRequestDTO dto, @org.mapstruct.MappingTarget PredmetLiteratura entitet);
}
