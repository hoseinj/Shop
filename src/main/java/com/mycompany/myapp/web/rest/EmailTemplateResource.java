package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EmailTemplateService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EmailTemplateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.EmailTemplate}.
 */
@RestController
@RequestMapping("/api")
public class EmailTemplateResource {

    private final Logger log = LoggerFactory.getLogger(EmailTemplateResource.class);

    private static final String ENTITY_NAME = "emailTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailTemplateService emailTemplateService;

    public EmailTemplateResource(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    /**
     * {@code POST  /email-templates} : Create a new emailTemplate.
     *
     * @param emailTemplateDTO the emailTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailTemplateDTO, or with status {@code 400 (Bad Request)} if the emailTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-templates")
    public ResponseEntity<EmailTemplateDTO> createEmailTemplate(@RequestBody EmailTemplateDTO emailTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save EmailTemplate : {}", emailTemplateDTO);
        if (emailTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailTemplateDTO result = emailTemplateService.save(emailTemplateDTO);
        return ResponseEntity.created(new URI("/api/email-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-templates} : Updates an existing emailTemplate.
     *
     * @param emailTemplateDTO the emailTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the emailTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-templates")
    public ResponseEntity<EmailTemplateDTO> updateEmailTemplate(@RequestBody EmailTemplateDTO emailTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update EmailTemplate : {}", emailTemplateDTO);
        if (emailTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailTemplateDTO result = emailTemplateService.save(emailTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emailTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /email-templates} : get all the emailTemplates.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailTemplates in body.
     */
    @GetMapping("/email-templates")
    public ResponseEntity<List<EmailTemplateDTO>> getAllEmailTemplates(Pageable pageable) {
        log.debug("REST request to get a page of EmailTemplates");
        Page<EmailTemplateDTO> page = emailTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /email-templates/:id} : get the "id" emailTemplate.
     *
     * @param id the id of the emailTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-templates/{id}")
    public ResponseEntity<EmailTemplateDTO> getEmailTemplate(@PathVariable Long id) {
        log.debug("REST request to get EmailTemplate : {}", id);
        Optional<EmailTemplateDTO> emailTemplateDTO = emailTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailTemplateDTO);
    }

    /**
     * {@code DELETE  /email-templates/:id} : delete the "id" emailTemplate.
     *
     * @param id the id of the emailTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-templates/{id}")
    public ResponseEntity<Void> deleteEmailTemplate(@PathVariable Long id) {
        log.debug("REST request to delete EmailTemplate : {}", id);
        emailTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
