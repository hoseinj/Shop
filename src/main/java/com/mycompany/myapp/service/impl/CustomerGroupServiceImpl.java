package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CustomerGroupService;
import com.mycompany.myapp.domain.CustomerGroup;
import com.mycompany.myapp.repository.CustomerGroupRepository;
import com.mycompany.myapp.service.dto.CustomerGroupDTO;
import com.mycompany.myapp.service.mapper.CustomerGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerGroup}.
 */
@Service
@Transactional
public class CustomerGroupServiceImpl implements CustomerGroupService {

    private final Logger log = LoggerFactory.getLogger(CustomerGroupServiceImpl.class);

    private final CustomerGroupRepository customerGroupRepository;

    private final CustomerGroupMapper customerGroupMapper;

    public CustomerGroupServiceImpl(CustomerGroupRepository customerGroupRepository, CustomerGroupMapper customerGroupMapper) {
        this.customerGroupRepository = customerGroupRepository;
        this.customerGroupMapper = customerGroupMapper;
    }

    /**
     * Save a customerGroup.
     *
     * @param customerGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerGroupDTO save(CustomerGroupDTO customerGroupDTO) {
        log.debug("Request to save CustomerGroup : {}", customerGroupDTO);
        CustomerGroup customerGroup = customerGroupMapper.toEntity(customerGroupDTO);
        customerGroup = customerGroupRepository.save(customerGroup);
        return customerGroupMapper.toDto(customerGroup);
    }

    /**
     * Get all the customerGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerGroups");
        return customerGroupRepository.findAll(pageable)
            .map(customerGroupMapper::toDto);
    }


    /**
     * Get one customerGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerGroupDTO> findOne(Long id) {
        log.debug("Request to get CustomerGroup : {}", id);
        return customerGroupRepository.findById(id)
            .map(customerGroupMapper::toDto);
    }

    /**
     * Delete the customerGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerGroup : {}", id);
        customerGroupRepository.deleteById(id);
    }
}
