package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CustomerWishlistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerWishlist} and its DTO {@link CustomerWishlistDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface CustomerWishlistMapper extends EntityMapper<CustomerWishlistDTO, CustomerWishlist> {

    @Mapping(source = "customerId.id", target = "customerIdId")
    @Mapping(source = "productId.id", target = "productIdId")
    @Mapping(source = "product.id", target = "productId")
    CustomerWishlistDTO toDto(CustomerWishlist customerWishlist);

    @Mapping(source = "customerIdId", target = "customerId")
    @Mapping(source = "productIdId", target = "productId")
    @Mapping(source = "productId", target = "product")
    CustomerWishlist toEntity(CustomerWishlistDTO customerWishlistDTO);

    default CustomerWishlist fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerWishlist customerWishlist = new CustomerWishlist();
        customerWishlist.setId(id);
        return customerWishlist;
    }
}
