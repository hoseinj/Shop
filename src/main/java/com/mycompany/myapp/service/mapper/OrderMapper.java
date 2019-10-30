package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CurrencyMapper.class, OrderStatusMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    @Mapping(source = "customerId.id", target = "customerIdId")
    @Mapping(source = "currencyId.id", target = "currencyIdId")
    @Mapping(source = "orderStatusId.id", target = "orderStatusIdId")
    OrderDTO toDto(Order order);

    @Mapping(target = "porductLists", ignore = true)
    @Mapping(target = "removePorductLists", ignore = true)
    @Mapping(source = "customerIdId", target = "customerId")
    @Mapping(source = "currencyIdId", target = "currencyId")
    @Mapping(source = "orderStatusIdId", target = "orderStatusId")
    Order toEntity(OrderDTO orderDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
