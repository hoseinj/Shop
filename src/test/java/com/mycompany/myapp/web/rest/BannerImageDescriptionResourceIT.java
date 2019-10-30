package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.BannerImageDescription;
import com.mycompany.myapp.repository.BannerImageDescriptionRepository;
import com.mycompany.myapp.service.BannerImageDescriptionService;
import com.mycompany.myapp.service.dto.BannerImageDescriptionDTO;
import com.mycompany.myapp.service.mapper.BannerImageDescriptionMapper;
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
 * Integration tests for the {@link BannerImageDescriptionResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class BannerImageDescriptionResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

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
    private BannerImageDescriptionRepository bannerImageDescriptionRepository;

    @Autowired
    private BannerImageDescriptionMapper bannerImageDescriptionMapper;

    @Autowired
    private BannerImageDescriptionService bannerImageDescriptionService;

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

    private MockMvc restBannerImageDescriptionMockMvc;

    private BannerImageDescription bannerImageDescription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BannerImageDescriptionResource bannerImageDescriptionResource = new BannerImageDescriptionResource(bannerImageDescriptionService);
        this.restBannerImageDescriptionMockMvc = MockMvcBuilders.standaloneSetup(bannerImageDescriptionResource)
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
    public static BannerImageDescription createEntity(EntityManager em) {
        BannerImageDescription bannerImageDescription = new BannerImageDescription()
            .title(DEFAULT_TITLE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return bannerImageDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BannerImageDescription createUpdatedEntity(EntityManager em) {
        BannerImageDescription bannerImageDescription = new BannerImageDescription()
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return bannerImageDescription;
    }

    @BeforeEach
    public void initTest() {
        bannerImageDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createBannerImageDescription() throws Exception {
        int databaseSizeBeforeCreate = bannerImageDescriptionRepository.findAll().size();

        // Create the BannerImageDescription
        BannerImageDescriptionDTO bannerImageDescriptionDTO = bannerImageDescriptionMapper.toDto(bannerImageDescription);
        restBannerImageDescriptionMockMvc.perform(post("/api/banner-image-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerImageDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the BannerImageDescription in the database
        List<BannerImageDescription> bannerImageDescriptionList = bannerImageDescriptionRepository.findAll();
        assertThat(bannerImageDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        BannerImageDescription testBannerImageDescription = bannerImageDescriptionList.get(bannerImageDescriptionList.size() - 1);
        assertThat(testBannerImageDescription.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBannerImageDescription.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testBannerImageDescription.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBannerImageDescription.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testBannerImageDescription.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBannerImageDescription.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBannerImageDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bannerImageDescriptionRepository.findAll().size();

        // Create the BannerImageDescription with an existing ID
        bannerImageDescription.setId(1L);
        BannerImageDescriptionDTO bannerImageDescriptionDTO = bannerImageDescriptionMapper.toDto(bannerImageDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBannerImageDescriptionMockMvc.perform(post("/api/banner-image-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerImageDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BannerImageDescription in the database
        List<BannerImageDescription> bannerImageDescriptionList = bannerImageDescriptionRepository.findAll();
        assertThat(bannerImageDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBannerImageDescriptions() throws Exception {
        // Initialize the database
        bannerImageDescriptionRepository.saveAndFlush(bannerImageDescription);

        // Get all the bannerImageDescriptionList
        restBannerImageDescriptionMockMvc.perform(get("/api/banner-image-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bannerImageDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getBannerImageDescription() throws Exception {
        // Initialize the database
        bannerImageDescriptionRepository.saveAndFlush(bannerImageDescription);

        // Get the bannerImageDescription
        restBannerImageDescriptionMockMvc.perform(get("/api/banner-image-descriptions/{id}", bannerImageDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bannerImageDescription.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingBannerImageDescription() throws Exception {
        // Get the bannerImageDescription
        restBannerImageDescriptionMockMvc.perform(get("/api/banner-image-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBannerImageDescription() throws Exception {
        // Initialize the database
        bannerImageDescriptionRepository.saveAndFlush(bannerImageDescription);

        int databaseSizeBeforeUpdate = bannerImageDescriptionRepository.findAll().size();

        // Update the bannerImageDescription
        BannerImageDescription updatedBannerImageDescription = bannerImageDescriptionRepository.findById(bannerImageDescription.getId()).get();
        // Disconnect from session so that the updates on updatedBannerImageDescription are not directly saved in db
        em.detach(updatedBannerImageDescription);
        updatedBannerImageDescription
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        BannerImageDescriptionDTO bannerImageDescriptionDTO = bannerImageDescriptionMapper.toDto(updatedBannerImageDescription);

        restBannerImageDescriptionMockMvc.perform(put("/api/banner-image-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerImageDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the BannerImageDescription in the database
        List<BannerImageDescription> bannerImageDescriptionList = bannerImageDescriptionRepository.findAll();
        assertThat(bannerImageDescriptionList).hasSize(databaseSizeBeforeUpdate);
        BannerImageDescription testBannerImageDescription = bannerImageDescriptionList.get(bannerImageDescriptionList.size() - 1);
        assertThat(testBannerImageDescription.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBannerImageDescription.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testBannerImageDescription.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBannerImageDescription.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBannerImageDescription.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBannerImageDescription.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBannerImageDescription() throws Exception {
        int databaseSizeBeforeUpdate = bannerImageDescriptionRepository.findAll().size();

        // Create the BannerImageDescription
        BannerImageDescriptionDTO bannerImageDescriptionDTO = bannerImageDescriptionMapper.toDto(bannerImageDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBannerImageDescriptionMockMvc.perform(put("/api/banner-image-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerImageDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BannerImageDescription in the database
        List<BannerImageDescription> bannerImageDescriptionList = bannerImageDescriptionRepository.findAll();
        assertThat(bannerImageDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBannerImageDescription() throws Exception {
        // Initialize the database
        bannerImageDescriptionRepository.saveAndFlush(bannerImageDescription);

        int databaseSizeBeforeDelete = bannerImageDescriptionRepository.findAll().size();

        // Delete the bannerImageDescription
        restBannerImageDescriptionMockMvc.perform(delete("/api/banner-image-descriptions/{id}", bannerImageDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BannerImageDescription> bannerImageDescriptionList = bannerImageDescriptionRepository.findAll();
        assertThat(bannerImageDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BannerImageDescription.class);
        BannerImageDescription bannerImageDescription1 = new BannerImageDescription();
        bannerImageDescription1.setId(1L);
        BannerImageDescription bannerImageDescription2 = new BannerImageDescription();
        bannerImageDescription2.setId(bannerImageDescription1.getId());
        assertThat(bannerImageDescription1).isEqualTo(bannerImageDescription2);
        bannerImageDescription2.setId(2L);
        assertThat(bannerImageDescription1).isNotEqualTo(bannerImageDescription2);
        bannerImageDescription1.setId(null);
        assertThat(bannerImageDescription1).isNotEqualTo(bannerImageDescription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BannerImageDescriptionDTO.class);
        BannerImageDescriptionDTO bannerImageDescriptionDTO1 = new BannerImageDescriptionDTO();
        bannerImageDescriptionDTO1.setId(1L);
        BannerImageDescriptionDTO bannerImageDescriptionDTO2 = new BannerImageDescriptionDTO();
        assertThat(bannerImageDescriptionDTO1).isNotEqualTo(bannerImageDescriptionDTO2);
        bannerImageDescriptionDTO2.setId(bannerImageDescriptionDTO1.getId());
        assertThat(bannerImageDescriptionDTO1).isEqualTo(bannerImageDescriptionDTO2);
        bannerImageDescriptionDTO2.setId(2L);
        assertThat(bannerImageDescriptionDTO1).isNotEqualTo(bannerImageDescriptionDTO2);
        bannerImageDescriptionDTO1.setId(null);
        assertThat(bannerImageDescriptionDTO1).isNotEqualTo(bannerImageDescriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bannerImageDescriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bannerImageDescriptionMapper.fromId(null)).isNull();
    }
}
