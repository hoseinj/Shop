package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CategoryDescriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CategoryDescription}.
 */
public interface CategoryDescriptionService {

    /**
     * Save a categoryDescription.
     *
     * @param categoryDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryDescriptionDTO save(CategoryDescriptionDTO categoryDescriptionDTO);

    /**
     * Get all the categoryDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategoryDescriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categoryDescription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryDescriptionDTO> findOne(Long id);

    /**
     * Delete the "id" categoryDescription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
