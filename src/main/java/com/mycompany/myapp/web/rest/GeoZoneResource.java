package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.GeoZoneService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.GeoZoneDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.GeoZone}.
 */
@RestController
@RequestMapping("/api")
public class GeoZoneResource {

    private final Logger log = LoggerFactory.getLogger(GeoZoneResource.class);

    private static final String ENTITY_NAME = "geoZone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoZoneService geoZoneService;

    public GeoZoneResource(GeoZoneService geoZoneService) {
        this.geoZoneService = geoZoneService;
    }

    /**
     * {@code POST  /geo-zones} : Create a new geoZone.
     *
     * @param geoZoneDTO the geoZoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoZoneDTO, or with status {@code 400 (Bad Request)} if the geoZone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-zones")
    public ResponseEntity<GeoZoneDTO> createGeoZone(@RequestBody GeoZoneDTO geoZoneDTO) throws URISyntaxException {
        log.debug("REST request to save GeoZone : {}", geoZoneDTO);
        if (geoZoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoZone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoZoneDTO result = geoZoneService.save(geoZoneDTO);
        return ResponseEntity.created(new URI("/api/geo-zones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-zones} : Updates an existing geoZone.
     *
     * @param geoZoneDTO the geoZoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoZoneDTO,
     * or with status {@code 400 (Bad Request)} if the geoZoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoZoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-zones")
    public ResponseEntity<GeoZoneDTO> updateGeoZone(@RequestBody GeoZoneDTO geoZoneDTO) throws URISyntaxException {
        log.debug("REST request to update GeoZone : {}", geoZoneDTO);
        if (geoZoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoZoneDTO result = geoZoneService.save(geoZoneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoZoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-zones} : get all the geoZones.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoZones in body.
     */
    @GetMapping("/geo-zones")
    public ResponseEntity<List<GeoZoneDTO>> getAllGeoZones(Pageable pageable) {
        log.debug("REST request to get a page of GeoZones");
        Page<GeoZoneDTO> page = geoZoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-zones/:id} : get the "id" geoZone.
     *
     * @param id the id of the geoZoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoZoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-zones/{id}")
    public ResponseEntity<GeoZoneDTO> getGeoZone(@PathVariable Long id) {
        log.debug("REST request to get GeoZone : {}", id);
        Optional<GeoZoneDTO> geoZoneDTO = geoZoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoZoneDTO);
    }

    /**
     * {@code DELETE  /geo-zones/:id} : delete the "id" geoZone.
     *
     * @param id the id of the geoZoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-zones/{id}")
    public ResponseEntity<Void> deleteGeoZone(@PathVariable Long id) {
        log.debug("REST request to delete GeoZone : {}", id);
        geoZoneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
