package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProductRatingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProductRating}.
 */
public interface ProductRatingService {

    /**
     * Save a productRating.
     *
     * @param productRatingDTO the entity to save.
     * @return the persisted entity.
     */
    ProductRatingDTO save(ProductRatingDTO productRatingDTO);

    /**
     * Get all the productRatings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductRatingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productRating.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductRatingDTO> findOne(Long id);

    /**
     * Delete the "id" productRating.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
