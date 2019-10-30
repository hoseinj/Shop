package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BannerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Banner} and its DTO {@link BannerDTO}.
 */
@Mapper(componentModel = "spring", uses = {BannerGroupMapper.class})
public interface BannerMapper extends EntityMapper<BannerDTO, Banner> {

    @Mapping(source = "bannerGroupId.id", target = "bannerGroupIdId")
    BannerDTO toDto(Banner banner);

    @Mapping(source = "bannerGroupIdId", target = "bannerGroupId")
    Banner toEntity(BannerDTO bannerDTO);

    default Banner fromId(Long id) {
        if (id == null) {
            return null;
        }
        Banner banner = new Banner();
        banner.setId(id);
        return banner;
    }
}
