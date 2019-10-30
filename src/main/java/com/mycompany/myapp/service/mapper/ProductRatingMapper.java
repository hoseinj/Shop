package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductRatingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductRating} and its DTO {@link ProductRatingDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, CustomerMapper.class})
public interface ProductRatingMapper extends EntityMapper<ProductRatingDTO, ProductRating> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "productId.id", target = "productIdId")
    @Mapping(source = "customerId.id", target = "customerIdId")
    @Mapping(source = "customer.id", target = "customerId")
    ProductRatingDTO toDto(ProductRating productRating);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "productIdId", target = "productId")
    @Mapping(source = "customerIdId", target = "customerId")
    @Mapping(source = "customerId", target = "customer")
    ProductRating toEntity(ProductRatingDTO productRatingDTO);

    default ProductRating fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductRating productRating = new ProductRating();
        productRating.setId(id);
        return productRating;
    }
}
