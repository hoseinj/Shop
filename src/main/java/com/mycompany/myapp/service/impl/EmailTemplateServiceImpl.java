package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EmailTemplateService;
import com.mycompany.myapp.domain.EmailTemplate;
import com.mycompany.myapp.repository.EmailTemplateRepository;
import com.mycompany.myapp.service.dto.EmailTemplateDTO;
import com.mycompany.myapp.service.mapper.EmailTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EmailTemplate}.
 */
@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final Logger log = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

    private final EmailTemplateRepository emailTemplateRepository;

    private final EmailTemplateMapper emailTemplateMapper;

    public EmailTemplateServiceImpl(EmailTemplateRepository emailTemplateRepository, EmailTemplateMapper emailTemplateMapper) {
        this.emailTemplateRepository = emailTemplateRepository;
        this.emailTemplateMapper = emailTemplateMapper;
    }

    /**
     * Save a emailTemplate.
     *
     * @param emailTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmailTemplateDTO save(EmailTemplateDTO emailTemplateDTO) {
        log.debug("Request to save EmailTemplate : {}", emailTemplateDTO);
        EmailTemplate emailTemplate = emailTemplateMapper.toEntity(emailTemplateDTO);
        emailTemplate = emailTemplateRepository.save(emailTemplate);
        return emailTemplateMapper.toDto(emailTemplate);
    }

    /**
     * Get all the emailTemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmailTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmailTemplates");
        return emailTemplateRepository.findAll(pageable)
            .map(emailTemplateMapper::toDto);
    }


    /**
     * Get one emailTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmailTemplateDTO> findOne(Long id) {
        log.debug("Request to get EmailTemplate : {}", id);
        return emailTemplateRepository.findById(id)
            .map(emailTemplateMapper::toDto);
    }

    /**
     * Delete the emailTemplate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmailTemplate : {}", id);
        emailTemplateRepository.deleteById(id);
    }
}
