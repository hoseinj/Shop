package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.OrderOptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.OrderOption}.
 */
public interface OrderOptionService {

    /**
     * Save a orderOption.
     *
     * @param orderOptionDTO the entity to save.
     * @return the persisted entity.
     */
    OrderOptionDTO save(OrderOptionDTO orderOptionDTO);

    /**
     * Get all the orderOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderOptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderOption.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderOptionDTO> findOne(Long id);

    /**
     * Delete the "id" orderOption.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
