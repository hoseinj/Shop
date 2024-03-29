package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProductDiscountService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProductDiscountDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductDiscount}.
 */
@RestController
@RequestMapping("/api")
public class ProductDiscountResource {

    private final Logger log = LoggerFactory.getLogger(ProductDiscountResource.class);

    private static final String ENTITY_NAME = "productDiscount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductDiscountService productDiscountService;

    public ProductDiscountResource(ProductDiscountService productDiscountService) {
        this.productDiscountService = productDiscountService;
    }

    /**
     * {@code POST  /product-discounts} : Create a new productDiscount.
     *
     * @param productDiscountDTO the productDiscountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDiscountDTO, or with status {@code 400 (Bad Request)} if the productDiscount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-discounts")
    public ResponseEntity<ProductDiscountDTO> createProductDiscount(@RequestBody ProductDiscountDTO productDiscountDTO) throws URISyntaxException {
        log.debug("REST request to save ProductDiscount : {}", productDiscountDTO);
        if (productDiscountDTO.getId() != null) {
            throw new BadRequestAlertException("A new productDiscount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductDiscountDTO result = productDiscountService.save(productDiscountDTO);
        return ResponseEntity.created(new URI("/api/product-discounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-discounts} : Updates an existing productDiscount.
     *
     * @param productDiscountDTO the productDiscountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productDiscountDTO,
     * or with status {@code 400 (Bad Request)} if the productDiscountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productDiscountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-discounts")
    public ResponseEntity<ProductDiscountDTO> updateProductDiscount(@RequestBody ProductDiscountDTO productDiscountDTO) throws URISyntaxException {
        log.debug("REST request to update ProductDiscount : {}", productDiscountDTO);
        if (productDiscountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductDiscountDTO result = productDiscountService.save(productDiscountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productDiscountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-discounts} : get all the productDiscounts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productDiscounts in body.
     */
    @GetMapping("/product-discounts")
    public ResponseEntity<List<ProductDiscountDTO>> getAllProductDiscounts(Pageable pageable) {
        log.debug("REST request to get a page of ProductDiscounts");
        Page<ProductDiscountDTO> page = productDiscountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-discounts/:id} : get the "id" productDiscount.
     *
     * @param id the id of the productDiscountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDiscountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-discounts/{id}")
    public ResponseEntity<ProductDiscountDTO> getProductDiscount(@PathVariable Long id) {
        log.debug("REST request to get ProductDiscount : {}", id);
        Optional<ProductDiscountDTO> productDiscountDTO = productDiscountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDiscountDTO);
    }

    /**
     * {@code DELETE  /product-discounts/:id} : delete the "id" productDiscount.
     *
     * @param id the id of the productDiscountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-discounts/{id}")
    public ResponseEntity<Void> deleteProductDiscount(@PathVariable Long id) {
        log.debug("REST request to delete ProductDiscount : {}", id);
        productDiscountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
