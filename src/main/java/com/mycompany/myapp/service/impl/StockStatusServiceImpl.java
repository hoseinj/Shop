package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StockStatusService;
import com.mycompany.myapp.domain.StockStatus;
import com.mycompany.myapp.repository.StockStatusRepository;
import com.mycompany.myapp.service.dto.StockStatusDTO;
import com.mycompany.myapp.service.mapper.StockStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StockStatus}.
 */
@Service
@Transactional
public class StockStatusServiceImpl implements StockStatusService {

    private final Logger log = LoggerFactory.getLogger(StockStatusServiceImpl.class);

    private final StockStatusRepository stockStatusRepository;

    private final StockStatusMapper stockStatusMapper;

    public StockStatusServiceImpl(StockStatusRepository stockStatusRepository, StockStatusMapper stockStatusMapper) {
        this.stockStatusRepository = stockStatusRepository;
        this.stockStatusMapper = stockStatusMapper;
    }

    /**
     * Save a stockStatus.
     *
     * @param stockStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StockStatusDTO save(StockStatusDTO stockStatusDTO) {
        log.debug("Request to save StockStatus : {}", stockStatusDTO);
        StockStatus stockStatus = stockStatusMapper.toEntity(stockStatusDTO);
        stockStatus = stockStatusRepository.save(stockStatus);
        return stockStatusMapper.toDto(stockStatus);
    }

    /**
     * Get all the stockStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockStatuses");
        return stockStatusRepository.findAll(pageable)
            .map(stockStatusMapper::toDto);
    }


    /**
     * Get one stockStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockStatusDTO> findOne(Long id) {
        log.debug("Request to get StockStatus : {}", id);
        return stockStatusRepository.findById(id)
            .map(stockStatusMapper::toDto);
    }

    /**
     * Delete the stockStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockStatus : {}", id);
        stockStatusRepository.deleteById(id);
    }
}
