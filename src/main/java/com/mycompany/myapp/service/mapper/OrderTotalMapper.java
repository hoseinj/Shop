package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderTotalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderTotal} and its DTO {@link OrderTotalDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface OrderTotalMapper extends EntityMapper<OrderTotalDTO, OrderTotal> {

    @Mapping(source = "orderId.id", target = "orderIdId")
    OrderTotalDTO toDto(OrderTotal orderTotal);

    @Mapping(source = "orderIdId", target = "orderId")
    OrderTotal toEntity(OrderTotalDTO orderTotalDTO);

    default OrderTotal fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderTotal orderTotal = new OrderTotal();
        orderTotal.setId(id);
        return orderTotal;
    }
}
