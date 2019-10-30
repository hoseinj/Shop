package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductTagService;
import com.mycompany.myapp.domain.ProductTag;
import com.mycompany.myapp.repository.ProductTagRepository;
import com.mycompany.myapp.service.dto.ProductTagDTO;
import com.mycompany.myapp.service.mapper.ProductTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductTag}.
 */
@Service
@Transactional
public class ProductTagServiceImpl implements ProductTagService {

    private final Logger log = LoggerFactory.getLogger(ProductTagServiceImpl.class);

    private final ProductTagRepository productTagRepository;

    private final ProductTagMapper productTagMapper;

    public ProductTagServiceImpl(ProductTagRepository productTagRepository, ProductTagMapper productTagMapper) {
        this.productTagRepository = productTagRepository;
        this.productTagMapper = productTagMapper;
    }

    /**
     * Save a productTag.
     *
     * @param productTagDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductTagDTO save(ProductTagDTO productTagDTO) {
        log.debug("Request to save ProductTag : {}", productTagDTO);
        ProductTag productTag = productTagMapper.toEntity(productTagDTO);
        productTag = productTagRepository.save(productTag);
        return productTagMapper.toDto(productTag);
    }

    /**
     * Get all the productTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductTags");
        return productTagRepository.findAll(pageable)
            .map(productTagMapper::toDto);
    }


    /**
     * Get one productTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductTagDTO> findOne(Long id) {
        log.debug("Request to get ProductTag : {}", id);
        return productTagRepository.findById(id)
            .map(productTagMapper::toDto);
    }

    /**
     * Delete the productTag by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductTag : {}", id);
        productTagRepository.deleteById(id);
    }
}
