package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PorductDescriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.PorductDescription}.
 */
public interface PorductDescriptionService {

    /**
     * Save a porductDescription.
     *
     * @param porductDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    PorductDescriptionDTO save(PorductDescriptionDTO porductDescriptionDTO);

    /**
     * Get all the porductDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PorductDescriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" porductDescription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PorductDescriptionDTO> findOne(Long id);

    /**
     * Delete the "id" porductDescription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
