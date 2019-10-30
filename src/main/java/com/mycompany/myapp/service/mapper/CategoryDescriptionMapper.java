package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CategoryDescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryDescription} and its DTO {@link CategoryDescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryDescriptionMapper extends EntityMapper<CategoryDescriptionDTO, CategoryDescription> {



    default CategoryDescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryDescription categoryDescription = new CategoryDescription();
        categoryDescription.setId(id);
        return categoryDescription;
    }
}
