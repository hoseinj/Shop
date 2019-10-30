package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProductViewLogService;
import com.mycompany.myapp.domain.ProductViewLog;
import com.mycompany.myapp.repository.ProductViewLogRepository;
import com.mycompany.myapp.service.dto.ProductViewLogDTO;
import com.mycompany.myapp.service.mapper.ProductViewLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductViewLog}.
 */
@Service
@Transactional
public class ProductViewLogServiceImpl implements ProductViewLogService {

    private final Logger log = LoggerFactory.getLogger(ProductViewLogServiceImpl.class);

    private final ProductViewLogRepository productViewLogRepository;

    private final ProductViewLogMapper productViewLogMapper;

    public ProductViewLogServiceImpl(ProductViewLogRepository productViewLogRepository, ProductViewLogMapper productViewLogMapper) {
        this.productViewLogRepository = productViewLogRepository;
        this.productViewLogMapper = productViewLogMapper;
    }

    /**
     * Save a productViewLog.
     *
     * @param productViewLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductViewLogDTO save(ProductViewLogDTO productViewLogDTO) {
        log.debug("Request to save ProductViewLog : {}", productViewLogDTO);
        ProductViewLog productViewLog = productViewLogMapper.toEntity(productViewLogDTO);
        productViewLog = productViewLogRepository.save(productViewLog);
        return productViewLogMapper.toDto(productViewLog);
    }

    /**
     * Get all the productViewLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductViewLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductViewLogs");
        return productViewLogRepository.findAll(pageable)
            .map(productViewLogMapper::toDto);
    }


    /**
     * Get one productViewLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductViewLogDTO> findOne(Long id) {
        log.debug("Request to get ProductViewLog : {}", id);
        return productViewLogRepository.findById(id)
            .map(productViewLogMapper::toDto);
    }

    /**
     * Delete the productViewLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductViewLog : {}", id);
        productViewLogRepository.deleteById(id);
    }
}
