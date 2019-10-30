package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderProduct} and its DTO {@link OrderProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface OrderProductMapper extends EntityMapper<OrderProductDTO, OrderProduct> {

    @Mapping(source = "productId.id", target = "productIdId")
    @Mapping(source = "orderId.id", target = "orderIdId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    OrderProductDTO toDto(OrderProduct orderProduct);

    @Mapping(source = "productIdId", target = "productId")
    @Mapping(source = "orderIdId", target = "orderId")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "orderId", target = "order")
    OrderProduct toEntity(OrderProductDTO orderProductDTO);

    default OrderProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(id);
        return orderProduct;
    }
}
