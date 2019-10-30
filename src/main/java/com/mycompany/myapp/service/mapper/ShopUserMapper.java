package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ShopUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopUser} and its DTO {@link ShopUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface ShopUserMapper extends EntityMapper<ShopUserDTO, ShopUser> {

    @Mapping(source = "userGroupId.id", target = "userGroupIdId")
    @Mapping(source = "userGroup.id", target = "userGroupId")
    ShopUserDTO toDto(ShopUser shopUser);

    @Mapping(target = "accessTokens", ignore = true)
    @Mapping(target = "removeAccessToken", ignore = true)
    @Mapping(source = "userGroupIdId", target = "userGroupId")
    @Mapping(source = "userGroupId", target = "userGroup")
    ShopUser toEntity(ShopUserDTO shopUserDTO);

    default ShopUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopUser shopUser = new ShopUser();
        shopUser.setId(id);
        return shopUser;
    }
}
