package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.LoginLogService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.LoginLogDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.LoginLog}.
 */
@RestController
@RequestMapping("/api")
public class LoginLogResource {

    private final Logger log = LoggerFactory.getLogger(LoginLogResource.class);

    private static final String ENTITY_NAME = "loginLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoginLogService loginLogService;

    public LoginLogResource(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * {@code POST  /login-logs} : Create a new loginLog.
     *
     * @param loginLogDTO the loginLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loginLogDTO, or with status {@code 400 (Bad Request)} if the loginLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/login-logs")
    public ResponseEntity<LoginLogDTO> createLoginLog(@RequestBody LoginLogDTO loginLogDTO) throws URISyntaxException {
        log.debug("REST request to save LoginLog : {}", loginLogDTO);
        if (loginLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new loginLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoginLogDTO result = loginLogService.save(loginLogDTO);
        return ResponseEntity.created(new URI("/api/login-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /login-logs} : Updates an existing loginLog.
     *
     * @param loginLogDTO the loginLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loginLogDTO,
     * or with status {@code 400 (Bad Request)} if the loginLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loginLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/login-logs")
    public ResponseEntity<LoginLogDTO> updateLoginLog(@RequestBody LoginLogDTO loginLogDTO) throws URISyntaxException {
        log.debug("REST request to update LoginLog : {}", loginLogDTO);
        if (loginLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoginLogDTO result = loginLogService.save(loginLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loginLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /login-logs} : get all the loginLogs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loginLogs in body.
     */
    @GetMapping("/login-logs")
    public ResponseEntity<List<LoginLogDTO>> getAllLoginLogs(Pageable pageable) {
        log.debug("REST request to get a page of LoginLogs");
        Page<LoginLogDTO> page = loginLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /login-logs/:id} : get the "id" loginLog.
     *
     * @param id the id of the loginLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loginLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/login-logs/{id}")
    public ResponseEntity<LoginLogDTO> getLoginLog(@PathVariable Long id) {
        log.debug("REST request to get LoginLog : {}", id);
        Optional<LoginLogDTO> loginLogDTO = loginLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loginLogDTO);
    }

    /**
     * {@code DELETE  /login-logs/:id} : delete the "id" loginLog.
     *
     * @param id the id of the loginLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/login-logs/{id}")
    public ResponseEntity<Void> deleteLoginLog(@PathVariable Long id) {
        log.debug("REST request to delete LoginLog : {}", id);
        loginLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
