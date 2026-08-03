package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.ModulDTO;
import com.fgc.kartonpredmeta.model.Modul;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ModulMapper {

    @Mapping(source= "studijskiProgram.id", target = "studijskiProgramId")
    @Mapping(source= "studijskiProgram.naziv", target = "studijskiProgramNaziv")
    ModulDTO toDTO(Modul entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studijskiProgramId", target = "studijskiProgram.id")
    Modul toEntity(ModulDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studijskiProgramId", target = "studijskiProgram.id")
    void updateEntityFromDTO(ModulDTO dto, @MappingTarget Modul entitet);
}
