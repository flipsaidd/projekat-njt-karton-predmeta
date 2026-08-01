package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.ModulDTO;
import com.fgc.kartonpredmeta.model.Modul;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModulMapper {

    @Mapping(source= "studijskiProgram.id", target = "studijskiProgramId")
    @Mapping(source= "studijskiProgram.naziv", target = "studijskiProgramNaziv")
    ModulDTO toDTO(Modul entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studijskiProgramId", target = "studijskiProgram.id")
    Modul toEntity(ModulDTO dto);
}
