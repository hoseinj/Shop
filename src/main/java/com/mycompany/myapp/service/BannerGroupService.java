package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BannerGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.BannerGroup}.
 */
public interface BannerGroupService {

    /**
     * Save a bannerGroup.
     *
     * @param bannerGroupDTO the entity to save.
     * @return the persisted entity.
     */
    BannerGroupDTO save(BannerGroupDTO bannerGroupDTO);

    /**
     * Get all the bannerGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BannerGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bannerGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BannerGroupDTO> findOne(Long id);

    /**
     * Delete the "id" bannerGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
