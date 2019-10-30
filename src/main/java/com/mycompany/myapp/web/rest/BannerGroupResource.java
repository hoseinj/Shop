package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.BannerGroupService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.BannerGroupDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.BannerGroup}.
 */
@RestController
@RequestMapping("/api")
public class BannerGroupResource {

    private final Logger log = LoggerFactory.getLogger(BannerGroupResource.class);

    private static final String ENTITY_NAME = "bannerGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BannerGroupService bannerGroupService;

    public BannerGroupResource(BannerGroupService bannerGroupService) {
        this.bannerGroupService = bannerGroupService;
    }

    /**
     * {@code POST  /banner-groups} : Create a new bannerGroup.
     *
     * @param bannerGroupDTO the bannerGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bannerGroupDTO, or with status {@code 400 (Bad Request)} if the bannerGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banner-groups")
    public ResponseEntity<BannerGroupDTO> createBannerGroup(@RequestBody BannerGroupDTO bannerGroupDTO) throws URISyntaxException {
        log.debug("REST request to save BannerGroup : {}", bannerGroupDTO);
        if (bannerGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new bannerGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BannerGroupDTO result = bannerGroupService.save(bannerGroupDTO);
        return ResponseEntity.created(new URI("/api/banner-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /banner-groups} : Updates an existing bannerGroup.
     *
     * @param bannerGroupDTO the bannerGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bannerGroupDTO,
     * or with status {@code 400 (Bad Request)} if the bannerGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bannerGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banner-groups")
    public ResponseEntity<BannerGroupDTO> updateBannerGroup(@RequestBody BannerGroupDTO bannerGroupDTO) throws URISyntaxException {
        log.debug("REST request to update BannerGroup : {}", bannerGroupDTO);
        if (bannerGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BannerGroupDTO result = bannerGroupService.save(bannerGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bannerGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /banner-groups} : get all the bannerGroups.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bannerGroups in body.
     */
    @GetMapping("/banner-groups")
    public ResponseEntity<List<BannerGroupDTO>> getAllBannerGroups(Pageable pageable) {
        log.debug("REST request to get a page of BannerGroups");
        Page<BannerGroupDTO> page = bannerGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /banner-groups/:id} : get the "id" bannerGroup.
     *
     * @param id the id of the bannerGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bannerGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banner-groups/{id}")
    public ResponseEntity<BannerGroupDTO> getBannerGroup(@PathVariable Long id) {
        log.debug("REST request to get BannerGroup : {}", id);
        Optional<BannerGroupDTO> bannerGroupDTO = bannerGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bannerGroupDTO);
    }

    /**
     * {@code DELETE  /banner-groups/:id} : delete the "id" bannerGroup.
     *
     * @param id the id of the bannerGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banner-groups/{id}")
    public ResponseEntity<Void> deleteBannerGroup(@PathVariable Long id) {
        log.debug("REST request to delete BannerGroup : {}", id);
        bannerGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
