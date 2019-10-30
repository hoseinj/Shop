package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.StockStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.StockStatus}.
 */
public interface StockStatusService {

    /**
     * Save a stockStatus.
     *
     * @param stockStatusDTO the entity to save.
     * @return the persisted entity.
     */
    StockStatusDTO save(StockStatusDTO stockStatusDTO);

    /**
     * Get all the stockStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockStatusDTO> findOne(Long id);

    /**
     * Delete the "id" stockStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
