package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PageGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PageGroup} and its DTO {@link PageGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PageGroupMapper extends EntityMapper<PageGroupDTO, PageGroup> {



    default PageGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        PageGroup pageGroup = new PageGroup();
        pageGroup.setId(id);
        return pageGroup;
    }
}
