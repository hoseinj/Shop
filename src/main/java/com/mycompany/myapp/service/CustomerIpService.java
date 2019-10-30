package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CustomerIpDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CustomerIp}.
 */
public interface CustomerIpService {

    /**
     * Save a customerIp.
     *
     * @param customerIpDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerIpDTO save(CustomerIpDTO customerIpDTO);

    /**
     * Get all the customerIps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerIpDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerIp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerIpDTO> findOne(Long id);

    /**
     * Delete the "id" customerIp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
