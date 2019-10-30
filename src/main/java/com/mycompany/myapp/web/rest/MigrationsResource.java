package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.MigrationsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.MigrationsDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Migrations}.
 */
@RestController
@RequestMapping("/api")
public class MigrationsResource {

    private final Logger log = LoggerFactory.getLogger(MigrationsResource.class);

    private static final String ENTITY_NAME = "migrations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MigrationsService migrationsService;

    public MigrationsResource(MigrationsService migrationsService) {
        this.migrationsService = migrationsService;
    }

    /**
     * {@code POST  /migrations} : Create a new migrations.
     *
     * @param migrationsDTO the migrationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new migrationsDTO, or with status {@code 400 (Bad Request)} if the migrations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/migrations")
    public ResponseEntity<MigrationsDTO> createMigrations(@RequestBody MigrationsDTO migrationsDTO) throws URISyntaxException {
        log.debug("REST request to save Migrations : {}", migrationsDTO);
        if (migrationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new migrations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MigrationsDTO result = migrationsService.save(migrationsDTO);
        return ResponseEntity.created(new URI("/api/migrations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /migrations} : Updates an existing migrations.
     *
     * @param migrationsDTO the migrationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated migrationsDTO,
     * or with status {@code 400 (Bad Request)} if the migrationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the migrationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/migrations")
    public ResponseEntity<MigrationsDTO> updateMigrations(@RequestBody MigrationsDTO migrationsDTO) throws URISyntaxException {
        log.debug("REST request to update Migrations : {}", migrationsDTO);
        if (migrationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MigrationsDTO result = migrationsService.save(migrationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, migrationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /migrations} : get all the migrations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of migrations in body.
     */
    @GetMapping("/migrations")
    public ResponseEntity<List<MigrationsDTO>> getAllMigrations(Pageable pageable) {
        log.debug("REST request to get a page of Migrations");
        Page<MigrationsDTO> page = migrationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /migrations/:id} : get the "id" migrations.
     *
     * @param id the id of the migrationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the migrationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/migrations/{id}")
    public ResponseEntity<MigrationsDTO> getMigrations(@PathVariable Long id) {
        log.debug("REST request to get Migrations : {}", id);
        Optional<MigrationsDTO> migrationsDTO = migrationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(migrationsDTO);
    }

    /**
     * {@code DELETE  /migrations/:id} : delete the "id" migrations.
     *
     * @param id the id of the migrationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/migrations/{id}")
    public ResponseEntity<Void> deleteMigrations(@PathVariable Long id) {
        log.debug("REST request to delete Migrations : {}", id);
        migrationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
