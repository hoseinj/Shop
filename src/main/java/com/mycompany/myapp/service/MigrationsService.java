package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MigrationsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Migrations}.
 */
public interface MigrationsService {

    /**
     * Save a migrations.
     *
     * @param migrationsDTO the entity to save.
     * @return the persisted entity.
     */
    MigrationsDTO save(MigrationsDTO migrationsDTO);

    /**
     * Get all the migrations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MigrationsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" migrations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MigrationsDTO> findOne(Long id);

    /**
     * Delete the "id" migrations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
