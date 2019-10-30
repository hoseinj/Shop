package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductRelatedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductRelated} and its DTO {@link ProductRelatedDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductRelatedMapper extends EntityMapper<ProductRelatedDTO, ProductRelated> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "productId.id", target = "productIdId")
    ProductRelatedDTO toDto(ProductRelated productRelated);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "productIdId", target = "productId")
    ProductRelated toEntity(ProductRelatedDTO productRelatedDTO);

    default ProductRelated fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductRelated productRelated = new ProductRelated();
        productRelated.setId(id);
        return productRelated;
    }
}
