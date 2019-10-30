package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProductRelatedService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProductRelatedDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductRelated}.
 */
@RestController
@RequestMapping("/api")
public class ProductRelatedResource {

    private final Logger log = LoggerFactory.getLogger(ProductRelatedResource.class);

    private static final String ENTITY_NAME = "productRelated";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductRelatedService productRelatedService;

    public ProductRelatedResource(ProductRelatedService productRelatedService) {
        this.productRelatedService = productRelatedService;
    }

    /**
     * {@code POST  /product-relateds} : Create a new productRelated.
     *
     * @param productRelatedDTO the productRelatedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productRelatedDTO, or with status {@code 400 (Bad Request)} if the productRelated has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-relateds")
    public ResponseEntity<ProductRelatedDTO> createProductRelated(@RequestBody ProductRelatedDTO productRelatedDTO) throws URISyntaxException {
        log.debug("REST request to save ProductRelated : {}", productRelatedDTO);
        if (productRelatedDTO.getId() != null) {
            throw new BadRequestAlertException("A new productRelated cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductRelatedDTO result = productRelatedService.save(productRelatedDTO);
        return ResponseEntity.created(new URI("/api/product-relateds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-relateds} : Updates an existing productRelated.
     *
     * @param productRelatedDTO the productRelatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productRelatedDTO,
     * or with status {@code 400 (Bad Request)} if the productRelatedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productRelatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-relateds")
    public ResponseEntity<ProductRelatedDTO> updateProductRelated(@RequestBody ProductRelatedDTO productRelatedDTO) throws URISyntaxException {
        log.debug("REST request to update ProductRelated : {}", productRelatedDTO);
        if (productRelatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductRelatedDTO result = productRelatedService.save(productRelatedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productRelatedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-relateds} : get all the productRelateds.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productRelateds in body.
     */
    @GetMapping("/product-relateds")
    public ResponseEntity<List<ProductRelatedDTO>> getAllProductRelateds(Pageable pageable) {
        log.debug("REST request to get a page of ProductRelateds");
        Page<ProductRelatedDTO> page = productRelatedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-relateds/:id} : get the "id" productRelated.
     *
     * @param id the id of the productRelatedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productRelatedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-relateds/{id}")
    public ResponseEntity<ProductRelatedDTO> getProductRelated(@PathVariable Long id) {
        log.debug("REST request to get ProductRelated : {}", id);
        Optional<ProductRelatedDTO> productRelatedDTO = productRelatedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productRelatedDTO);
    }

    /**
     * {@code DELETE  /product-relateds/:id} : delete the "id" productRelated.
     *
     * @param id the id of the productRelatedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-relateds/{id}")
    public ResponseEntity<Void> deleteProductRelated(@PathVariable Long id) {
        log.debug("REST request to delete ProductRelated : {}", id);
        productRelatedService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
