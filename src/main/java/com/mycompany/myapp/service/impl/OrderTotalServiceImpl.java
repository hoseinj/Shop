package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OrderTotalService;
import com.mycompany.myapp.domain.OrderTotal;
import com.mycompany.myapp.repository.OrderTotalRepository;
import com.mycompany.myapp.service.dto.OrderTotalDTO;
import com.mycompany.myapp.service.mapper.OrderTotalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderTotal}.
 */
@Service
@Transactional
public class OrderTotalServiceImpl implements OrderTotalService {

    private final Logger log = LoggerFactory.getLogger(OrderTotalServiceImpl.class);

    private final OrderTotalRepository orderTotalRepository;

    private final OrderTotalMapper orderTotalMapper;

    public OrderTotalServiceImpl(OrderTotalRepository orderTotalRepository, OrderTotalMapper orderTotalMapper) {
        this.orderTotalRepository = orderTotalRepository;
        this.orderTotalMapper = orderTotalMapper;
    }

    /**
     * Save a orderTotal.
     *
     * @param orderTotalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderTotalDTO save(OrderTotalDTO orderTotalDTO) {
        log.debug("Request to save OrderTotal : {}", orderTotalDTO);
        OrderTotal orderTotal = orderTotalMapper.toEntity(orderTotalDTO);
        orderTotal = orderTotalRepository.save(orderTotal);
        return orderTotalMapper.toDto(orderTotal);
    }

    /**
     * Get all the orderTotals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderTotalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderTotals");
        return orderTotalRepository.findAll(pageable)
            .map(orderTotalMapper::toDto);
    }


    /**
     * Get one orderTotal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderTotalDTO> findOne(Long id) {
        log.debug("Request to get OrderTotal : {}", id);
        return orderTotalRepository.findById(id)
            .map(orderTotalMapper::toDto);
    }

    /**
     * Delete the orderTotal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderTotal : {}", id);
        orderTotalRepository.deleteById(id);
    }
}
