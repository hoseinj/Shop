package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.EmailTemplate;
import com.mycompany.myapp.repository.EmailTemplateRepository;
import com.mycompany.myapp.service.EmailTemplateService;
import com.mycompany.myapp.service.dto.EmailTemplateDTO;
import com.mycompany.myapp.service.mapper.EmailTemplateMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EmailTemplateResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class EmailTemplateResourceIT {

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_ACTIVE = 1;
    private static final Integer UPDATED_IS_ACTIVE = 2;

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final Long DEFAULT_MODIFIED_BY = 1L;
    private static final Long UPDATED_MODIFIED_BY = 2L;

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_DATE = "BBBBBBBBBB";

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private EmailTemplateMapper emailTemplateMapper;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEmailTemplateMockMvc;

    private EmailTemplate emailTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmailTemplateResource emailTemplateResource = new EmailTemplateResource(emailTemplateService);
        this.restEmailTemplateMockMvc = MockMvcBuilders.standaloneSetup(emailTemplateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailTemplate createEntity(EntityManager em) {
        EmailTemplate emailTemplate = new EmailTemplate()
            .shortName(DEFAULT_SHORT_NAME)
            .subject(DEFAULT_SUBJECT)
            .message(DEFAULT_MESSAGE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return emailTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailTemplate createUpdatedEntity(EntityManager em) {
        EmailTemplate emailTemplate = new EmailTemplate()
            .shortName(UPDATED_SHORT_NAME)
            .subject(UPDATED_SUBJECT)
            .message(UPDATED_MESSAGE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return emailTemplate;
    }

    @BeforeEach
    public void initTest() {
        emailTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmailTemplate() throws Exception {
        int databaseSizeBeforeCreate = emailTemplateRepository.findAll().size();

        // Create the EmailTemplate
        EmailTemplateDTO emailTemplateDTO = emailTemplateMapper.toDto(emailTemplate);
        restEmailTemplateMockMvc.perform(post("/api/email-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the EmailTemplate in the database
        List<EmailTemplate> emailTemplateList = emailTemplateRepository.findAll();
        assertThat(emailTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        EmailTemplate testEmailTemplate = emailTemplateList.get(emailTemplateList.size() - 1);
        assertThat(testEmailTemplate.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testEmailTemplate.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testEmailTemplate.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testEmailTemplate.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testEmailTemplate.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testEmailTemplate.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testEmailTemplate.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testEmailTemplate.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createEmailTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailTemplateRepository.findAll().size();

        // Create the EmailTemplate with an existing ID
        emailTemplate.setId(1L);
        EmailTemplateDTO emailTemplateDTO = emailTemplateMapper.toDto(emailTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailTemplateMockMvc.perform(post("/api/email-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailTemplate in the database
        List<EmailTemplate> emailTemplateList = emailTemplateRepository.findAll();
        assertThat(emailTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmailTemplates() throws Exception {
        // Initialize the database
        emailTemplateRepository.saveAndFlush(emailTemplate);

        // Get all the emailTemplateList
        restEmailTemplateMockMvc.perform(get("/api/email-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getEmailTemplate() throws Exception {
        // Initialize the database
        emailTemplateRepository.saveAndFlush(emailTemplate);

        // Get the emailTemplate
        restEmailTemplateMockMvc.perform(get("/api/email-templates/{id}", emailTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emailTemplate.getId().intValue()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingEmailTemplate() throws Exception {
        // Get the emailTemplate
        restEmailTemplateMockMvc.perform(get("/api/email-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmailTemplate() throws Exception {
        // Initialize the database
        emailTemplateRepository.saveAndFlush(emailTemplate);

        int databaseSizeBeforeUpdate = emailTemplateRepository.findAll().size();

        // Update the emailTemplate
        EmailTemplate updatedEmailTemplate = emailTemplateRepository.findById(emailTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedEmailTemplate are not directly saved in db
        em.detach(updatedEmailTemplate);
        updatedEmailTemplate
            .shortName(UPDATED_SHORT_NAME)
            .subject(UPDATED_SUBJECT)
            .message(UPDATED_MESSAGE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        EmailTemplateDTO emailTemplateDTO = emailTemplateMapper.toDto(updatedEmailTemplate);

        restEmailTemplateMockMvc.perform(put("/api/email-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the EmailTemplate in the database
        List<EmailTemplate> emailTemplateList = emailTemplateRepository.findAll();
        assertThat(emailTemplateList).hasSize(databaseSizeBeforeUpdate);
        EmailTemplate testEmailTemplate = emailTemplateList.get(emailTemplateList.size() - 1);
        assertThat(testEmailTemplate.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testEmailTemplate.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testEmailTemplate.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testEmailTemplate.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testEmailTemplate.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testEmailTemplate.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testEmailTemplate.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testEmailTemplate.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmailTemplate() throws Exception {
        int databaseSizeBeforeUpdate = emailTemplateRepository.findAll().size();

        // Create the EmailTemplate
        EmailTemplateDTO emailTemplateDTO = emailTemplateMapper.toDto(emailTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmailTemplateMockMvc.perform(put("/api/email-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emailTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailTemplate in the database
        List<EmailTemplate> emailTemplateList = emailTemplateRepository.findAll();
        assertThat(emailTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmailTemplate() throws Exception {
        // Initialize the database
        emailTemplateRepository.saveAndFlush(emailTemplate);

        int databaseSizeBeforeDelete = emailTemplateRepository.findAll().size();

        // Delete the emailTemplate
        restEmailTemplateMockMvc.perform(delete("/api/email-templates/{id}", emailTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmailTemplate> emailTemplateList = emailTemplateRepository.findAll();
        assertThat(emailTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailTemplate.class);
        EmailTemplate emailTemplate1 = new EmailTemplate();
        emailTemplate1.setId(1L);
        EmailTemplate emailTemplate2 = new EmailTemplate();
        emailTemplate2.setId(emailTemplate1.getId());
        assertThat(emailTemplate1).isEqualTo(emailTemplate2);
        emailTemplate2.setId(2L);
        assertThat(emailTemplate1).isNotEqualTo(emailTemplate2);
        emailTemplate1.setId(null);
        assertThat(emailTemplate1).isNotEqualTo(emailTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailTemplateDTO.class);
        EmailTemplateDTO emailTemplateDTO1 = new EmailTemplateDTO();
        emailTemplateDTO1.setId(1L);
        EmailTemplateDTO emailTemplateDTO2 = new EmailTemplateDTO();
        assertThat(emailTemplateDTO1).isNotEqualTo(emailTemplateDTO2);
        emailTemplateDTO2.setId(emailTemplateDTO1.getId());
        assertThat(emailTemplateDTO1).isEqualTo(emailTemplateDTO2);
        emailTemplateDTO2.setId(2L);
        assertThat(emailTemplateDTO1).isNotEqualTo(emailTemplateDTO2);
        emailTemplateDTO1.setId(null);
        assertThat(emailTemplateDTO1).isNotEqualTo(emailTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(emailTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(emailTemplateMapper.fromId(null)).isNull();
    }
}
