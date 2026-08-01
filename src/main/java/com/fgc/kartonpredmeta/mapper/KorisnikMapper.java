package com.fgc.kartonpredmeta.mapper;

import com.fgc.kartonpredmeta.dto.KorisnikDTO;
import com.fgc.kartonpredmeta.model.Korisnik;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {

    KorisnikDTO toDTO(Korisnik entitet);

    @Mapping(target = "password", ignore = true)
    Korisnik toEntity(KorisnikDTO dto);
}
