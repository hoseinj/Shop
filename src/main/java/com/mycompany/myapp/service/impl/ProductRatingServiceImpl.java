package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductRatingService;
import com.mycompany.myapp.domain.ProductRating;
import com.mycompany.myapp.repository.ProductRatingRepository;
import com.mycompany.myapp.service.dto.ProductRatingDTO;
import com.mycompany.myapp.service.mapper.ProductRatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductRating}.
 */
@Service
@Transactional
public class ProductRatingServiceImpl implements ProductRatingService {

    private final Logger log = LoggerFactory.getLogger(ProductRatingServiceImpl.class);

    private final ProductRatingRepository productRatingRepository;

    private final ProductRatingMapper productRatingMapper;

    public ProductRatingServiceImpl(ProductRatingRepository productRatingRepository, ProductRatingMapper productRatingMapper) {
        this.productRatingRepository = productRatingRepository;
        this.productRatingMapper = productRatingMapper;
    }

    /**
     * Save a productRating.
     *
     * @param productRatingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductRatingDTO save(ProductRatingDTO productRatingDTO) {
        log.debug("Request to save ProductRating : {}", productRatingDTO);
        ProductRating productRating = productRatingMapper.toEntity(productRatingDTO);
        productRating = productRatingRepository.save(productRating);
        return productRatingMapper.toDto(productRating);
    }

    /**
     * Get all the productRatings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductRatingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductRatings");
        return productRatingRepository.findAll(pageable)
            .map(productRatingMapper::toDto);
    }


    /**
     * Get one productRating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductRatingDTO> findOne(Long id) {
        log.debug("Request to get ProductRating : {}", id);
        return productRatingRepository.findById(id)
            .map(productRatingMapper::toDto);
    }

    /**
     * Delete the productRating by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductRating : {}", id);
        productRatingRepository.deleteById(id);
    }
}
