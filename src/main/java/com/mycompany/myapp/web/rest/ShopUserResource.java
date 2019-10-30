package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ShopUserService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ShopUserDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ShopUser}.
 */
@RestController
@RequestMapping("/api")
public class ShopUserResource {

    private final Logger log = LoggerFactory.getLogger(ShopUserResource.class);

    private static final String ENTITY_NAME = "shopUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopUserService shopUserService;

    public ShopUserResource(ShopUserService shopUserService) {
        this.shopUserService = shopUserService;
    }

    /**
     * {@code POST  /shop-users} : Create a new shopUser.
     *
     * @param shopUserDTO the shopUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopUserDTO, or with status {@code 400 (Bad Request)} if the shopUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shop-users")
    public ResponseEntity<ShopUserDTO> createShopUser(@RequestBody ShopUserDTO shopUserDTO) throws URISyntaxException {
        log.debug("REST request to save ShopUser : {}", shopUserDTO);
        if (shopUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopUserDTO result = shopUserService.save(shopUserDTO);
        return ResponseEntity.created(new URI("/api/shop-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shop-users} : Updates an existing shopUser.
     *
     * @param shopUserDTO the shopUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopUserDTO,
     * or with status {@code 400 (Bad Request)} if the shopUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shop-users")
    public ResponseEntity<ShopUserDTO> updateShopUser(@RequestBody ShopUserDTO shopUserDTO) throws URISyntaxException {
        log.debug("REST request to update ShopUser : {}", shopUserDTO);
        if (shopUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopUserDTO result = shopUserService.save(shopUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shopUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shop-users} : get all the shopUsers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopUsers in body.
     */
    @GetMapping("/shop-users")
    public ResponseEntity<List<ShopUserDTO>> getAllShopUsers(Pageable pageable) {
        log.debug("REST request to get a page of ShopUsers");
        Page<ShopUserDTO> page = shopUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shop-users/:id} : get the "id" shopUser.
     *
     * @param id the id of the shopUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shop-users/{id}")
    public ResponseEntity<ShopUserDTO> getShopUser(@PathVariable Long id) {
        log.debug("REST request to get ShopUser : {}", id);
        Optional<ShopUserDTO> shopUserDTO = shopUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopUserDTO);
    }

    /**
     * {@code DELETE  /shop-users/:id} : delete the "id" shopUser.
     *
     * @param id the id of the shopUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shop-users/{id}")
    public ResponseEntity<Void> deleteShopUser(@PathVariable Long id) {
        log.debug("REST request to delete ShopUser : {}", id);
        shopUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
