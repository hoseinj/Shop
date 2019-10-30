package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CustomerIpService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CustomerIpDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CustomerIp}.
 */
@RestController
@RequestMapping("/api")
public class CustomerIpResource {

    private final Logger log = LoggerFactory.getLogger(CustomerIpResource.class);

    private static final String ENTITY_NAME = "customerIp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerIpService customerIpService;

    public CustomerIpResource(CustomerIpService customerIpService) {
        this.customerIpService = customerIpService;
    }

    /**
     * {@code POST  /customer-ips} : Create a new customerIp.
     *
     * @param customerIpDTO the customerIpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerIpDTO, or with status {@code 400 (Bad Request)} if the customerIp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-ips")
    public ResponseEntity<CustomerIpDTO> createCustomerIp(@RequestBody CustomerIpDTO customerIpDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerIp : {}", customerIpDTO);
        if (customerIpDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerIp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerIpDTO result = customerIpService.save(customerIpDTO);
        return ResponseEntity.created(new URI("/api/customer-ips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customer-ips} : Updates an existing customerIp.
     *
     * @param customerIpDTO the customerIpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerIpDTO,
     * or with status {@code 400 (Bad Request)} if the customerIpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerIpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customer-ips")
    public ResponseEntity<CustomerIpDTO> updateCustomerIp(@RequestBody CustomerIpDTO customerIpDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerIp : {}", customerIpDTO);
        if (customerIpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerIpDTO result = customerIpService.save(customerIpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customerIpDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customer-ips} : get all the customerIps.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerIps in body.
     */
    @GetMapping("/customer-ips")
    public ResponseEntity<List<CustomerIpDTO>> getAllCustomerIps(Pageable pageable) {
        log.debug("REST request to get a page of CustomerIps");
        Page<CustomerIpDTO> page = customerIpService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-ips/:id} : get the "id" customerIp.
     *
     * @param id the id of the customerIpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerIpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-ips/{id}")
    public ResponseEntity<CustomerIpDTO> getCustomerIp(@PathVariable Long id) {
        log.debug("REST request to get CustomerIp : {}", id);
        Optional<CustomerIpDTO> customerIpDTO = customerIpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerIpDTO);
    }

    /**
     * {@code DELETE  /customer-ips/:id} : delete the "id" customerIp.
     *
     * @param id the id of the customerIpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-ips/{id}")
    public ResponseEntity<Void> deleteCustomerIp(@PathVariable Long id) {
        log.debug("REST request to delete CustomerIp : {}", id);
        customerIpService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
