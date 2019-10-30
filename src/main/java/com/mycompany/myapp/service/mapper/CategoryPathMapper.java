package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CategoryPathDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryPath} and its DTO {@link CategoryPathDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryPathMapper extends EntityMapper<CategoryPathDTO, CategoryPath> {

    @Mapping(source = "categoryId.id", target = "categoryIdId")
    CategoryPathDTO toDto(CategoryPath categoryPath);

    @Mapping(source = "categoryIdId", target = "categoryId")
    CategoryPath toEntity(CategoryPathDTO categoryPathDTO);

    default CategoryPath fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryPath categoryPath = new CategoryPath();
        categoryPath.setId(id);
        return categoryPath;
    }
}
