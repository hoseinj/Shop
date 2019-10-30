package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProductDiscountDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProductDiscount}.
 */
public interface ProductDiscountService {

    /**
     * Save a productDiscount.
     *
     * @param productDiscountDTO the entity to save.
     * @return the persisted entity.
     */
    ProductDiscountDTO save(ProductDiscountDTO productDiscountDTO);

    /**
     * Get all the productDiscounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductDiscountDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productDiscount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductDiscountDTO> findOne(Long id);

    /**
     * Delete the "id" productDiscount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
