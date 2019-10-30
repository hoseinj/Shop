package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductDiscountService;
import com.mycompany.myapp.domain.ProductDiscount;
import com.mycompany.myapp.repository.ProductDiscountRepository;
import com.mycompany.myapp.service.dto.ProductDiscountDTO;
import com.mycompany.myapp.service.mapper.ProductDiscountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductDiscount}.
 */
@Service
@Transactional
public class ProductDiscountServiceImpl implements ProductDiscountService {

    private final Logger log = LoggerFactory.getLogger(ProductDiscountServiceImpl.class);

    private final ProductDiscountRepository productDiscountRepository;

    private final ProductDiscountMapper productDiscountMapper;

    public ProductDiscountServiceImpl(ProductDiscountRepository productDiscountRepository, ProductDiscountMapper productDiscountMapper) {
        this.productDiscountRepository = productDiscountRepository;
        this.productDiscountMapper = productDiscountMapper;
    }

    /**
     * Save a productDiscount.
     *
     * @param productDiscountDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductDiscountDTO save(ProductDiscountDTO productDiscountDTO) {
        log.debug("Request to save ProductDiscount : {}", productDiscountDTO);
        ProductDiscount productDiscount = productDiscountMapper.toEntity(productDiscountDTO);
        productDiscount = productDiscountRepository.save(productDiscount);
        return productDiscountMapper.toDto(productDiscount);
    }

    /**
     * Get all the productDiscounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDiscountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductDiscounts");
        return productDiscountRepository.findAll(pageable)
            .map(productDiscountMapper::toDto);
    }


    /**
     * Get one productDiscount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDiscountDTO> findOne(Long id) {
        log.debug("Request to get ProductDiscount : {}", id);
        return productDiscountRepository.findById(id)
            .map(productDiscountMapper::toDto);
    }

    /**
     * Delete the productDiscount by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductDiscount : {}", id);
        productDiscountRepository.deleteById(id);
    }
}
