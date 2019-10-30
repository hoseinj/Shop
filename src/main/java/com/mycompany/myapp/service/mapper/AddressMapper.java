package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CountryMapper.class, ZoneMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "customerId.id", target = "customerIdId")
    @Mapping(source = "countryId.id", target = "countryIdId")
    @Mapping(source = "zoneId.id", target = "zoneIdId")
    AddressDTO toDto(Address address);

    @Mapping(source = "customerIdId", target = "customerId")
    @Mapping(source = "countryIdId", target = "countryId")
    @Mapping(source = "zoneIdId", target = "zoneId")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
