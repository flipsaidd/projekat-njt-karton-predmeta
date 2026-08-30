package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.OblikNastaveDTO;
import com.fgc.kartonpredmeta.model.OblikNastave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OblikNastaveMapper {

     OblikNastaveDTO toDTO(OblikNastave entity);

     @Mapping(target="id" ,ignore=true)
     OblikNastave toEntity(OblikNastaveDTO dto);

     @Mapping(target = "id", ignore = true)
     void updateEntityfromDTO(OblikNastaveDTO dto, @MappingTarget OblikNastave entity);
}
