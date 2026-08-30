package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetOblikNastaveResponseDTO;
import com.fgc.kartonpredmeta.model.PredmetOblikNastave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PredmetOblikNastaveMapper {

    @Mapping(source="oblikNastave.id", target="oblikId")
    @Mapping(source="oblikNastave.naziv", target="naziv")
    PredmetOblikNastaveResponseDTO toResponseDTO(PredmetOblikNastave entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source="oblikId",target="oblikNastave.id")
    @Mapping(target = "predmet", ignore = true)
    PredmetOblikNastave toEntity(PredmetOblikNastaveRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "predmet", ignore = true)
    @Mapping(target = "oblikNastave", ignore = true)
    void updateEntityFromDTO(PredmetOblikNastaveRequestDTO dto, @org.mapstruct.MappingTarget PredmetOblikNastave entitet);

}
