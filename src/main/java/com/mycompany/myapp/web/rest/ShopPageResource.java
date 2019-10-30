package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ShopPageService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ShopPageDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ShopPage}.
 */
@RestController
@RequestMapping("/api")
public class ShopPageResource {

    private final Logger log = LoggerFactory.getLogger(ShopPageResource.class);

    private static final String ENTITY_NAME = "shopPage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopPageService shopPageService;

    public ShopPageResource(ShopPageService shopPageService) {
        this.shopPageService = shopPageService;
    }

    /**
     * {@code POST  /shop-pages} : Create a new shopPage.
     *
     * @param shopPageDTO the shopPageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopPageDTO, or with status {@code 400 (Bad Request)} if the shopPage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shop-pages")
    public ResponseEntity<ShopPageDTO> createShopPage(@RequestBody ShopPageDTO shopPageDTO) throws URISyntaxException {
        log.debug("REST request to save ShopPage : {}", shopPageDTO);
        if (shopPageDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopPage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopPageDTO result = shopPageService.save(shopPageDTO);
        return ResponseEntity.created(new URI("/api/shop-pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shop-pages} : Updates an existing shopPage.
     *
     * @param shopPageDTO the shopPageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopPageDTO,
     * or with status {@code 400 (Bad Request)} if the shopPageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopPageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shop-pages")
    public ResponseEntity<ShopPageDTO> updateShopPage(@RequestBody ShopPageDTO shopPageDTO) throws URISyntaxException {
        log.debug("REST request to update ShopPage : {}", shopPageDTO);
        if (shopPageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopPageDTO result = shopPageService.save(shopPageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shopPageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shop-pages} : get all the shopPages.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopPages in body.
     */
    @GetMapping("/shop-pages")
    public ResponseEntity<List<ShopPageDTO>> getAllShopPages(Pageable pageable) {
        log.debug("REST request to get a page of ShopPages");
        Page<ShopPageDTO> page = shopPageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shop-pages/:id} : get the "id" shopPage.
     *
     * @param id the id of the shopPageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopPageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shop-pages/{id}")
    public ResponseEntity<ShopPageDTO> getShopPage(@PathVariable Long id) {
        log.debug("REST request to get ShopPage : {}", id);
        Optional<ShopPageDTO> shopPageDTO = shopPageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopPageDTO);
    }

    /**
     * {@code DELETE  /shop-pages/:id} : delete the "id" shopPage.
     *
     * @param id the id of the shopPageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shop-pages/{id}")
    public ResponseEntity<Void> deleteShopPage(@PathVariable Long id) {
        log.debug("REST request to delete ShopPage : {}", id);
        shopPageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
