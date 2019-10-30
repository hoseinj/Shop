package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OrderLogService;
import com.mycompany.myapp.domain.OrderLog;
import com.mycompany.myapp.repository.OrderLogRepository;
import com.mycompany.myapp.service.dto.OrderLogDTO;
import com.mycompany.myapp.service.mapper.OrderLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderLog}.
 */
@Service
@Transactional
public class OrderLogServiceImpl implements OrderLogService {

    private final Logger log = LoggerFactory.getLogger(OrderLogServiceImpl.class);

    private final OrderLogRepository orderLogRepository;

    private final OrderLogMapper orderLogMapper;

    public OrderLogServiceImpl(OrderLogRepository orderLogRepository, OrderLogMapper orderLogMapper) {
        this.orderLogRepository = orderLogRepository;
        this.orderLogMapper = orderLogMapper;
    }

    /**
     * Save a orderLog.
     *
     * @param orderLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderLogDTO save(OrderLogDTO orderLogDTO) {
        log.debug("Request to save OrderLog : {}", orderLogDTO);
        OrderLog orderLog = orderLogMapper.toEntity(orderLogDTO);
        orderLog = orderLogRepository.save(orderLog);
        return orderLogMapper.toDto(orderLog);
    }

    /**
     * Get all the orderLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderLogs");
        return orderLogRepository.findAll(pageable)
            .map(orderLogMapper::toDto);
    }


    /**
     * Get one orderLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderLogDTO> findOne(Long id) {
        log.debug("Request to get OrderLog : {}", id);
        return orderLogRepository.findById(id)
            .map(orderLogMapper::toDto);
    }

    /**
     * Delete the orderLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderLog : {}", id);
        orderLogRepository.deleteById(id);
    }
}
