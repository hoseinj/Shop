package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.StockStatusService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.StockStatusDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.StockStatus}.
 */
@RestController
@RequestMapping("/api")
public class StockStatusResource {

    private final Logger log = LoggerFactory.getLogger(StockStatusResource.class);

    private static final String ENTITY_NAME = "stockStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockStatusService stockStatusService;

    public StockStatusResource(StockStatusService stockStatusService) {
        this.stockStatusService = stockStatusService;
    }

    /**
     * {@code POST  /stock-statuses} : Create a new stockStatus.
     *
     * @param stockStatusDTO the stockStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockStatusDTO, or with status {@code 400 (Bad Request)} if the stockStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-statuses")
    public ResponseEntity<StockStatusDTO> createStockStatus(@RequestBody StockStatusDTO stockStatusDTO) throws URISyntaxException {
        log.debug("REST request to save StockStatus : {}", stockStatusDTO);
        if (stockStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockStatusDTO result = stockStatusService.save(stockStatusDTO);
        return ResponseEntity.created(new URI("/api/stock-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-statuses} : Updates an existing stockStatus.
     *
     * @param stockStatusDTO the stockStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockStatusDTO,
     * or with status {@code 400 (Bad Request)} if the stockStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-statuses")
    public ResponseEntity<StockStatusDTO> updateStockStatus(@RequestBody StockStatusDTO stockStatusDTO) throws URISyntaxException {
        log.debug("REST request to update StockStatus : {}", stockStatusDTO);
        if (stockStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockStatusDTO result = stockStatusService.save(stockStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stockStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stock-statuses} : get all the stockStatuses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockStatuses in body.
     */
    @GetMapping("/stock-statuses")
    public ResponseEntity<List<StockStatusDTO>> getAllStockStatuses(Pageable pageable) {
        log.debug("REST request to get a page of StockStatuses");
        Page<StockStatusDTO> page = stockStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stock-statuses/:id} : get the "id" stockStatus.
     *
     * @param id the id of the stockStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-statuses/{id}")
    public ResponseEntity<StockStatusDTO> getStockStatus(@PathVariable Long id) {
        log.debug("REST request to get StockStatus : {}", id);
        Optional<StockStatusDTO> stockStatusDTO = stockStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stockStatusDTO);
    }

    /**
     * {@code DELETE  /stock-statuses/:id} : delete the "id" stockStatus.
     *
     * @param id the id of the stockStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-statuses/{id}")
    public ResponseEntity<Void> deleteStockStatus(@PathVariable Long id) {
        log.debug("REST request to delete StockStatus : {}", id);
        stockStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
