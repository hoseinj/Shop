package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProductViewLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductViewLog} and its DTO {@link ProductViewLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductViewLogMapper extends EntityMapper<ProductViewLogDTO, ProductViewLog> {

    @Mapping(source = "productId.id", target = "productIdId")
    ProductViewLogDTO toDto(ProductViewLog productViewLog);

    @Mapping(source = "productIdId", target = "productId")
    ProductViewLog toEntity(ProductViewLogDTO productViewLogDTO);

    default ProductViewLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductViewLog productViewLog = new ProductViewLog();
        productViewLog.setId(id);
        return productViewLog;
    }
}
