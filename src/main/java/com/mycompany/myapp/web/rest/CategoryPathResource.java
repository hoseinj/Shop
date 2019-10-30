package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CategoryPathService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CategoryPathDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CategoryPath}.
 */
@RestController
@RequestMapping("/api")
public class CategoryPathResource {

    private final Logger log = LoggerFactory.getLogger(CategoryPathResource.class);

    private static final String ENTITY_NAME = "categoryPath";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryPathService categoryPathService;

    public CategoryPathResource(CategoryPathService categoryPathService) {
        this.categoryPathService = categoryPathService;
    }

    /**
     * {@code POST  /category-paths} : Create a new categoryPath.
     *
     * @param categoryPathDTO the categoryPathDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryPathDTO, or with status {@code 400 (Bad Request)} if the categoryPath has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-paths")
    public ResponseEntity<CategoryPathDTO> createCategoryPath(@RequestBody CategoryPathDTO categoryPathDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryPath : {}", categoryPathDTO);
        if (categoryPathDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryPath cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryPathDTO result = categoryPathService.save(categoryPathDTO);
        return ResponseEntity.created(new URI("/api/category-paths/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-paths} : Updates an existing categoryPath.
     *
     * @param categoryPathDTO the categoryPathDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryPathDTO,
     * or with status {@code 400 (Bad Request)} if the categoryPathDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryPathDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-paths")
    public ResponseEntity<CategoryPathDTO> updateCategoryPath(@RequestBody CategoryPathDTO categoryPathDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryPath : {}", categoryPathDTO);
        if (categoryPathDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryPathDTO result = categoryPathService.save(categoryPathDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryPathDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-paths} : get all the categoryPaths.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryPaths in body.
     */
    @GetMapping("/category-paths")
    public ResponseEntity<List<CategoryPathDTO>> getAllCategoryPaths(Pageable pageable) {
        log.debug("REST request to get a page of CategoryPaths");
        Page<CategoryPathDTO> page = categoryPathService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /category-paths/:id} : get the "id" categoryPath.
     *
     * @param id the id of the categoryPathDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryPathDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-paths/{id}")
    public ResponseEntity<CategoryPathDTO> getCategoryPath(@PathVariable Long id) {
        log.debug("REST request to get CategoryPath : {}", id);
        Optional<CategoryPathDTO> categoryPathDTO = categoryPathService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryPathDTO);
    }

    /**
     * {@code DELETE  /category-paths/:id} : delete the "id" categoryPath.
     *
     * @param id the id of the categoryPathDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-paths/{id}")
    public ResponseEntity<Void> deleteCategoryPath(@PathVariable Long id) {
        log.debug("REST request to delete CategoryPath : {}", id);
        categoryPathService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
