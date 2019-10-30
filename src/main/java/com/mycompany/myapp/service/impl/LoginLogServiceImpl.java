package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LoginLogService;
import com.mycompany.myapp.domain.LoginLog;
import com.mycompany.myapp.repository.LoginLogRepository;
import com.mycompany.myapp.service.dto.LoginLogDTO;
import com.mycompany.myapp.service.mapper.LoginLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LoginLog}.
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {

    private final Logger log = LoggerFactory.getLogger(LoginLogServiceImpl.class);

    private final LoginLogRepository loginLogRepository;

    private final LoginLogMapper loginLogMapper;

    public LoginLogServiceImpl(LoginLogRepository loginLogRepository, LoginLogMapper loginLogMapper) {
        this.loginLogRepository = loginLogRepository;
        this.loginLogMapper = loginLogMapper;
    }

    /**
     * Save a loginLog.
     *
     * @param loginLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LoginLogDTO save(LoginLogDTO loginLogDTO) {
        log.debug("Request to save LoginLog : {}", loginLogDTO);
        LoginLog loginLog = loginLogMapper.toEntity(loginLogDTO);
        loginLog = loginLogRepository.save(loginLog);
        return loginLogMapper.toDto(loginLog);
    }

    /**
     * Get all the loginLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LoginLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoginLogs");
        return loginLogRepository.findAll(pageable)
            .map(loginLogMapper::toDto);
    }


    /**
     * Get one loginLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LoginLogDTO> findOne(Long id) {
        log.debug("Request to get LoginLog : {}", id);
        return loginLogRepository.findById(id)
            .map(loginLogMapper::toDto);
    }

    /**
     * Delete the loginLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LoginLog : {}", id);
        loginLogRepository.deleteById(id);
    }
}
