package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BannerImageDescriptionService;
import com.mycompany.myapp.domain.BannerImageDescription;
import com.mycompany.myapp.repository.BannerImageDescriptionRepository;
import com.mycompany.myapp.service.dto.BannerImageDescriptionDTO;
import com.mycompany.myapp.service.mapper.BannerImageDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BannerImageDescription}.
 */
@Service
@Transactional
public class BannerImageDescriptionServiceImpl implements BannerImageDescriptionService {

    private final Logger log = LoggerFactory.getLogger(BannerImageDescriptionServiceImpl.class);

    private final BannerImageDescriptionRepository bannerImageDescriptionRepository;

    private final BannerImageDescriptionMapper bannerImageDescriptionMapper;

    public BannerImageDescriptionServiceImpl(BannerImageDescriptionRepository bannerImageDescriptionRepository, BannerImageDescriptionMapper bannerImageDescriptionMapper) {
        this.bannerImageDescriptionRepository = bannerImageDescriptionRepository;
        this.bannerImageDescriptionMapper = bannerImageDescriptionMapper;
    }

    /**
     * Save a bannerImageDescription.
     *
     * @param bannerImageDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BannerImageDescriptionDTO save(BannerImageDescriptionDTO bannerImageDescriptionDTO) {
        log.debug("Request to save BannerImageDescription : {}", bannerImageDescriptionDTO);
        BannerImageDescription bannerImageDescription = bannerImageDescriptionMapper.toEntity(bannerImageDescriptionDTO);
        bannerImageDescription = bannerImageDescriptionRepository.save(bannerImageDescription);
        return bannerImageDescriptionMapper.toDto(bannerImageDescription);
    }

    /**
     * Get all the bannerImageDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BannerImageDescriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BannerImageDescriptions");
        return bannerImageDescriptionRepository.findAll(pageable)
            .map(bannerImageDescriptionMapper::toDto);
    }


    /**
     * Get one bannerImageDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BannerImageDescriptionDTO> findOne(Long id) {
        log.debug("Request to get BannerImageDescription : {}", id);
        return bannerImageDescriptionRepository.findById(id)
            .map(bannerImageDescriptionMapper::toDto);
    }

    /**
     * Delete the bannerImageDescription by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BannerImageDescription : {}", id);
        bannerImageDescriptionRepository.deleteById(id);
    }
}
