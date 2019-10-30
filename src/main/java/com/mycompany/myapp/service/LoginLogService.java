package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LoginLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.LoginLog}.
 */
public interface LoginLogService {

    /**
     * Save a loginLog.
     *
     * @param loginLogDTO the entity to save.
     * @return the persisted entity.
     */
    LoginLogDTO save(LoginLogDTO loginLogDTO);

    /**
     * Get all the loginLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoginLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" loginLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoginLogDTO> findOne(Long id);

    /**
     * Delete the "id" loginLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
