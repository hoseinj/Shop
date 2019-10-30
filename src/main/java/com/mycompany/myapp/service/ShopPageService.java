package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ShopPageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ShopPage}.
 */
public interface ShopPageService {

    /**
     * Save a shopPage.
     *
     * @param shopPageDTO the entity to save.
     * @return the persisted entity.
     */
    ShopPageDTO save(ShopPageDTO shopPageDTO);

    /**
     * Get all the shopPages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShopPageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shopPage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShopPageDTO> findOne(Long id);

    /**
     * Delete the "id" shopPage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
