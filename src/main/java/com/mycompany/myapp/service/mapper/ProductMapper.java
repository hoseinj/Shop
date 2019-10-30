package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {StockStatusMapper.class, ManufacturerMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "stockStatusid.id", target = "stockStatusidId")
    @Mapping(source = "manufacturerId.id", target = "manufacturerIdId")
    ProductDTO toDto(Product product);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "removeCategories", ignore = true)
    @Mapping(target = "productImages", ignore = true)
    @Mapping(target = "removeProductImages", ignore = true)
    @Mapping(target = "wishLists", ignore = true)
    @Mapping(target = "removeWishLists", ignore = true)
    @Mapping(target = "productRelatedS", ignore = true)
    @Mapping(target = "removeProductRelatedS", ignore = true)
    @Mapping(target = "productRatings", ignore = true)
    @Mapping(target = "removeProductRatings", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "removeOrderProducts", ignore = true)
    @Mapping(source = "stockStatusidId", target = "stockStatusid")
    @Mapping(source = "manufacturerIdId", target = "manufacturerId")
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
