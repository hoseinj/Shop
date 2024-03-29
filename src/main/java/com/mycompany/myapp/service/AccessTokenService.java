package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AccessTokenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.AccessToken}.
 */
public interface AccessTokenService {

    /**
     * Save a accessToken.
     *
     * @param accessTokenDTO the entity to save.
     * @return the persisted entity.
     */
    AccessTokenDTO save(AccessTokenDTO accessTokenDTO);

    /**
     * Get all the accessTokens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccessTokenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accessToken.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccessTokenDTO> findOne(Long id);

    /**
     * Delete the "id" accessToken.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
