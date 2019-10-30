package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OrderProductService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.OrderProductDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderProduct}.
 */
@RestController
@RequestMapping("/api")
public class OrderProductResource {

    private final Logger log = LoggerFactory.getLogger(OrderProductResource.class);

    private static final String ENTITY_NAME = "orderProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderProductService orderProductService;

    public OrderProductResource(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    /**
     * {@code POST  /order-products} : Create a new orderProduct.
     *
     * @param orderProductDTO the orderProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderProductDTO, or with status {@code 400 (Bad Request)} if the orderProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-products")
    public ResponseEntity<OrderProductDTO> createOrderProduct(@RequestBody OrderProductDTO orderProductDTO) throws URISyntaxException {
        log.debug("REST request to save OrderProduct : {}", orderProductDTO);
        if (orderProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderProductDTO result = orderProductService.save(orderProductDTO);
        return ResponseEntity.created(new URI("/api/order-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-products} : Updates an existing orderProduct.
     *
     * @param orderProductDTO the orderProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProductDTO,
     * or with status {@code 400 (Bad Request)} if the orderProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-products")
    public ResponseEntity<OrderProductDTO> updateOrderProduct(@RequestBody OrderProductDTO orderProductDTO) throws URISyntaxException {
        log.debug("REST request to update OrderProduct : {}", orderProductDTO);
        if (orderProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderProductDTO result = orderProductService.save(orderProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-products} : get all the orderProducts.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderProducts in body.
     */
    @GetMapping("/order-products")
    public ResponseEntity<List<OrderProductDTO>> getAllOrderProducts(Pageable pageable) {
        log.debug("REST request to get a page of OrderProducts");
        Page<OrderProductDTO> page = orderProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-products/:id} : get the "id" orderProduct.
     *
     * @param id the id of the orderProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-products/{id}")
    public ResponseEntity<OrderProductDTO> getOrderProduct(@PathVariable Long id) {
        log.debug("REST request to get OrderProduct : {}", id);
        Optional<OrderProductDTO> orderProductDTO = orderProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderProductDTO);
    }

    /**
     * {@code DELETE  /order-products/:id} : delete the "id" orderProduct.
     *
     * @param id the id of the orderProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-products/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long id) {
        log.debug("REST request to delete OrderProduct : {}", id);
        orderProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
