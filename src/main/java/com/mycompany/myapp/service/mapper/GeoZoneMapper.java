package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.GeoZoneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoZone} and its DTO {@link GeoZoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoZoneMapper extends EntityMapper<GeoZoneDTO, GeoZone> {



    default GeoZone fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoZone geoZone = new GeoZone();
        geoZone.setId(id);
        return geoZone;
    }
}
