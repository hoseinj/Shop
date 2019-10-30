package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProductTagDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProductTag}.
 */
public interface ProductTagService {

    /**
     * Save a productTag.
     *
     * @param productTagDTO the entity to save.
     * @return the persisted entity.
     */
    ProductTagDTO save(ProductTagDTO productTagDTO);

    /**
     * Get all the productTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductTagDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductTagDTO> findOne(Long id);

    /**
     * Delete the "id" productTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
