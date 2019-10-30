package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CustomerTransactionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CustomerTransaction}.
 */
public interface CustomerTransactionService {

    /**
     * Save a customerTransaction.
     *
     * @param customerTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    CustomerTransactionDTO save(CustomerTransactionDTO customerTransactionDTO);

    /**
     * Get all the customerTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomerTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" customerTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
