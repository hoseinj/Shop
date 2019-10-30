package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BannerGroupService;
import com.mycompany.myapp.domain.BannerGroup;
import com.mycompany.myapp.repository.BannerGroupRepository;
import com.mycompany.myapp.service.dto.BannerGroupDTO;
import com.mycompany.myapp.service.mapper.BannerGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BannerGroup}.
 */
@Service
@Transactional
public class BannerGroupServiceImpl implements BannerGroupService {

    private final Logger log = LoggerFactory.getLogger(BannerGroupServiceImpl.class);

    private final BannerGroupRepository bannerGroupRepository;

    private final BannerGroupMapper bannerGroupMapper;

    public BannerGroupServiceImpl(BannerGroupRepository bannerGroupRepository, BannerGroupMapper bannerGroupMapper) {
        this.bannerGroupRepository = bannerGroupRepository;
        this.bannerGroupMapper = bannerGroupMapper;
    }

    /**
     * Save a bannerGroup.
     *
     * @param bannerGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BannerGroupDTO save(BannerGroupDTO bannerGroupDTO) {
        log.debug("Request to save BannerGroup : {}", bannerGroupDTO);
        BannerGroup bannerGroup = bannerGroupMapper.toEntity(bannerGroupDTO);
        bannerGroup = bannerGroupRepository.save(bannerGroup);
        return bannerGroupMapper.toDto(bannerGroup);
    }

    /**
     * Get all the bannerGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BannerGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BannerGroups");
        return bannerGroupRepository.findAll(pageable)
            .map(bannerGroupMapper::toDto);
    }


    /**
     * Get one bannerGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BannerGroupDTO> findOne(Long id) {
        log.debug("Request to get BannerGroup : {}", id);
        return bannerGroupRepository.findById(id)
            .map(bannerGroupMapper::toDto);
    }

    /**
     * Delete the bannerGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BannerGroup : {}", id);
        bannerGroupRepository.deleteById(id);
    }
}
