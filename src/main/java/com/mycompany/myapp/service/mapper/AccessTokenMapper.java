package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AccessTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccessToken} and its DTO {@link AccessTokenDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopUserMapper.class})
public interface AccessTokenMapper extends EntityMapper<AccessTokenDTO, AccessToken> {

    @Mapping(source = "shopUser.id", target = "shopUserId")
    @Mapping(source = "shopUserId.id", target = "shopUserIdId")
    AccessTokenDTO toDto(AccessToken accessToken);

    @Mapping(source = "shopUserId", target = "shopUser")
    @Mapping(source = "shopUserIdId", target = "shopUserId")
    AccessToken toEntity(AccessTokenDTO accessTokenDTO);

    default AccessToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccessToken accessToken = new AccessToken();
        accessToken.setId(id);
        return accessToken;
    }
}
