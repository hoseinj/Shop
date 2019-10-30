package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CustomerGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CustomerGroup}.
 */
public interface CustomerGroupService {

    /**
     * Save a customerGroup.
     *
     * @param customerGroupDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerGroupDTO save(CustomerGroupDTO customerGroupDTO);

    /**
     * Get all the customerGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerGroupDTO> findOne(Long id);

    /**
     * Delete the "id" customerGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
