package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ShopUserService;
import com.mycompany.myapp.domain.ShopUser;
import com.mycompany.myapp.repository.ShopUserRepository;
import com.mycompany.myapp.service.dto.ShopUserDTO;
import com.mycompany.myapp.service.mapper.ShopUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShopUser}.
 */
@Service
@Transactional
public class ShopUserServiceImpl implements ShopUserService {

    private final Logger log = LoggerFactory.getLogger(ShopUserServiceImpl.class);

    private final ShopUserRepository shopUserRepository;

    private final ShopUserMapper shopUserMapper;

    public ShopUserServiceImpl(ShopUserRepository shopUserRepository, ShopUserMapper shopUserMapper) {
        this.shopUserRepository = shopUserRepository;
        this.shopUserMapper = shopUserMapper;
    }

    /**
     * Save a shopUser.
     *
     * @param shopUserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShopUserDTO save(ShopUserDTO shopUserDTO) {
        log.debug("Request to save ShopUser : {}", shopUserDTO);
        ShopUser shopUser = shopUserMapper.toEntity(shopUserDTO);
        shopUser = shopUserRepository.save(shopUser);
        return shopUserMapper.toDto(shopUser);
    }

    /**
     * Get all the shopUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShopUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShopUsers");
        return shopUserRepository.findAll(pageable)
            .map(shopUserMapper::toDto);
    }


    /**
     * Get one shopUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShopUserDTO> findOne(Long id) {
        log.debug("Request to get ShopUser : {}", id);
        return shopUserRepository.findById(id)
            .map(shopUserMapper::toDto);
    }

    /**
     * Delete the shopUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShopUser : {}", id);
        shopUserRepository.deleteById(id);
    }
}
