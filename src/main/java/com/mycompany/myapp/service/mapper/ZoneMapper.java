package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ZoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Zone} and its DTO {@link ZoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ZoneMapper extends EntityMapper<ZoneDTO, Zone> {

    @Mapping(source = "country.id", target = "countryId")
    ZoneDTO toDto(Zone zone);

    @Mapping(source = "countryId", target = "country")
    Zone toEntity(ZoneDTO zoneDTO);

    default Zone fromId(Long id) {
        if (id == null) {
            return null;
        }
        Zone zone = new Zone();
        zone.setId(id);
        return zone;
    }
}
