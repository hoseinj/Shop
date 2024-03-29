package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SettingService;
import com.mycompany.myapp.domain.Setting;
import com.mycompany.myapp.repository.SettingRepository;
import com.mycompany.myapp.service.dto.SettingDTO;
import com.mycompany.myapp.service.mapper.SettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Setting}.
 */
@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    private final Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);

    private final SettingRepository settingRepository;

    private final SettingMapper settingMapper;

    public SettingServiceImpl(SettingRepository settingRepository, SettingMapper settingMapper) {
        this.settingRepository = settingRepository;
        this.settingMapper = settingMapper;
    }

    /**
     * Save a setting.
     *
     * @param settingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SettingDTO save(SettingDTO settingDTO) {
        log.debug("Request to save Setting : {}", settingDTO);
        Setting setting = settingMapper.toEntity(settingDTO);
        setting = settingRepository.save(setting);
        return settingMapper.toDto(setting);
    }

    /**
     * Get all the settings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SettingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Settings");
        return settingRepository.findAll(pageable)
            .map(settingMapper::toDto);
    }


    /**
     * Get one setting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SettingDTO> findOne(Long id) {
        log.debug("Request to get Setting : {}", id);
        return settingRepository.findById(id)
            .map(settingMapper::toDto);
    }

    /**
     * Delete the setting by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Setting : {}", id);
        settingRepository.deleteById(id);
    }
}
