package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProductViewLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProductViewLog}.
 */
public interface ProductViewLogService {

    /**
     * Save a productViewLog.
     *
     * @param productViewLogDTO the entity to save.
     * @return the persisted entity.
     */
    ProductViewLogDTO save(ProductViewLogDTO productViewLogDTO);

    /**
     * Get all the productViewLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductViewLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productViewLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductViewLogDTO> findOne(Long id);

    /**
     * Delete the "id" productViewLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
