package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PageGroupService;
import com.mycompany.myapp.domain.PageGroup;
import com.mycompany.myapp.repository.PageGroupRepository;
import com.mycompany.myapp.service.dto.PageGroupDTO;
import com.mycompany.myapp.service.mapper.PageGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PageGroup}.
 */
@Service
@Transactional
public class PageGroupServiceImpl implements PageGroupService {

    private final Logger log = LoggerFactory.getLogger(PageGroupServiceImpl.class);

    private final PageGroupRepository pageGroupRepository;

    private final PageGroupMapper pageGroupMapper;

    public PageGroupServiceImpl(PageGroupRepository pageGroupRepository, PageGroupMapper pageGroupMapper) {
        this.pageGroupRepository = pageGroupRepository;
        this.pageGroupMapper = pageGroupMapper;
    }

    /**
     * Save a pageGroup.
     *
     * @param pageGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PageGroupDTO save(PageGroupDTO pageGroupDTO) {
        log.debug("Request to save PageGroup : {}", pageGroupDTO);
        PageGroup pageGroup = pageGroupMapper.toEntity(pageGroupDTO);
        pageGroup = pageGroupRepository.save(pageGroup);
        return pageGroupMapper.toDto(pageGroup);
    }

    /**
     * Get all the pageGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PageGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PageGroups");
        return pageGroupRepository.findAll(pageable)
            .map(pageGroupMapper::toDto);
    }


    /**
     * Get one pageGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PageGroupDTO> findOne(Long id) {
        log.debug("Request to get PageGroup : {}", id);
        return pageGroupRepository.findById(id)
            .map(pageGroupMapper::toDto);
    }

    /**
     * Delete the pageGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PageGroup : {}", id);
        pageGroupRepository.deleteById(id);
    }
}
