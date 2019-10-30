package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderHistory} and its DTO {@link OrderHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderHistoryMapper extends EntityMapper<OrderHistoryDTO, OrderHistory> {



    default OrderHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(id);
        return orderHistory;
    }
}
