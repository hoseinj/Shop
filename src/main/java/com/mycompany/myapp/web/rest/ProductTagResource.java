package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProductTagService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProductTagDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductTag}.
 */
@RestController
@RequestMapping("/api")
public class ProductTagResource {

    private final Logger log = LoggerFactory.getLogger(ProductTagResource.class);

    private static final String ENTITY_NAME = "productTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductTagService productTagService;

    public ProductTagResource(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    /**
     * {@code POST  /product-tags} : Create a new productTag.
     *
     * @param productTagDTO the productTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productTagDTO, or with status {@code 400 (Bad Request)} if the productTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-tags")
    public ResponseEntity<ProductTagDTO> createProductTag(@RequestBody ProductTagDTO productTagDTO) throws URISyntaxException {
        log.debug("REST request to save ProductTag : {}", productTagDTO);
        if (productTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new productTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductTagDTO result = productTagService.save(productTagDTO);
        return ResponseEntity.created(new URI("/api/product-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-tags} : Updates an existing productTag.
     *
     * @param productTagDTO the productTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productTagDTO,
     * or with status {@code 400 (Bad Request)} if the productTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-tags")
    public ResponseEntity<ProductTagDTO> updateProductTag(@RequestBody ProductTagDTO productTagDTO) throws URISyntaxException {
        log.debug("REST request to update ProductTag : {}", productTagDTO);
        if (productTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductTagDTO result = productTagService.save(productTagDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-tags} : get all the productTags.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productTags in body.
     */
    @GetMapping("/product-tags")
    public ResponseEntity<List<ProductTagDTO>> getAllProductTags(Pageable pageable) {
        log.debug("REST request to get a page of ProductTags");
        Page<ProductTagDTO> page = productTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-tags/:id} : get the "id" productTag.
     *
     * @param id the id of the productTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-tags/{id}")
    public ResponseEntity<ProductTagDTO> getProductTag(@PathVariable Long id) {
        log.debug("REST request to get ProductTag : {}", id);
        Optional<ProductTagDTO> productTagDTO = productTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productTagDTO);
    }

    /**
     * {@code DELETE  /product-tags/:id} : delete the "id" productTag.
     *
     * @param id the id of the productTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-tags/{id}")
    public ResponseEntity<Void> deleteProductTag(@PathVariable Long id) {
        log.debug("REST request to delete ProductTag : {}", id);
        productTagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
