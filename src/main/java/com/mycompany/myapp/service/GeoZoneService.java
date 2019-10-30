package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GeoZoneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.GeoZone}.
 */
public interface GeoZoneService {

    /**
     * Save a geoZone.
     *
     * @param geoZoneDTO the entity to save.
     * @return the persisted entity.
     */
    GeoZoneDTO save(GeoZoneDTO geoZoneDTO);

    /**
     * Get all the geoZones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GeoZoneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" geoZone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeoZoneDTO> findOne(Long id);

    /**
     * Delete the "id" geoZone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
