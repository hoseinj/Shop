package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OrderOptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.OrderOptionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderOption}.
 */
@RestController
@RequestMapping("/api")
public class OrderOptionResource {

    private final Logger log = LoggerFactory.getLogger(OrderOptionResource.class);

    private static final String ENTITY_NAME = "orderOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderOptionService orderOptionService;

    public OrderOptionResource(OrderOptionService orderOptionService) {
        this.orderOptionService = orderOptionService;
    }

    /**
     * {@code POST  /order-options} : Create a new orderOption.
     *
     * @param orderOptionDTO the orderOptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderOptionDTO, or with status {@code 400 (Bad Request)} if the orderOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-options")
    public ResponseEntity<OrderOptionDTO> createOrderOption(@RequestBody OrderOptionDTO orderOptionDTO) throws URISyntaxException {
        log.debug("REST request to save OrderOption : {}", orderOptionDTO);
        if (orderOptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderOptionDTO result = orderOptionService.save(orderOptionDTO);
        return ResponseEntity.created(new URI("/api/order-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-options} : Updates an existing orderOption.
     *
     * @param orderOptionDTO the orderOptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderOptionDTO,
     * or with status {@code 400 (Bad Request)} if the orderOptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderOptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-options")
    public ResponseEntity<OrderOptionDTO> updateOrderOption(@RequestBody OrderOptionDTO orderOptionDTO) throws URISyntaxException {
        log.debug("REST request to update OrderOption : {}", orderOptionDTO);
        if (orderOptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderOptionDTO result = orderOptionService.save(orderOptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderOptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-options} : get all the orderOptions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderOptions in body.
     */
    @GetMapping("/order-options")
    public ResponseEntity<List<OrderOptionDTO>> getAllOrderOptions(Pageable pageable) {
        log.debug("REST request to get a page of OrderOptions");
        Page<OrderOptionDTO> page = orderOptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-options/:id} : get the "id" orderOption.
     *
     * @param id the id of the orderOptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderOptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-options/{id}")
    public ResponseEntity<OrderOptionDTO> getOrderOption(@PathVariable Long id) {
        log.debug("REST request to get OrderOption : {}", id);
        Optional<OrderOptionDTO> orderOptionDTO = orderOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderOptionDTO);
    }

    /**
     * {@code DELETE  /order-options/:id} : delete the "id" orderOption.
     *
     * @param id the id of the orderOptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-options/{id}")
    public ResponseEntity<Void> deleteOrderOption(@PathVariable Long id) {
        log.debug("REST request to delete OrderOption : {}", id);
        orderOptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
