package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderOption} and its DTO {@link OrderOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderOptionMapper extends EntityMapper<OrderOptionDTO, OrderOption> {



    default OrderOption fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderOption orderOption = new OrderOption();
        orderOption.setId(id);
        return orderOption;
    }
}
