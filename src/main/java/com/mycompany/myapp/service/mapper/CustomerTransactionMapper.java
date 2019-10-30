package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CustomerTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerTransaction} and its DTO {@link CustomerTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerTransactionMapper extends EntityMapper<CustomerTransactionDTO, CustomerTransaction> {



    default CustomerTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setId(id);
        return customerTransaction;
    }
}
