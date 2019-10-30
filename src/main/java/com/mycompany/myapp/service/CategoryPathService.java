package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CategoryPathDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CategoryPath}.
 */
public interface CategoryPathService {

    /**
     * Save a categoryPath.
     *
     * @param categoryPathDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryPathDTO save(CategoryPathDTO categoryPathDTO);

    /**
     * Get all the categoryPaths.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategoryPathDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categoryPath.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryPathDTO> findOne(Long id);

    /**
     * Delete the "id" categoryPath.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
