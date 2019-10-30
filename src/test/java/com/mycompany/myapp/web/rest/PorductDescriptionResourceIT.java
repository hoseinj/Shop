package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.PorductDescription;
import com.mycompany.myapp.repository.PorductDescriptionRepository;
import com.mycompany.myapp.service.PorductDescriptionService;
import com.mycompany.myapp.service.dto.PorductDescriptionDTO;
import com.mycompany.myapp.service.mapper.PorductDescriptionMapper;
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
 * Integration tests for the {@link PorductDescriptionResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class PorductDescriptionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_META_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_META_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_META_KEYWORD = "AAAAAAAAAA";
    private static final String UPDATED_META_KEYWORD = "BBBBBBBBBB";

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
    private PorductDescriptionRepository porductDescriptionRepository;

    @Autowired
    private PorductDescriptionMapper porductDescriptionMapper;

    @Autowired
    private PorductDescriptionService porductDescriptionService;

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

    private MockMvc restPorductDescriptionMockMvc;

    private PorductDescription porductDescription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PorductDescriptionResource porductDescriptionResource = new PorductDescriptionResource(porductDescriptionService);
        this.restPorductDescriptionMockMvc = MockMvcBuilders.standaloneSetup(porductDescriptionResource)
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
    public static PorductDescription createEntity(EntityManager em) {
        PorductDescription porductDescription = new PorductDescription()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .metaDescription(DEFAULT_META_DESCRIPTION)
            .metaKeyword(DEFAULT_META_KEYWORD)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return porductDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PorductDescription createUpdatedEntity(EntityManager em) {
        PorductDescription porductDescription = new PorductDescription()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .metaDescription(UPDATED_META_DESCRIPTION)
            .metaKeyword(UPDATED_META_KEYWORD)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return porductDescription;
    }

    @BeforeEach
    public void initTest() {
        porductDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createPorductDescription() throws Exception {
        int databaseSizeBeforeCreate = porductDescriptionRepository.findAll().size();

        // Create the PorductDescription
        PorductDescriptionDTO porductDescriptionDTO = porductDescriptionMapper.toDto(porductDescription);
        restPorductDescriptionMockMvc.perform(post("/api/porduct-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(porductDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the PorductDescription in the database
        List<PorductDescription> porductDescriptionList = porductDescriptionRepository.findAll();
        assertThat(porductDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        PorductDescription testPorductDescription = porductDescriptionList.get(porductDescriptionList.size() - 1);
        assertThat(testPorductDescription.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPorductDescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPorductDescription.getMetaDescription()).isEqualTo(DEFAULT_META_DESCRIPTION);
        assertThat(testPorductDescription.getMetaKeyword()).isEqualTo(DEFAULT_META_KEYWORD);
        assertThat(testPorductDescription.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testPorductDescription.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPorductDescription.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testPorductDescription.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPorductDescription.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createPorductDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = porductDescriptionRepository.findAll().size();

        // Create the PorductDescription with an existing ID
        porductDescription.setId(1L);
        PorductDescriptionDTO porductDescriptionDTO = porductDescriptionMapper.toDto(porductDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPorductDescriptionMockMvc.perform(post("/api/porduct-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(porductDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PorductDescription in the database
        List<PorductDescription> porductDescriptionList = porductDescriptionRepository.findAll();
        assertThat(porductDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPorductDescriptions() throws Exception {
        // Initialize the database
        porductDescriptionRepository.saveAndFlush(porductDescription);

        // Get all the porductDescriptionList
        restPorductDescriptionMockMvc.perform(get("/api/porduct-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(porductDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].metaDescription").value(hasItem(DEFAULT_META_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].metaKeyword").value(hasItem(DEFAULT_META_KEYWORD)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getPorductDescription() throws Exception {
        // Initialize the database
        porductDescriptionRepository.saveAndFlush(porductDescription);

        // Get the porductDescription
        restPorductDescriptionMockMvc.perform(get("/api/porduct-descriptions/{id}", porductDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(porductDescription.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.metaDescription").value(DEFAULT_META_DESCRIPTION))
            .andExpect(jsonPath("$.metaKeyword").value(DEFAULT_META_KEYWORD))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingPorductDescription() throws Exception {
        // Get the porductDescription
        restPorductDescriptionMockMvc.perform(get("/api/porduct-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePorductDescription() throws Exception {
        // Initialize the database
        porductDescriptionRepository.saveAndFlush(porductDescription);

        int databaseSizeBeforeUpdate = porductDescriptionRepository.findAll().size();

        // Update the porductDescription
        PorductDescription updatedPorductDescription = porductDescriptionRepository.findById(porductDescription.getId()).get();
        // Disconnect from session so that the updates on updatedPorductDescription are not directly saved in db
        em.detach(updatedPorductDescription);
        updatedPorductDescription
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .metaDescription(UPDATED_META_DESCRIPTION)
            .metaKeyword(UPDATED_META_KEYWORD)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        PorductDescriptionDTO porductDescriptionDTO = porductDescriptionMapper.toDto(updatedPorductDescription);

        restPorductDescriptionMockMvc.perform(put("/api/porduct-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(porductDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the PorductDescription in the database
        List<PorductDescription> porductDescriptionList = porductDescriptionRepository.findAll();
        assertThat(porductDescriptionList).hasSize(databaseSizeBeforeUpdate);
        PorductDescription testPorductDescription = porductDescriptionList.get(porductDescriptionList.size() - 1);
        assertThat(testPorductDescription.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPorductDescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPorductDescription.getMetaDescription()).isEqualTo(UPDATED_META_DESCRIPTION);
        assertThat(testPorductDescription.getMetaKeyword()).isEqualTo(UPDATED_META_KEYWORD);
        assertThat(testPorductDescription.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPorductDescription.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPorductDescription.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testPorductDescription.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPorductDescription.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPorductDescription() throws Exception {
        int databaseSizeBeforeUpdate = porductDescriptionRepository.findAll().size();

        // Create the PorductDescription
        PorductDescriptionDTO porductDescriptionDTO = porductDescriptionMapper.toDto(porductDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPorductDescriptionMockMvc.perform(put("/api/porduct-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(porductDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PorductDescription in the database
        List<PorductDescription> porductDescriptionList = porductDescriptionRepository.findAll();
        assertThat(porductDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePorductDescription() throws Exception {
        // Initialize the database
        porductDescriptionRepository.saveAndFlush(porductDescription);

        int databaseSizeBeforeDelete = porductDescriptionRepository.findAll().size();

        // Delete the porductDescription
        restPorductDescriptionMockMvc.perform(delete("/api/porduct-descriptions/{id}", porductDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PorductDescription> porductDescriptionList = porductDescriptionRepository.findAll();
        assertThat(porductDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PorductDescription.class);
        PorductDescription porductDescription1 = new PorductDescription();
        porductDescription1.setId(1L);
        PorductDescription porductDescription2 = new PorductDescription();
        porductDescription2.setId(porductDescription1.getId());
        assertThat(porductDescription1).isEqualTo(porductDescription2);
        porductDescription2.setId(2L);
        assertThat(porductDescription1).isNotEqualTo(porductDescription2);
        porductDescription1.setId(null);
        assertThat(porductDescription1).isNotEqualTo(porductDescription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PorductDescriptionDTO.class);
        PorductDescriptionDTO porductDescriptionDTO1 = new PorductDescriptionDTO();
        porductDescriptionDTO1.setId(1L);
        PorductDescriptionDTO porductDescriptionDTO2 = new PorductDescriptionDTO();
        assertThat(porductDescriptionDTO1).isNotEqualTo(porductDescriptionDTO2);
        porductDescriptionDTO2.setId(porductDescriptionDTO1.getId());
        assertThat(porductDescriptionDTO1).isEqualTo(porductDescriptionDTO2);
        porductDescriptionDTO2.setId(2L);
        assertThat(porductDescriptionDTO1).isNotEqualTo(porductDescriptionDTO2);
        porductDescriptionDTO1.setId(null);
        assertThat(porductDescriptionDTO1).isNotEqualTo(porductDescriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(porductDescriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(porductDescriptionMapper.fromId(null)).isNull();
    }
}
