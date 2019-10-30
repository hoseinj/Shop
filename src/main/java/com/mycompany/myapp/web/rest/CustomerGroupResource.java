package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CustomerGroupService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CustomerGroupDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CustomerGroup}.
 */
@RestController
@RequestMapping("/api")
public class CustomerGroupResource {

    private final Logger log = LoggerFactory.getLogger(CustomerGroupResource.class);

    private static final String ENTITY_NAME = "customerGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerGroupService customerGroupService;

    public CustomerGroupResource(CustomerGroupService customerGroupService) {
        this.customerGroupService = customerGroupService;
    }

    /**
     * {@code POST  /customer-groups} : Create a new customerGroup.
     *
     * @param customerGroupDTO the customerGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerGroupDTO, or with status {@code 400 (Bad Request)} if the customerGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-groups")
    public ResponseEntity<CustomerGroupDTO> createCustomerGroup(@RequestBody CustomerGroupDTO customerGroupDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerGroup : {}", customerGroupDTO);
        if (customerGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerGroupDTO result = customerGroupService.save(customerGroupDTO);
        return ResponseEntity.created(new URI("/api/customer-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-groups} : Updates an existing customerGroup.
     *
     * @param customerGroupDTO the customerGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerGroupDTO,
     * or with status {@code 400 (Bad Request)} if the customerGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-groups")
    public ResponseEntity<CustomerGroupDTO> updateCustomerGroup(@RequestBody CustomerGroupDTO customerGroupDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerGroup : {}", customerGroupDTO);
        if (customerGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerGroupDTO result = customerGroupService.save(customerGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-groups} : get all the customerGroups.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerGroups in body.
     */
    @GetMapping("/customer-groups")
    public ResponseEntity<List<CustomerGroupDTO>> getAllCustomerGroups(Pageable pageable) {
        log.debug("REST request to get a page of CustomerGroups");
        Page<CustomerGroupDTO> page = customerGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-groups/:id} : get the "id" customerGroup.
     *
     * @param id the id of the customerGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-groups/{id}")
    public ResponseEntity<CustomerGroupDTO> getCustomerGroup(@PathVariable Long id) {
        log.debug("REST request to get CustomerGroup : {}", id);
        Optional<CustomerGroupDTO> customerGroupDTO = customerGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerGroupDTO);
    }

    /**
     * {@code DELETE  /customer-groups/:id} : delete the "id" customerGroup.
     *
     * @param id the id of the customerGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-groups/{id}")
    public ResponseEntity<Void> deleteCustomerGroup(@PathVariable Long id) {
        log.debug("REST request to delete CustomerGroup : {}", id);
        customerGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
