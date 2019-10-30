package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CustomerTransactionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CustomerTransactionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CustomerTransaction}.
 */
@RestController
@RequestMapping("/api")
public class CustomerTransactionResource {

    private final Logger log = LoggerFactory.getLogger(CustomerTransactionResource.class);

    private static final String ENTITY_NAME = "customerTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerTransactionService customerTransactionService;

    public CustomerTransactionResource(CustomerTransactionService customerTransactionService) {
        this.customerTransactionService = customerTransactionService;
    }

    /**
     * {@code POST  /customer-transactions} : Create a new customerTransaction.
     *
     * @param customerTransactionDTO the customerTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerTransactionDTO, or with status {@code 400 (Bad Request)} if the customerTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-transactions")
    public ResponseEntity<CustomerTransactionDTO> createCustomerTransaction(@RequestBody CustomerTransactionDTO customerTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerTransaction : {}", customerTransactionDTO);
        if (customerTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerTransactionDTO result = customerTransactionService.save(customerTransactionDTO);
        return ResponseEntity.created(new URI("/api/customer-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-transactions} : Updates an existing customerTransaction.
     *
     * @param customerTransactionDTO the customerTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the customerTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-transactions")
    public ResponseEntity<CustomerTransactionDTO> updateCustomerTransaction(@RequestBody CustomerTransactionDTO customerTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerTransaction : {}", customerTransactionDTO);
        if (customerTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerTransactionDTO result = customerTransactionService.save(customerTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-transactions} : get all the customerTransactions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerTransactions in body.
     */
    @GetMapping("/customer-transactions")
    public ResponseEntity<List<CustomerTransactionDTO>> getAllCustomerTransactions(Pageable pageable) {
        log.debug("REST request to get a page of CustomerTransactions");
        Page<CustomerTransactionDTO> page = customerTransactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-transactions/:id} : get the "id" customerTransaction.
     *
     * @param id the id of the customerTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-transactions/{id}")
    public ResponseEntity<CustomerTransactionDTO> getCustomerTransaction(@PathVariable Long id) {
        log.debug("REST request to get CustomerTransaction : {}", id);
        Optional<CustomerTransactionDTO> customerTransactionDTO = customerTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerTransactionDTO);
    }

    /**
     * {@code DELETE  /customer-transactions/:id} : delete the "id" customerTransaction.
     *
     * @param id the id of the customerTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-transactions/{id}")
    public ResponseEntity<Void> deleteCustomerTransaction(@PathVariable Long id) {
        log.debug("REST request to delete CustomerTransaction : {}", id);
        customerTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
