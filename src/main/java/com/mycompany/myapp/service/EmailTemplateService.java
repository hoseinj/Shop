package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EmailTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EmailTemplate}.
 */
public interface EmailTemplateService {

    /**
     * Save a emailTemplate.
     *
     * @param emailTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    EmailTemplateDTO save(EmailTemplateDTO emailTemplateDTO);

    /**
     * Get all the emailTemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmailTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" emailTemplate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmailTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" emailTemplate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
