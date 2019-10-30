package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CustomerIpService;
import com.mycompany.myapp.domain.CustomerIp;
import com.mycompany.myapp.repository.CustomerIpRepository;
import com.mycompany.myapp.service.dto.CustomerIpDTO;
import com.mycompany.myapp.service.mapper.CustomerIpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerIp}.
 */
@Service
@Transactional
public class CustomerIpServiceImpl implements CustomerIpService {

    private final Logger log = LoggerFactory.getLogger(CustomerIpServiceImpl.class);

    private final CustomerIpRepository customerIpRepository;

    private final CustomerIpMapper customerIpMapper;

    public CustomerIpServiceImpl(CustomerIpRepository customerIpRepository, CustomerIpMapper customerIpMapper) {
        this.customerIpRepository = customerIpRepository;
        this.customerIpMapper = customerIpMapper;
    }

    /**
     * Save a customerIp.
     *
     * @param customerIpDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerIpDTO save(CustomerIpDTO customerIpDTO) {
        log.debug("Request to save CustomerIp : {}", customerIpDTO);
        CustomerIp customerIp = customerIpMapper.toEntity(customerIpDTO);
        customerIp = customerIpRepository.save(customerIp);
        return customerIpMapper.toDto(customerIp);
    }

    /**
     * Get all the customerIps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerIpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerIps");
        return customerIpRepository.findAll(pageable)
            .map(customerIpMapper::toDto);
    }


    /**
     * Get one customerIp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerIpDTO> findOne(Long id) {
        log.debug("Request to get CustomerIp : {}", id);
        return customerIpRepository.findById(id)
            .map(customerIpMapper::toDto);
    }

    /**
     * Delete the customerIp by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerIp : {}", id);
        customerIpRepository.deleteById(id);
    }
}
