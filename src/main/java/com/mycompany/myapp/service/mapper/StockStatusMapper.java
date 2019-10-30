package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.StockStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockStatus} and its DTO {@link StockStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockStatusMapper extends EntityMapper<StockStatusDTO, StockStatus> {



    default StockStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockStatus stockStatus = new StockStatus();
        stockStatus.setId(id);
        return stockStatus;
    }
}
