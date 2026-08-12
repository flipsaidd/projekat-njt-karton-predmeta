package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.PredmetRequestDTO;
import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.model.Predmet;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses={PredmetModulMapper.class, AngazovanjeMapper.class, PredmetnaObavezaMapper.class, PredmetLiteraturaMapper.class})
public interface PredmetMapper {

    PredmetResponseDTO toResponseDTO(Predmet entitet);

    @Mapping(target = "id", ignore = true)
    Predmet toEntity(PredmetRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(PredmetRequestDTO dto, @MappingTarget Predmet entity);

    @AfterMapping
    default void uvezivanjeDece(@MappingTarget Predmet predmet) {
        if (predmet.getObaveze() != null) {
            predmet.getObaveze().forEach(o -> o.setPredmet(predmet));
        }
        if (predmet.getAngazovanja() != null) {
            predmet.getAngazovanja().forEach(a -> a.setPredmet(predmet));
        }
        if (predmet.getModuli() != null) {
            predmet.getModuli().forEach(m -> m.setPredmet(predmet));
        }
        if (predmet.getLiterature() != null) {
            predmet.getLiterature().forEach(l -> l.setPredmet(predmet));
        }
    }
}
