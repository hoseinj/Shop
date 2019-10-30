package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ShopPageService;
import com.mycompany.myapp.domain.ShopPage;
import com.mycompany.myapp.repository.ShopPageRepository;
import com.mycompany.myapp.service.dto.ShopPageDTO;
import com.mycompany.myapp.service.mapper.ShopPageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShopPage}.
 */
@Service
@Transactional
public class ShopPageServiceImpl implements ShopPageService {

    private final Logger log = LoggerFactory.getLogger(ShopPageServiceImpl.class);

    private final ShopPageRepository shopPageRepository;

    private final ShopPageMapper shopPageMapper;

    public ShopPageServiceImpl(ShopPageRepository shopPageRepository, ShopPageMapper shopPageMapper) {
        this.shopPageRepository = shopPageRepository;
        this.shopPageMapper = shopPageMapper;
    }

    /**
     * Save a shopPage.
     *
     * @param shopPageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShopPageDTO save(ShopPageDTO shopPageDTO) {
        log.debug("Request to save ShopPage : {}", shopPageDTO);
        ShopPage shopPage = shopPageMapper.toEntity(shopPageDTO);
        shopPage = shopPageRepository.save(shopPage);
        return shopPageMapper.toDto(shopPage);
    }

    /**
     * Get all the shopPages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShopPageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShopPages");
        return shopPageRepository.findAll(pageable)
            .map(shopPageMapper::toDto);
    }


    /**
     * Get one shopPage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShopPageDTO> findOne(Long id) {
        log.debug("Request to get ShopPage : {}", id);
        return shopPageRepository.findById(id)
            .map(shopPageMapper::toDto);
    }

    /**
     * Delete the shopPage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShopPage : {}", id);
        shopPageRepository.deleteById(id);
    }
}
