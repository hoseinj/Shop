package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderLog} and its DTO {@link OrderLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CurrencyMapper.class, OrderStatusMapper.class})
public interface OrderLogMapper extends EntityMapper<OrderLogDTO, OrderLog> {

    @Mapping(source = "customerId.id", target = "customerIdId")
    @Mapping(source = "currencyId.id", target = "currencyIdId")
    @Mapping(source = "orderStatusId.id", target = "orderStatusIdId")
    OrderLogDTO toDto(OrderLog orderLog);

    @Mapping(source = "customerIdId", target = "customerId")
    @Mapping(source = "currencyIdId", target = "currencyId")
    @Mapping(source = "orderStatusIdId", target = "orderStatusId")
    OrderLog toEntity(OrderLogDTO orderLogDTO);

    default OrderLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderLog orderLog = new OrderLog();
        orderLog.setId(id);
        return orderLog;
    }
}
