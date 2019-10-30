package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OrderProductService;
import com.mycompany.myapp.domain.OrderProduct;
import com.mycompany.myapp.repository.OrderProductRepository;
import com.mycompany.myapp.service.dto.OrderProductDTO;
import com.mycompany.myapp.service.mapper.OrderProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderProduct}.
 */
@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private final Logger log = LoggerFactory.getLogger(OrderProductServiceImpl.class);

    private final OrderProductRepository orderProductRepository;

    private final OrderProductMapper orderProductMapper;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository, OrderProductMapper orderProductMapper) {
        this.orderProductRepository = orderProductRepository;
        this.orderProductMapper = orderProductMapper;
    }

    /**
     * Save a orderProduct.
     *
     * @param orderProductDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderProductDTO save(OrderProductDTO orderProductDTO) {
        log.debug("Request to save OrderProduct : {}", orderProductDTO);
        OrderProduct orderProduct = orderProductMapper.toEntity(orderProductDTO);
        orderProduct = orderProductRepository.save(orderProduct);
        return orderProductMapper.toDto(orderProduct);
    }

    /**
     * Get all the orderProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderProducts");
        return orderProductRepository.findAll(pageable)
            .map(orderProductMapper::toDto);
    }


    /**
     * Get one orderProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderProductDTO> findOne(Long id) {
        log.debug("Request to get OrderProduct : {}", id);
        return orderProductRepository.findById(id)
            .map(orderProductMapper::toDto);
    }

    /**
     * Delete the orderProduct by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderProduct : {}", id);
        orderProductRepository.deleteById(id);
    }
}
