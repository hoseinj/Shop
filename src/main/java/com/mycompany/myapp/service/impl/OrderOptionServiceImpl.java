package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.OrderOptionService;
import com.mycompany.myapp.domain.OrderOption;
import com.mycompany.myapp.repository.OrderOptionRepository;
import com.mycompany.myapp.service.dto.OrderOptionDTO;
import com.mycompany.myapp.service.mapper.OrderOptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderOption}.
 */
@Service
@Transactional
public class OrderOptionServiceImpl implements OrderOptionService {

    private final Logger log = LoggerFactory.getLogger(OrderOptionServiceImpl.class);

    private final OrderOptionRepository orderOptionRepository;

    private final OrderOptionMapper orderOptionMapper;

    public OrderOptionServiceImpl(OrderOptionRepository orderOptionRepository, OrderOptionMapper orderOptionMapper) {
        this.orderOptionRepository = orderOptionRepository;
        this.orderOptionMapper = orderOptionMapper;
    }

    /**
     * Save a orderOption.
     *
     * @param orderOptionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrderOptionDTO save(OrderOptionDTO orderOptionDTO) {
        log.debug("Request to save OrderOption : {}", orderOptionDTO);
        OrderOption orderOption = orderOptionMapper.toEntity(orderOptionDTO);
        orderOption = orderOptionRepository.save(orderOption);
        return orderOptionMapper.toDto(orderOption);
    }

    /**
     * Get all the orderOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderOptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderOptions");
        return orderOptionRepository.findAll(pageable)
            .map(orderOptionMapper::toDto);
    }


    /**
     * Get one orderOption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderOptionDTO> findOne(Long id) {
        log.debug("Request to get OrderOption : {}", id);
        return orderOptionRepository.findById(id)
            .map(orderOptionMapper::toDto);
    }

    /**
     * Delete the orderOption by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderOption : {}", id);
        orderOptionRepository.deleteById(id);
    }
}
