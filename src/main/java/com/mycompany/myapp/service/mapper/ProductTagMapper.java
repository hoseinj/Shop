package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductTagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductTag} and its DTO {@link ProductTagDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductTagMapper extends EntityMapper<ProductTagDTO, ProductTag> {



    default ProductTag fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductTag productTag = new ProductTag();
        productTag.setId(id);
        return productTag;
    }
}
