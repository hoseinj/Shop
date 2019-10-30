package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PorductDescriptionService;
import com.mycompany.myapp.domain.PorductDescription;
import com.mycompany.myapp.repository.PorductDescriptionRepository;
import com.mycompany.myapp.service.dto.PorductDescriptionDTO;
import com.mycompany.myapp.service.mapper.PorductDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PorductDescription}.
 */
@Service
@Transactional
public class PorductDescriptionServiceImpl implements PorductDescriptionService {

    private final Logger log = LoggerFactory.getLogger(PorductDescriptionServiceImpl.class);

    private final PorductDescriptionRepository porductDescriptionRepository;

    private final PorductDescriptionMapper porductDescriptionMapper;

    public PorductDescriptionServiceImpl(PorductDescriptionRepository porductDescriptionRepository, PorductDescriptionMapper porductDescriptionMapper) {
        this.porductDescriptionRepository = porductDescriptionRepository;
        this.porductDescriptionMapper = porductDescriptionMapper;
    }

    /**
     * Save a porductDescription.
     *
     * @param porductDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PorductDescriptionDTO save(PorductDescriptionDTO porductDescriptionDTO) {
        log.debug("Request to save PorductDescription : {}", porductDescriptionDTO);
        PorductDescription porductDescription = porductDescriptionMapper.toEntity(porductDescriptionDTO);
        porductDescription = porductDescriptionRepository.save(porductDescription);
        return porductDescriptionMapper.toDto(porductDescription);
    }

    /**
     * Get all the porductDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PorductDescriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PorductDescriptions");
        return porductDescriptionRepository.findAll(pageable)
            .map(porductDescriptionMapper::toDto);
    }


    /**
     * Get one porductDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PorductDescriptionDTO> findOne(Long id) {
        log.debug("Request to get PorductDescription : {}", id);
        return porductDescriptionRepository.findById(id)
            .map(porductDescriptionMapper::toDto);
    }

    /**
     * Delete the porductDescription by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PorductDescription : {}", id);
        porductDescriptionRepository.deleteById(id);
    }
}
