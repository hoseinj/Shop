package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductDiscountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductDiscount} and its DTO {@link ProductDiscountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductDiscountMapper extends EntityMapper<ProductDiscountDTO, ProductDiscount> {



    default ProductDiscount fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductDiscount productDiscount = new ProductDiscount();
        productDiscount.setId(id);
        return productDiscount;
    }
}
