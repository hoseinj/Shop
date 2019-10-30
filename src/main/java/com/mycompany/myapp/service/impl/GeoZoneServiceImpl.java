package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.GeoZoneService;
import com.mycompany.myapp.domain.GeoZone;
import com.mycompany.myapp.repository.GeoZoneRepository;
import com.mycompany.myapp.service.dto.GeoZoneDTO;
import com.mycompany.myapp.service.mapper.GeoZoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoZone}.
 */
@Service
@Transactional
public class GeoZoneServiceImpl implements GeoZoneService {

    private final Logger log = LoggerFactory.getLogger(GeoZoneServiceImpl.class);

    private final GeoZoneRepository geoZoneRepository;

    private final GeoZoneMapper geoZoneMapper;

    public GeoZoneServiceImpl(GeoZoneRepository geoZoneRepository, GeoZoneMapper geoZoneMapper) {
        this.geoZoneRepository = geoZoneRepository;
        this.geoZoneMapper = geoZoneMapper;
    }

    /**
     * Save a geoZone.
     *
     * @param geoZoneDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GeoZoneDTO save(GeoZoneDTO geoZoneDTO) {
        log.debug("Request to save GeoZone : {}", geoZoneDTO);
        GeoZone geoZone = geoZoneMapper.toEntity(geoZoneDTO);
        geoZone = geoZoneRepository.save(geoZone);
        return geoZoneMapper.toDto(geoZone);
    }

    /**
     * Get all the geoZones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GeoZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoZones");
        return geoZoneRepository.findAll(pageable)
            .map(geoZoneMapper::toDto);
    }


    /**
     * Get one geoZone by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeoZoneDTO> findOne(Long id) {
        log.debug("Request to get GeoZone : {}", id);
        return geoZoneRepository.findById(id)
            .map(geoZoneMapper::toDto);
    }

    /**
     * Delete the geoZone by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GeoZone : {}", id);
        geoZoneRepository.deleteById(id);
    }
}
