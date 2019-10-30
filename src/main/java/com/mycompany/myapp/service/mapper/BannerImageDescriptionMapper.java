package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BannerImageDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BannerImageDescription} and its DTO {@link BannerImageDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BannerImageDescriptionMapper extends EntityMapper<BannerImageDescriptionDTO, BannerImageDescription> {



    default BannerImageDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        BannerImageDescription bannerImageDescription = new BannerImageDescription();
        bannerImageDescription.setId(id);
        return bannerImageDescription;
    }
}
