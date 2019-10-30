package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderStatus} and its DTO {@link OrderStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderStatusMapper extends EntityMapper<OrderStatusDTO, OrderStatus> {



    default OrderStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(id);
        return orderStatus;
    }
}
