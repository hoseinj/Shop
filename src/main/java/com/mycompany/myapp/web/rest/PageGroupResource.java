package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PageGroupService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PageGroupDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.PageGroup}.
 */
@RestController
@RequestMapping("/api")
public class PageGroupResource {

    private final Logger log = LoggerFactory.getLogger(PageGroupResource.class);

    private static final String ENTITY_NAME = "pageGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PageGroupService pageGroupService;

    public PageGroupResource(PageGroupService pageGroupService) {
        this.pageGroupService = pageGroupService;
    }

    /**
     * {@code POST  /page-groups} : Create a new pageGroup.
     *
     * @param pageGroupDTO the pageGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pageGroupDTO, or with status {@code 400 (Bad Request)} if the pageGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/page-groups")
    public ResponseEntity<PageGroupDTO> createPageGroup(@RequestBody PageGroupDTO pageGroupDTO) throws URISyntaxException {
        log.debug("REST request to save PageGroup : {}", pageGroupDTO);
        if (pageGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new pageGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PageGroupDTO result = pageGroupService.save(pageGroupDTO);
        return ResponseEntity.created(new URI("/api/page-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /page-groups} : Updates an existing pageGroup.
     *
     * @param pageGroupDTO the pageGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageGroupDTO,
     * or with status {@code 400 (Bad Request)} if the pageGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pageGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/page-groups")
    public ResponseEntity<PageGroupDTO> updatePageGroup(@RequestBody PageGroupDTO pageGroupDTO) throws URISyntaxException {
        log.debug("REST request to update PageGroup : {}", pageGroupDTO);
        if (pageGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PageGroupDTO result = pageGroupService.save(pageGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pageGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /page-groups} : get all the pageGroups.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pageGroups in body.
     */
    @GetMapping("/page-groups")
    public ResponseEntity<List<PageGroupDTO>> getAllPageGroups(Pageable pageable) {
        log.debug("REST request to get a page of PageGroups");
        Page<PageGroupDTO> page = pageGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /page-groups/:id} : get the "id" pageGroup.
     *
     * @param id the id of the pageGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pageGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/page-groups/{id}")
    public ResponseEntity<PageGroupDTO> getPageGroup(@PathVariable Long id) {
        log.debug("REST request to get PageGroup : {}", id);
        Optional<PageGroupDTO> pageGroupDTO = pageGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pageGroupDTO);
    }

    /**
     * {@code DELETE  /page-groups/:id} : delete the "id" pageGroup.
     *
     * @param id the id of the pageGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/page-groups/{id}")
    public ResponseEntity<Void> deletePageGroup(@PathVariable Long id) {
        log.debug("REST request to delete PageGroup : {}", id);
        pageGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
