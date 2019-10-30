package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CategoryPathService;
import com.mycompany.myapp.domain.CategoryPath;
import com.mycompany.myapp.repository.CategoryPathRepository;
import com.mycompany.myapp.service.dto.CategoryPathDTO;
import com.mycompany.myapp.service.mapper.CategoryPathMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategoryPath}.
 */
@Service
@Transactional
public class CategoryPathServiceImpl implements CategoryPathService {

    private final Logger log = LoggerFactory.getLogger(CategoryPathServiceImpl.class);

    private final CategoryPathRepository categoryPathRepository;

    private final CategoryPathMapper categoryPathMapper;

    public CategoryPathServiceImpl(CategoryPathRepository categoryPathRepository, CategoryPathMapper categoryPathMapper) {
        this.categoryPathRepository = categoryPathRepository;
        this.categoryPathMapper = categoryPathMapper;
    }

    /**
     * Save a categoryPath.
     *
     * @param categoryPathDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategoryPathDTO save(CategoryPathDTO categoryPathDTO) {
        log.debug("Request to save CategoryPath : {}", categoryPathDTO);
        CategoryPath categoryPath = categoryPathMapper.toEntity(categoryPathDTO);
        categoryPath = categoryPathRepository.save(categoryPath);
        return categoryPathMapper.toDto(categoryPath);
    }

    /**
     * Get all the categoryPaths.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryPathDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryPaths");
        return categoryPathRepository.findAll(pageable)
            .map(categoryPathMapper::toDto);
    }


    /**
     * Get one categoryPath by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryPathDTO> findOne(Long id) {
        log.debug("Request to get CategoryPath : {}", id);
        return categoryPathRepository.findById(id)
            .map(categoryPathMapper::toDto);
    }

    /**
     * Delete the categoryPath by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryPath : {}", id);
        categoryPathRepository.deleteById(id);
    }
}
