package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CustomerGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerGroup} and its DTO {@link CustomerGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerGroupMapper extends EntityMapper<CustomerGroupDTO, CustomerGroup> {



    default CustomerGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setId(id);
        return customerGroup;
    }
}
