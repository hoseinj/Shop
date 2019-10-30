package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CustomerTransactionService;
import com.mycompany.myapp.domain.CustomerTransaction;
import com.mycompany.myapp.repository.CustomerTransactionRepository;
import com.mycompany.myapp.service.dto.CustomerTransactionDTO;
import com.mycompany.myapp.service.mapper.CustomerTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerTransaction}.
 */
@Service
@Transactional
public class CustomerTransactionServiceImpl implements CustomerTransactionService {

    private final Logger log = LoggerFactory.getLogger(CustomerTransactionServiceImpl.class);

    private final CustomerTransactionRepository customerTransactionRepository;

    private final CustomerTransactionMapper customerTransactionMapper;

    public CustomerTransactionServiceImpl(CustomerTransactionRepository customerTransactionRepository, CustomerTransactionMapper customerTransactionMapper) {
        this.customerTransactionRepository = customerTransactionRepository;
        this.customerTransactionMapper = customerTransactionMapper;
    }

    /**
     * Save a customerTransaction.
     *
     * @param customerTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerTransactionDTO save(CustomerTransactionDTO customerTransactionDTO) {
        log.debug("Request to save CustomerTransaction : {}", customerTransactionDTO);
        CustomerTransaction customerTransaction = customerTransactionMapper.toEntity(customerTransactionDTO);
        customerTransaction = customerTransactionRepository.save(customerTransaction);
        return customerTransactionMapper.toDto(customerTransaction);
    }

    /**
     * Get all the customerTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerTransactions");
        return customerTransactionRepository.findAll(pageable)
            .map(customerTransactionMapper::toDto);
    }


    /**
     * Get one customerTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerTransactionDTO> findOne(Long id) {
        log.debug("Request to get CustomerTransaction : {}", id);
        return customerTransactionRepository.findById(id)
            .map(customerTransactionMapper::toDto);
    }

    /**
     * Delete the customerTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerTransaction : {}", id);
        customerTransactionRepository.deleteById(id);
    }
}
