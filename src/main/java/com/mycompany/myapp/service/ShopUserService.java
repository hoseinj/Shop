package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ShopUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ShopUser}.
 */
public interface ShopUserService {

    /**
     * Save a shopUser.
     *
     * @param shopUserDTO the entity to save.
     * @return the persisted entity.
     */
    ShopUserDTO save(ShopUserDTO shopUserDTO);

    /**
     * Get all the shopUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShopUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shopUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShopUserDTO> findOne(Long id);

    /**
     * Delete the "id" shopUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
