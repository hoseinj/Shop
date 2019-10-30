package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.OrderTotalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.OrderTotal}.
 */
public interface OrderTotalService {

    /**
     * Save a orderTotal.
     *
     * @param orderTotalDTO the entity to save.
     * @return the persisted entity.
     */
    OrderTotalDTO save(OrderTotalDTO orderTotalDTO);

    /**
     * Get all the orderTotals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderTotalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderTotal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderTotalDTO> findOne(Long id);

    /**
     * Delete the "id" orderTotal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
