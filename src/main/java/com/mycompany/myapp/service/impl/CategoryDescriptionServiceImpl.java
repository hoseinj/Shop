package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CategoryDescriptionService;
import com.mycompany.myapp.domain.CategoryDescription;
import com.mycompany.myapp.repository.CategoryDescriptionRepository;
import com.mycompany.myapp.service.dto.CategoryDescriptionDTO;
import com.mycompany.myapp.service.mapper.CategoryDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategoryDescription}.
 */
@Service
@Transactional
public class CategoryDescriptionServiceImpl implements CategoryDescriptionService {

    private final Logger log = LoggerFactory.getLogger(CategoryDescriptionServiceImpl.class);

    private final CategoryDescriptionRepository categoryDescriptionRepository;

    private final CategoryDescriptionMapper categoryDescriptionMapper;

    public CategoryDescriptionServiceImpl(CategoryDescriptionRepository categoryDescriptionRepository, CategoryDescriptionMapper categoryDescriptionMapper) {
        this.categoryDescriptionRepository = categoryDescriptionRepository;
        this.categoryDescriptionMapper = categoryDescriptionMapper;
    }

    /**
     * Save a categoryDescription.
     *
     * @param categoryDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategoryDescriptionDTO save(CategoryDescriptionDTO categoryDescriptionDTO) {
        log.debug("Request to save CategoryDescription : {}", categoryDescriptionDTO);
        CategoryDescription categoryDescription = categoryDescriptionMapper.toEntity(categoryDescriptionDTO);
        categoryDescription = categoryDescriptionRepository.save(categoryDescription);
        return categoryDescriptionMapper.toDto(categoryDescription);
    }

    /**
     * Get all the categoryDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDescriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryDescriptions");
        return categoryDescriptionRepository.findAll(pageable)
            .map(categoryDescriptionMapper::toDto);
    }


    /**
     * Get one categoryDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDescriptionDTO> findOne(Long id) {
        log.debug("Request to get CategoryDescription : {}", id);
        return categoryDescriptionRepository.findById(id)
            .map(categoryDescriptionMapper::toDto);
    }

    /**
     * Delete the categoryDescription by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryDescription : {}", id);
        categoryDescriptionRepository.deleteById(id);
    }
}
