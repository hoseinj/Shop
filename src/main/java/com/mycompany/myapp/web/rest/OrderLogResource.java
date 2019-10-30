package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OrderLogService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.OrderLogDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderLog}.
 */
@RestController
@RequestMapping("/api")
public class OrderLogResource {

    private final Logger log = LoggerFactory.getLogger(OrderLogResource.class);

    private static final String ENTITY_NAME = "orderLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderLogService orderLogService;

    public OrderLogResource(OrderLogService orderLogService) {
        this.orderLogService = orderLogService;
    }

    /**
     * {@code POST  /order-logs} : Create a new orderLog.
     *
     * @param orderLogDTO the orderLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderLogDTO, or with status {@code 400 (Bad Request)} if the orderLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-logs")
    public ResponseEntity<OrderLogDTO> createOrderLog(@RequestBody OrderLogDTO orderLogDTO) throws URISyntaxException {
        log.debug("REST request to save OrderLog : {}", orderLogDTO);
        if (orderLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderLogDTO result = orderLogService.save(orderLogDTO);
        return ResponseEntity.created(new URI("/api/order-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-logs} : Updates an existing orderLog.
     *
     * @param orderLogDTO the orderLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderLogDTO,
     * or with status {@code 400 (Bad Request)} if the orderLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-logs")
    public ResponseEntity<OrderLogDTO> updateOrderLog(@RequestBody OrderLogDTO orderLogDTO) throws URISyntaxException {
        log.debug("REST request to update OrderLog : {}", orderLogDTO);
        if (orderLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderLogDTO result = orderLogService.save(orderLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-logs} : get all the orderLogs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderLogs in body.
     */
    @GetMapping("/order-logs")
    public ResponseEntity<List<OrderLogDTO>> getAllOrderLogs(Pageable pageable) {
        log.debug("REST request to get a page of OrderLogs");
        Page<OrderLogDTO> page = orderLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-logs/:id} : get the "id" orderLog.
     *
     * @param id the id of the orderLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-logs/{id}")
    public ResponseEntity<OrderLogDTO> getOrderLog(@PathVariable Long id) {
        log.debug("REST request to get OrderLog : {}", id);
        Optional<OrderLogDTO> orderLogDTO = orderLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderLogDTO);
    }

    /**
     * {@code DELETE  /order-logs/:id} : delete the "id" orderLog.
     *
     * @param id the id of the orderLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-logs/{id}")
    public ResponseEntity<Void> deleteOrderLog(@PathVariable Long id) {
        log.debug("REST request to delete OrderLog : {}", id);
        orderLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
