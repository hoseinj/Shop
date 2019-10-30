package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PorductDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PorductDescription} and its DTO {@link PorductDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PorductDescriptionMapper extends EntityMapper<PorductDescriptionDTO, PorductDescription> {



    default PorductDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        PorductDescription porductDescription = new PorductDescription();
        porductDescription.setId(id);
        return porductDescription;
    }
}
