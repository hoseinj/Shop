package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CustomerWishlistService;
import com.mycompany.myapp.domain.CustomerWishlist;
import com.mycompany.myapp.repository.CustomerWishlistRepository;
import com.mycompany.myapp.service.dto.CustomerWishlistDTO;
import com.mycompany.myapp.service.mapper.CustomerWishlistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerWishlist}.
 */
@Service
@Transactional
public class CustomerWishlistServiceImpl implements CustomerWishlistService {

    private final Logger log = LoggerFactory.getLogger(CustomerWishlistServiceImpl.class);

    private final CustomerWishlistRepository customerWishlistRepository;

    private final CustomerWishlistMapper customerWishlistMapper;

    public CustomerWishlistServiceImpl(CustomerWishlistRepository customerWishlistRepository, CustomerWishlistMapper customerWishlistMapper) {
        this.customerWishlistRepository = customerWishlistRepository;
        this.customerWishlistMapper = customerWishlistMapper;
    }

    /**
     * Save a customerWishlist.
     *
     * @param customerWishlistDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CustomerWishlistDTO save(CustomerWishlistDTO customerWishlistDTO) {
        log.debug("Request to save CustomerWishlist : {}", customerWishlistDTO);
        CustomerWishlist customerWishlist = customerWishlistMapper.toEntity(customerWishlistDTO);
        customerWishlist = customerWishlistRepository.save(customerWishlist);
        return customerWishlistMapper.toDto(customerWishlist);
    }

    /**
     * Get all the customerWishlists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerWishlistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerWishlists");
        return customerWishlistRepository.findAll(pageable)
            .map(customerWishlistMapper::toDto);
    }


    /**
     * Get one customerWishlist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerWishlistDTO> findOne(Long id) {
        log.debug("Request to get CustomerWishlist : {}", id);
        return customerWishlistRepository.findById(id)
            .map(customerWishlistMapper::toDto);
    }

    /**
     * Delete the customerWishlist by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerWishlist : {}", id);
        customerWishlistRepository.deleteById(id);
    }
}
