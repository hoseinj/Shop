package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CustomerWishlistDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CustomerWishlist}.
 */
public interface CustomerWishlistService {

    /**
     * Save a customerWishlist.
     *
     * @param customerWishlistDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerWishlistDTO save(CustomerWishlistDTO customerWishlistDTO);

    /**
     * Get all the customerWishlists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerWishlistDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerWishlist.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerWishlistDTO> findOne(Long id);

    /**
     * Delete the "id" customerWishlist.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
