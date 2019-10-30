package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PorductDescriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PorductDescriptionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.PorductDescription}.
 */
@RestController
@RequestMapping("/api")
public class PorductDescriptionResource {

    private final Logger log = LoggerFactory.getLogger(PorductDescriptionResource.class);

    private static final String ENTITY_NAME = "porductDescription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PorductDescriptionService porductDescriptionService;

    public PorductDescriptionResource(PorductDescriptionService porductDescriptionService) {
        this.porductDescriptionService = porductDescriptionService;
    }

    /**
     * {@code POST  /porduct-descriptions} : Create a new porductDescription.
     *
     * @param porductDescriptionDTO the porductDescriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new porductDescriptionDTO, or with status {@code 400 (Bad Request)} if the porductDescription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/porduct-descriptions")
    public ResponseEntity<PorductDescriptionDTO> createPorductDescription(@RequestBody PorductDescriptionDTO porductDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to save PorductDescription : {}", porductDescriptionDTO);
        if (porductDescriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new porductDescription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PorductDescriptionDTO result = porductDescriptionService.save(porductDescriptionDTO);
        return ResponseEntity.created(new URI("/api/porduct-descriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /porduct-descriptions} : Updates an existing porductDescription.
     *
     * @param porductDescriptionDTO the porductDescriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated porductDescriptionDTO,
     * or with status {@code 400 (Bad Request)} if the porductDescriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the porductDescriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/porduct-descriptions")
    public ResponseEntity<PorductDescriptionDTO> updatePorductDescription(@RequestBody PorductDescriptionDTO porductDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to update PorductDescription : {}", porductDescriptionDTO);
        if (porductDescriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PorductDescriptionDTO result = porductDescriptionService.save(porductDescriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, porductDescriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /porduct-descriptions} : get all the porductDescriptions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of porductDescriptions in body.
     */
    @GetMapping("/porduct-descriptions")
    public ResponseEntity<List<PorductDescriptionDTO>> getAllPorductDescriptions(Pageable pageable) {
        log.debug("REST request to get a page of PorductDescriptions");
        Page<PorductDescriptionDTO> page = porductDescriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /porduct-descriptions/:id} : get the "id" porductDescription.
     *
     * @param id the id of the porductDescriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the porductDescriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/porduct-descriptions/{id}")
    public ResponseEntity<PorductDescriptionDTO> getPorductDescription(@PathVariable Long id) {
        log.debug("REST request to get PorductDescription : {}", id);
        Optional<PorductDescriptionDTO> porductDescriptionDTO = porductDescriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(porductDescriptionDTO);
    }

    /**
     * {@code DELETE  /porduct-descriptions/:id} : delete the "id" porductDescription.
     *
     * @param id the id of the porductDescriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/porduct-descriptions/{id}")
    public ResponseEntity<Void> deletePorductDescription(@PathVariable Long id) {
        log.debug("REST request to delete PorductDescription : {}", id);
        porductDescriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
