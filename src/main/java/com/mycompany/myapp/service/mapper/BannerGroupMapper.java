package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BannerGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BannerGroup} and its DTO {@link BannerGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BannerGroupMapper extends EntityMapper<BannerGroupDTO, BannerGroup> {



    default BannerGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        BannerGroup bannerGroup = new BannerGroup();
        bannerGroup.setId(id);
        return bannerGroup;
    }
}
