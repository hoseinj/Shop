package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProductRelatedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProductRelated}.
 */
public interface ProductRelatedService {

    /**
     * Save a productRelated.
     *
     * @param productRelatedDTO the entity to save.
     * @return the persisted entity.
     */
    ProductRelatedDTO save(ProductRelatedDTO productRelatedDTO);

    /**
     * Get all the productRelateds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductRelatedDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productRelated.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductRelatedDTO> findOne(Long id);

    /**
     * Delete the "id" productRelated.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
