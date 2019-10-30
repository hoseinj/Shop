package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProductRatingService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProductRatingDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductRating}.
 */
@RestController
@RequestMapping("/api")
public class ProductRatingResource {

    private final Logger log = LoggerFactory.getLogger(ProductRatingResource.class);

    private static final String ENTITY_NAME = "productRating";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductRatingService productRatingService;

    public ProductRatingResource(ProductRatingService productRatingService) {
        this.productRatingService = productRatingService;
    }

    /**
     * {@code POST  /product-ratings} : Create a new productRating.
     *
     * @param productRatingDTO the productRatingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productRatingDTO, or with status {@code 400 (Bad Request)} if the productRating has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-ratings")
    public ResponseEntity<ProductRatingDTO> createProductRating(@RequestBody ProductRatingDTO productRatingDTO) throws URISyntaxException {
        log.debug("REST request to save ProductRating : {}", productRatingDTO);
        if (productRatingDTO.getId() != null) {
            throw new BadRequestAlertException("A new productRating cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductRatingDTO result = productRatingService.save(productRatingDTO);
        return ResponseEntity.created(new URI("/api/product-ratings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-ratings} : Updates an existing productRating.
     *
     * @param productRatingDTO the productRatingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productRatingDTO,
     * or with status {@code 400 (Bad Request)} if the productRatingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productRatingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-ratings")
    public ResponseEntity<ProductRatingDTO> updateProductRating(@RequestBody ProductRatingDTO productRatingDTO) throws URISyntaxException {
        log.debug("REST request to update ProductRating : {}", productRatingDTO);
        if (productRatingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductRatingDTO result = productRatingService.save(productRatingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productRatingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-ratings} : get all the productRatings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productRatings in body.
     */
    @GetMapping("/product-ratings")
    public ResponseEntity<List<ProductRatingDTO>> getAllProductRatings(Pageable pageable) {
        log.debug("REST request to get a page of ProductRatings");
        Page<ProductRatingDTO> page = productRatingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-ratings/:id} : get the "id" productRating.
     *
     * @param id the id of the productRatingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productRatingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-ratings/{id}")
    public ResponseEntity<ProductRatingDTO> getProductRating(@PathVariable Long id) {
        log.debug("REST request to get ProductRating : {}", id);
        Optional<ProductRatingDTO> productRatingDTO = productRatingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productRatingDTO);
    }

    /**
     * {@code DELETE  /product-ratings/:id} : delete the "id" productRating.
     *
     * @param id the id of the productRatingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-ratings/{id}")
    public ResponseEntity<Void> deleteProductRating(@PathVariable Long id) {
        log.debug("REST request to delete ProductRating : {}", id);
        productRatingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
