package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ShopPageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopPage} and its DTO {@link ShopPageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShopPageMapper extends EntityMapper<ShopPageDTO, ShopPage> {



    default ShopPage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopPage shopPage = new ShopPage();
        shopPage.setId(id);
        return shopPage;
    }
}
