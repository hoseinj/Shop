package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MigrationsService;
import com.mycompany.myapp.domain.Migrations;
import com.mycompany.myapp.repository.MigrationsRepository;
import com.mycompany.myapp.service.dto.MigrationsDTO;
import com.mycompany.myapp.service.mapper.MigrationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Migrations}.
 */
@Service
@Transactional
public class MigrationsServiceImpl implements MigrationsService {

    private final Logger log = LoggerFactory.getLogger(MigrationsServiceImpl.class);

    private final MigrationsRepository migrationsRepository;

    private final MigrationsMapper migrationsMapper;

    public MigrationsServiceImpl(MigrationsRepository migrationsRepository, MigrationsMapper migrationsMapper) {
        this.migrationsRepository = migrationsRepository;
        this.migrationsMapper = migrationsMapper;
    }

    /**
     * Save a migrations.
     *
     * @param migrationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MigrationsDTO save(MigrationsDTO migrationsDTO) {
        log.debug("Request to save Migrations : {}", migrationsDTO);
        Migrations migrations = migrationsMapper.toEntity(migrationsDTO);
        migrations = migrationsRepository.save(migrations);
        return migrationsMapper.toDto(migrations);
    }

    /**
     * Get all the migrations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MigrationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Migrations");
        return migrationsRepository.findAll(pageable)
            .map(migrationsMapper::toDto);
    }


    /**
     * Get one migrations by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MigrationsDTO> findOne(Long id) {
        log.debug("Request to get Migrations : {}", id);
        return migrationsRepository.findById(id)
            .map(migrationsMapper::toDto);
    }

    /**
     * Delete the migrations by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Migrations : {}", id);
        migrationsRepository.deleteById(id);
    }
}
