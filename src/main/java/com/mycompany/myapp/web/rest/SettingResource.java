package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.SettingService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.SettingDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Setting}.
 */
@RestController
@RequestMapping("/api")
public class SettingResource {

    private final Logger log = LoggerFactory.getLogger(SettingResource.class);

    private static final String ENTITY_NAME = "setting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SettingService settingService;

    public SettingResource(SettingService settingService) {
        this.settingService = settingService;
    }

    /**
     * {@code POST  /settings} : Create a new setting.
     *
     * @param settingDTO the settingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new settingDTO, or with status {@code 400 (Bad Request)} if the setting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/settings")
    public ResponseEntity<SettingDTO> createSetting(@RequestBody SettingDTO settingDTO) throws URISyntaxException {
        log.debug("REST request to save Setting : {}", settingDTO);
        if (settingDTO.getId() != null) {
            throw new BadRequestAlertException("A new setting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SettingDTO result = settingService.save(settingDTO);
        return ResponseEntity.created(new URI("/api/settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /settings} : Updates an existing setting.
     *
     * @param settingDTO the settingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated settingDTO,
     * or with status {@code 400 (Bad Request)} if the settingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the settingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/settings")
    public ResponseEntity<SettingDTO> updateSetting(@RequestBody SettingDTO settingDTO) throws URISyntaxException {
        log.debug("REST request to update Setting : {}", settingDTO);
        if (settingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SettingDTO result = settingService.save(settingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, settingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /settings} : get all the settings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of settings in body.
     */
    @GetMapping("/settings")
    public ResponseEntity<List<SettingDTO>> getAllSettings(Pageable pageable) {
        log.debug("REST request to get a page of Settings");
        Page<SettingDTO> page = settingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /settings/:id} : get the "id" setting.
     *
     * @param id the id of the settingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the settingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/settings/{id}")
    public ResponseEntity<SettingDTO> getSetting(@PathVariable Long id) {
        log.debug("REST request to get Setting : {}", id);
        Optional<SettingDTO> settingDTO = settingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(settingDTO);
    }

    /**
     * {@code DELETE  /settings/:id} : delete the "id" setting.
     *
     * @param id the id of the settingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/settings/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        log.debug("REST request to delete Setting : {}", id);
        settingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
