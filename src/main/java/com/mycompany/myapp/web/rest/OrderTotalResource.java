package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OrderTotalService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.OrderTotalDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderTotal}.
 */
@RestController
@RequestMapping("/api")
public class OrderTotalResource {

    private final Logger log = LoggerFactory.getLogger(OrderTotalResource.class);

    private static final String ENTITY_NAME = "orderTotal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderTotalService orderTotalService;

    public OrderTotalResource(OrderTotalService orderTotalService) {
        this.orderTotalService = orderTotalService;
    }

    /**
     * {@code POST  /order-totals} : Create a new orderTotal.
     *
     * @param orderTotalDTO the orderTotalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderTotalDTO, or with status {@code 400 (Bad Request)} if the orderTotal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-totals")
    public ResponseEntity<OrderTotalDTO> createOrderTotal(@RequestBody OrderTotalDTO orderTotalDTO) throws URISyntaxException {
        log.debug("REST request to save OrderTotal : {}", orderTotalDTO);
        if (orderTotalDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderTotal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderTotalDTO result = orderTotalService.save(orderTotalDTO);
        return ResponseEntity.created(new URI("/api/order-totals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-totals} : Updates an existing orderTotal.
     *
     * @param orderTotalDTO the orderTotalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderTotalDTO,
     * or with status {@code 400 (Bad Request)} if the orderTotalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderTotalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-totals")
    public ResponseEntity<OrderTotalDTO> updateOrderTotal(@RequestBody OrderTotalDTO orderTotalDTO) throws URISyntaxException {
        log.debug("REST request to update OrderTotal : {}", orderTotalDTO);
        if (orderTotalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderTotalDTO result = orderTotalService.save(orderTotalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderTotalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-totals} : get all the orderTotals.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderTotals in body.
     */
    @GetMapping("/order-totals")
    public ResponseEntity<List<OrderTotalDTO>> getAllOrderTotals(Pageable pageable) {
        log.debug("REST request to get a page of OrderTotals");
        Page<OrderTotalDTO> page = orderTotalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-totals/:id} : get the "id" orderTotal.
     *
     * @param id the id of the orderTotalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderTotalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-totals/{id}")
    public ResponseEntity<OrderTotalDTO> getOrderTotal(@PathVariable Long id) {
        log.debug("REST request to get OrderTotal : {}", id);
        Optional<OrderTotalDTO> orderTotalDTO = orderTotalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderTotalDTO);
    }

    /**
     * {@code DELETE  /order-totals/:id} : delete the "id" orderTotal.
     *
     * @param id the id of the orderTotalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-totals/{id}")
    public ResponseEntity<Void> deleteOrderTotal(@PathVariable Long id) {
        log.debug("REST request to delete OrderTotal : {}", id);
        orderTotalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
