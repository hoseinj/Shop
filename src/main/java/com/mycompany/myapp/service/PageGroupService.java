package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PageGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.PageGroup}.
 */
public interface PageGroupService {

    /**
     * Save a pageGroup.
     *
     * @param pageGroupDTO the entity to save.
     * @return the persisted entity.
     */
    PageGroupDTO save(PageGroupDTO pageGroupDTO);

    /**
     * Get all the pageGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PageGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pageGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PageGroupDTO> findOne(Long id);

    /**
     * Delete the "id" pageGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
