package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ZoneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Zone}.
 */
public interface ZoneService {

    /**
     * Save a zone.
     *
     * @param zoneDTO the entity to save.
     * @return the persisted entity.
     */
    ZoneDTO save(ZoneDTO zoneDTO);

    /**
     * Get all the zones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ZoneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ZoneDTO> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
