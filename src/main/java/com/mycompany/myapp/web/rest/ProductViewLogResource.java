package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProductViewLogService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProductViewLogDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductViewLog}.
 */
@RestController
@RequestMapping("/api")
public class ProductViewLogResource {

    private final Logger log = LoggerFactory.getLogger(ProductViewLogResource.class);

    private static final String ENTITY_NAME = "productViewLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductViewLogService productViewLogService;

    public ProductViewLogResource(ProductViewLogService productViewLogService) {
        this.productViewLogService = productViewLogService;
    }

    /**
     * {@code POST  /product-view-logs} : Create a new productViewLog.
     *
     * @param productViewLogDTO the productViewLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productViewLogDTO, or with status {@code 400 (Bad Request)} if the productViewLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-view-logs")
    public ResponseEntity<ProductViewLogDTO> createProductViewLog(@RequestBody ProductViewLogDTO productViewLogDTO) throws URISyntaxException {
        log.debug("REST request to save ProductViewLog : {}", productViewLogDTO);
        if (productViewLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new productViewLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductViewLogDTO result = productViewLogService.save(productViewLogDTO);
        return ResponseEntity.created(new URI("/api/product-view-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-view-logs} : Updates an existing productViewLog.
     *
     * @param productViewLogDTO the productViewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productViewLogDTO,
     * or with status {@code 400 (Bad Request)} if the productViewLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productViewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-view-logs")
    public ResponseEntity<ProductViewLogDTO> updateProductViewLog(@RequestBody ProductViewLogDTO productViewLogDTO) throws URISyntaxException {
        log.debug("REST request to update ProductViewLog : {}", productViewLogDTO);
        if (productViewLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductViewLogDTO result = productViewLogService.save(productViewLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productViewLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-view-logs} : get all the productViewLogs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productViewLogs in body.
     */
    @GetMapping("/product-view-logs")
    public ResponseEntity<List<ProductViewLogDTO>> getAllProductViewLogs(Pageable pageable) {
        log.debug("REST request to get a page of ProductViewLogs");
        Page<ProductViewLogDTO> page = productViewLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-view-logs/:id} : get the "id" productViewLog.
     *
     * @param id the id of the productViewLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productViewLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-view-logs/{id}")
    public ResponseEntity<ProductViewLogDTO> getProductViewLog(@PathVariable Long id) {
        log.debug("REST request to get ProductViewLog : {}", id);
        Optional<ProductViewLogDTO> productViewLogDTO = productViewLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productViewLogDTO);
    }

    /**
     * {@code DELETE  /product-view-logs/:id} : delete the "id" productViewLog.
     *
     * @param id the id of the productViewLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-view-logs/{id}")
    public ResponseEntity<Void> deleteProductViewLog(@PathVariable Long id) {
        log.debug("REST request to delete ProductViewLog : {}", id);
        productViewLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
