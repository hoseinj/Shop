package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CustomerWishlistService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CustomerWishlistDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CustomerWishlist}.
 */
@RestController
@RequestMapping("/api")
public class CustomerWishlistResource {

    private final Logger log = LoggerFactory.getLogger(CustomerWishlistResource.class);

    private static final String ENTITY_NAME = "customerWishlist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerWishlistService customerWishlistService;

    public CustomerWishlistResource(CustomerWishlistService customerWishlistService) {
        this.customerWishlistService = customerWishlistService;
    }

    /**
     * {@code POST  /customer-wishlists} : Create a new customerWishlist.
     *
     * @param customerWishlistDTO the customerWishlistDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerWishlistDTO, or with status {@code 400 (Bad Request)} if the customerWishlist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-wishlists")
    public ResponseEntity<CustomerWishlistDTO> createCustomerWishlist(@RequestBody CustomerWishlistDTO customerWishlistDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerWishlist : {}", customerWishlistDTO);
        if (customerWishlistDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerWishlist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerWishlistDTO result = customerWishlistService.save(customerWishlistDTO);
        return ResponseEntity.created(new URI("/api/customer-wishlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-wishlists} : Updates an existing customerWishlist.
     *
     * @param customerWishlistDTO the customerWishlistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerWishlistDTO,
     * or with status {@code 400 (Bad Request)} if the customerWishlistDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerWishlistDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-wishlists")
    public ResponseEntity<CustomerWishlistDTO> updateCustomerWishlist(@RequestBody CustomerWishlistDTO customerWishlistDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerWishlist : {}", customerWishlistDTO);
        if (customerWishlistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerWishlistDTO result = customerWishlistService.save(customerWishlistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerWishlistDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-wishlists} : get all the customerWishlists.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerWishlists in body.
     */
    @GetMapping("/customer-wishlists")
    public ResponseEntity<List<CustomerWishlistDTO>> getAllCustomerWishlists(Pageable pageable) {
        log.debug("REST request to get a page of CustomerWishlists");
        Page<CustomerWishlistDTO> page = customerWishlistService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-wishlists/:id} : get the "id" customerWishlist.
     *
     * @param id the id of the customerWishlistDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerWishlistDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-wishlists/{id}")
    public ResponseEntity<CustomerWishlistDTO> getCustomerWishlist(@PathVariable Long id) {
        log.debug("REST request to get CustomerWishlist : {}", id);
        Optional<CustomerWishlistDTO> customerWishlistDTO = customerWishlistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerWishlistDTO);
    }

    /**
     * {@code DELETE  /customer-wishlists/:id} : delete the "id" customerWishlist.
     *
     * @param id the id of the customerWishlistDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-wishlists/{id}")
    public ResponseEntity<Void> deleteCustomerWishlist(@PathVariable Long id) {
        log.debug("REST request to delete CustomerWishlist : {}", id);
        customerWishlistService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
