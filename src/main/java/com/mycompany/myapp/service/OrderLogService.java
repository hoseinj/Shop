package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.OrderLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.OrderLog}.
 */
public interface OrderLogService {

    /**
     * Save a orderLog.
     *
     * @param orderLogDTO the entity to save.
     * @return the persisted entity.
     */
    OrderLogDTO save(OrderLogDTO orderLogDTO);

    /**
     * Get all the orderLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderLogDTO> findOne(Long id);

    /**
     * Delete the "id" orderLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
