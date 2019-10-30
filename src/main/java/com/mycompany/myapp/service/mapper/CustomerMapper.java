package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ZoneMapper.class, CustomerGroupMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "countryId.id", target = "countryIdId")
    @Mapping(source = "zoneId.id", target = "zoneIdId")
    @Mapping(source = "customerGroupId.id", target = "customerGroupIdId")
    CustomerDTO toDto(Customer customer);

    @Mapping(target = "productRatings", ignore = true)
    @Mapping(target = "removeProductRating", ignore = true)
    @Mapping(source = "countryIdId", target = "countryId")
    @Mapping(source = "zoneIdId", target = "zoneId")
    @Mapping(source = "customerGroupIdId", target = "customerGroupId")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
