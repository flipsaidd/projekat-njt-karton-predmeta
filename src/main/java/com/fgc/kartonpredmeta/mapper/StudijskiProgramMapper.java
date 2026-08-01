package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.StudijskiProgramDTO;
import com.fgc.kartonpredmeta.model.StudijskiProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses={ModulMapper.class})
public interface StudijskiProgramMapper {

    StudijskiProgramDTO toDTO(StudijskiProgram entitet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "moduli", ignore = true)
    StudijskiProgram toEntity(StudijskiProgramDTO dto);
}
