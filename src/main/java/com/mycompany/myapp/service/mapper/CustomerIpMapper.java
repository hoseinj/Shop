package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CustomerIpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerIp} and its DTO {@link CustomerIpDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerIpMapper extends EntityMapper<CustomerIpDTO, CustomerIp> {



    default CustomerIp fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerIp customerIp = new CustomerIp();
        customerIp.setId(id);
        return customerIp;
    }
}
