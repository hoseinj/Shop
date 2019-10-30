package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CategoryDescriptionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CategoryDescriptionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CategoryDescription}.
 */
@RestController
@RequestMapping("/api")
public class CategoryDescriptionResource {

    private final Logger log = LoggerFactory.getLogger(CategoryDescriptionResource.class);

    private static final String ENTITY_NAME = "categoryDescription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryDescriptionService categoryDescriptionService;

    public CategoryDescriptionResource(CategoryDescriptionService categoryDescriptionService) {
        this.categoryDescriptionService = categoryDescriptionService;
    }

    /**
     * {@code POST  /category-descriptions} : Create a new categoryDescription.
     *
     * @param categoryDescriptionDTO the categoryDescriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryDescriptionDTO, or with status {@code 400 (Bad Request)} if the categoryDescription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-descriptions")
    public ResponseEntity<CategoryDescriptionDTO> createCategoryDescription(@RequestBody CategoryDescriptionDTO categoryDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryDescription : {}", categoryDescriptionDTO);
        if (categoryDescriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryDescription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryDescriptionDTO result = categoryDescriptionService.save(categoryDescriptionDTO);
        return ResponseEntity.created(new URI("/api/category-descriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-descriptions} : Updates an existing categoryDescription.
     *
     * @param categoryDescriptionDTO the categoryDescriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryDescriptionDTO,
     * or with status {@code 400 (Bad Request)} if the categoryDescriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryDescriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-descriptions")
    public ResponseEntity<CategoryDescriptionDTO> updateCategoryDescription(@RequestBody CategoryDescriptionDTO categoryDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryDescription : {}", categoryDescriptionDTO);
        if (categoryDescriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryDescriptionDTO result = categoryDescriptionService.save(categoryDescriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryDescriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-descriptions} : get all the categoryDescriptions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryDescriptions in body.
     */
    @GetMapping("/category-descriptions")
    public ResponseEntity<List<CategoryDescriptionDTO>> getAllCategoryDescriptions(Pageable pageable) {
        log.debug("REST request to get a page of CategoryDescriptions");
        Page<CategoryDescriptionDTO> page = categoryDescriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /category-descriptions/:id} : get the "id" categoryDescription.
     *
     * @param id the id of the categoryDescriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryDescriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-descriptions/{id}")
    public ResponseEntity<CategoryDescriptionDTO> getCategoryDescription(@PathVariable Long id) {
        log.debug("REST request to get CategoryDescription : {}", id);
        Optional<CategoryDescriptionDTO> categoryDescriptionDTO = categoryDescriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDescriptionDTO);
    }

    /**
     * {@code DELETE  /category-descriptions/:id} : delete the "id" categoryDescription.
     *
     * @param id the id of the categoryDescriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-descriptions/{id}")
    public ResponseEntity<Void> deleteCategoryDescription(@PathVariable Long id) {
        log.debug("REST request to delete CategoryDescription : {}", id);
        categoryDescriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
