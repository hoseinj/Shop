package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ManufacturerService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ManufacturerDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Manufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturerResource.class);

    private static final String ENTITY_NAME = "manufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufacturerService manufacturerService;

    public ManufacturerResource(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * {@code POST  /manufacturers} : Create a new manufacturer.
     *
     * @param manufacturerDTO the manufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufacturerDTO, or with status {@code 400 (Bad Request)} if the manufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufacturers")
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save Manufacturer : {}", manufacturerDTO);
        if (manufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new manufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManufacturerDTO result = manufacturerService.save(manufacturerDTO);
        return ResponseEntity.created(new URI("/api/manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufacturers} : Updates an existing manufacturer.
     *
     * @param manufacturerDTO the manufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the manufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufacturers")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update Manufacturer : {}", manufacturerDTO);
        if (manufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManufacturerDTO result = manufacturerService.save(manufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manufacturers} : get all the manufacturers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufacturers in body.
     */
    @GetMapping("/manufacturers")
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers(Pageable pageable) {
        log.debug("REST request to get a page of Manufacturers");
        Page<ManufacturerDTO> page = manufacturerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /manufacturers/:id} : get the "id" manufacturer.
     *
     * @param id the id of the manufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturer(@PathVariable Long id) {
        log.debug("REST request to get Manufacturer : {}", id);
        Optional<ManufacturerDTO> manufacturerDTO = manufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufacturerDTO);
    }

    /**
     * {@code DELETE  /manufacturers/:id} : delete the "id" manufacturer.
     *
     * @param id the id of the manufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete Manufacturer : {}", id);
        manufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
