package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AccessTokenService;
import com.mycompany.myapp.domain.AccessToken;
import com.mycompany.myapp.repository.AccessTokenRepository;
import com.mycompany.myapp.service.dto.AccessTokenDTO;
import com.mycompany.myapp.service.mapper.AccessTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccessToken}.
 */
@Service
@Transactional
public class AccessTokenServiceImpl implements AccessTokenService {

    private final Logger log = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    private final AccessTokenRepository accessTokenRepository;

    private final AccessTokenMapper accessTokenMapper;

    public AccessTokenServiceImpl(AccessTokenRepository accessTokenRepository, AccessTokenMapper accessTokenMapper) {
        this.accessTokenRepository = accessTokenRepository;
        this.accessTokenMapper = accessTokenMapper;
    }

    /**
     * Save a accessToken.
     *
     * @param accessTokenDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccessTokenDTO save(AccessTokenDTO accessTokenDTO) {
        log.debug("Request to save AccessToken : {}", accessTokenDTO);
        AccessToken accessToken = accessTokenMapper.toEntity(accessTokenDTO);
        accessToken = accessTokenRepository.save(accessToken);
        return accessTokenMapper.toDto(accessToken);
    }

    /**
     * Get all the accessTokens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccessTokenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccessTokens");
        return accessTokenRepository.findAll(pageable)
            .map(accessTokenMapper::toDto);
    }


    /**
     * Get one accessToken by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccessTokenDTO> findOne(Long id) {
        log.debug("Request to get AccessToken : {}", id);
        return accessTokenRepository.findById(id)
            .map(accessTokenMapper::toDto);
    }

    /**
     * Delete the accessToken by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccessToken : {}", id);
        accessTokenRepository.deleteById(id);
    }
}
