package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductRelatedService;
import com.mycompany.myapp.domain.ProductRelated;
import com.mycompany.myapp.repository.ProductRelatedRepository;
import com.mycompany.myapp.service.dto.ProductRelatedDTO;
import com.mycompany.myapp.service.mapper.ProductRelatedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductRelated}.
 */
@Service
@Transactional
public class ProductRelatedServiceImpl implements ProductRelatedService {

    private final Logger log = LoggerFactory.getLogger(ProductRelatedServiceImpl.class);

    private final ProductRelatedRepository productRelatedRepository;

    private final ProductRelatedMapper productRelatedMapper;

    public ProductRelatedServiceImpl(ProductRelatedRepository productRelatedRepository, ProductRelatedMapper productRelatedMapper) {
        this.productRelatedRepository = productRelatedRepository;
        this.productRelatedMapper = productRelatedMapper;
    }

    /**
     * Save a productRelated.
     *
     * @param productRelatedDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductRelatedDTO save(ProductRelatedDTO productRelatedDTO) {
        log.debug("Request to save ProductRelated : {}", productRelatedDTO);
        ProductRelated productRelated = productRelatedMapper.toEntity(productRelatedDTO);
        productRelated = productRelatedRepository.save(productRelated);
        return productRelatedMapper.toDto(productRelated);
    }

    /**
     * Get all the productRelateds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductRelatedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductRelateds");
        return productRelatedRepository.findAll(pageable)
            .map(productRelatedMapper::toDto);
    }


    /**
     * Get one productRelated by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductRelatedDTO> findOne(Long id) {
        log.debug("Request to get ProductRelated : {}", id);
        return productRelatedRepository.findById(id)
            .map(productRelatedMapper::toDto);
    }

    /**
     * Delete the productRelated by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductRelated : {}", id);
        productRelatedRepository.deleteById(id);
    }
}
