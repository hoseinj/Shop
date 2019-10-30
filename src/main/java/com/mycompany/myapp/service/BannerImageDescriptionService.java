package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BannerImageDescriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.BannerImageDescription}.
 */
public interface BannerImageDescriptionService {

    /**
     * Save a bannerImageDescription.
     *
     * @param bannerImageDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    BannerImageDescriptionDTO save(BannerImageDescriptionDTO bannerImageDescriptionDTO);

    /**
     * Get all the bannerImageDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BannerImageDescriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bannerImageDescription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BannerImageDescriptionDTO> findOne(Long id);

    /**
     * Delete the "id" bannerImageDescription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
