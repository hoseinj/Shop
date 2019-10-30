package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CategoryDescription;
import com.mycompany.myapp.repository.CategoryDescriptionRepository;
import com.mycompany.myapp.service.CategoryDescriptionService;
import com.mycompany.myapp.service.dto.CategoryDescriptionDTO;
import com.mycompany.myapp.service.mapper.CategoryDescriptionMapper;
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
 * Integration tests for the {@link CategoryDescriptionResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CategoryDescriptionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_META_DESCROPTION = "AAAAAAAAAA";
    private static final String UPDATED_META_DESCROPTION = "BBBBBBBBBB";

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
    private CategoryDescriptionRepository categoryDescriptionRepository;

    @Autowired
    private CategoryDescriptionMapper categoryDescriptionMapper;

    @Autowired
    private CategoryDescriptionService categoryDescriptionService;

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

    private MockMvc restCategoryDescriptionMockMvc;

    private CategoryDescription categoryDescription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoryDescriptionResource categoryDescriptionResource = new CategoryDescriptionResource(categoryDescriptionService);
        this.restCategoryDescriptionMockMvc = MockMvcBuilders.standaloneSetup(categoryDescriptionResource)
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
    public static CategoryDescription createEntity(EntityManager em) {
        CategoryDescription categoryDescription = new CategoryDescription()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .metaDescroption(DEFAULT_META_DESCROPTION)
            .metaKeyword(DEFAULT_META_KEYWORD)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return categoryDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryDescription createUpdatedEntity(EntityManager em) {
        CategoryDescription categoryDescription = new CategoryDescription()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .metaDescroption(UPDATED_META_DESCROPTION)
            .metaKeyword(UPDATED_META_KEYWORD)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return categoryDescription;
    }

    @BeforeEach
    public void initTest() {
        categoryDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoryDescription() throws Exception {
        int databaseSizeBeforeCreate = categoryDescriptionRepository.findAll().size();

        // Create the CategoryDescription
        CategoryDescriptionDTO categoryDescriptionDTO = categoryDescriptionMapper.toDto(categoryDescription);
        restCategoryDescriptionMockMvc.perform(post("/api/category-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoryDescription in the database
        List<CategoryDescription> categoryDescriptionList = categoryDescriptionRepository.findAll();
        assertThat(categoryDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryDescription testCategoryDescription = categoryDescriptionList.get(categoryDescriptionList.size() - 1);
        assertThat(testCategoryDescription.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCategoryDescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategoryDescription.getMetaDescroption()).isEqualTo(DEFAULT_META_DESCROPTION);
        assertThat(testCategoryDescription.getMetaKeyword()).isEqualTo(DEFAULT_META_KEYWORD);
        assertThat(testCategoryDescription.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCategoryDescription.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCategoryDescription.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCategoryDescription.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCategoryDescription.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCategoryDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryDescriptionRepository.findAll().size();

        // Create the CategoryDescription with an existing ID
        categoryDescription.setId(1L);
        CategoryDescriptionDTO categoryDescriptionDTO = categoryDescriptionMapper.toDto(categoryDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryDescriptionMockMvc.perform(post("/api/category-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryDescription in the database
        List<CategoryDescription> categoryDescriptionList = categoryDescriptionRepository.findAll();
        assertThat(categoryDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategoryDescriptions() throws Exception {
        // Initialize the database
        categoryDescriptionRepository.saveAndFlush(categoryDescription);

        // Get all the categoryDescriptionList
        restCategoryDescriptionMockMvc.perform(get("/api/category-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].metaDescroption").value(hasItem(DEFAULT_META_DESCROPTION)))
            .andExpect(jsonPath("$.[*].metaKeyword").value(hasItem(DEFAULT_META_KEYWORD)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCategoryDescription() throws Exception {
        // Initialize the database
        categoryDescriptionRepository.saveAndFlush(categoryDescription);

        // Get the categoryDescription
        restCategoryDescriptionMockMvc.perform(get("/api/category-descriptions/{id}", categoryDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoryDescription.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.metaDescroption").value(DEFAULT_META_DESCROPTION))
            .andExpect(jsonPath("$.metaKeyword").value(DEFAULT_META_KEYWORD))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCategoryDescription() throws Exception {
        // Get the categoryDescription
        restCategoryDescriptionMockMvc.perform(get("/api/category-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryDescription() throws Exception {
        // Initialize the database
        categoryDescriptionRepository.saveAndFlush(categoryDescription);

        int databaseSizeBeforeUpdate = categoryDescriptionRepository.findAll().size();

        // Update the categoryDescription
        CategoryDescription updatedCategoryDescription = categoryDescriptionRepository.findById(categoryDescription.getId()).get();
        // Disconnect from session so that the updates on updatedCategoryDescription are not directly saved in db
        em.detach(updatedCategoryDescription);
        updatedCategoryDescription
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .metaDescroption(UPDATED_META_DESCROPTION)
            .metaKeyword(UPDATED_META_KEYWORD)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CategoryDescriptionDTO categoryDescriptionDTO = categoryDescriptionMapper.toDto(updatedCategoryDescription);

        restCategoryDescriptionMockMvc.perform(put("/api/category-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the CategoryDescription in the database
        List<CategoryDescription> categoryDescriptionList = categoryDescriptionRepository.findAll();
        assertThat(categoryDescriptionList).hasSize(databaseSizeBeforeUpdate);
        CategoryDescription testCategoryDescription = categoryDescriptionList.get(categoryDescriptionList.size() - 1);
        assertThat(testCategoryDescription.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCategoryDescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategoryDescription.getMetaDescroption()).isEqualTo(UPDATED_META_DESCROPTION);
        assertThat(testCategoryDescription.getMetaKeyword()).isEqualTo(UPDATED_META_KEYWORD);
        assertThat(testCategoryDescription.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCategoryDescription.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCategoryDescription.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCategoryDescription.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCategoryDescription.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoryDescription() throws Exception {
        int databaseSizeBeforeUpdate = categoryDescriptionRepository.findAll().size();

        // Create the CategoryDescription
        CategoryDescriptionDTO categoryDescriptionDTO = categoryDescriptionMapper.toDto(categoryDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryDescriptionMockMvc.perform(put("/api/category-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryDescription in the database
        List<CategoryDescription> categoryDescriptionList = categoryDescriptionRepository.findAll();
        assertThat(categoryDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoryDescription() throws Exception {
        // Initialize the database
        categoryDescriptionRepository.saveAndFlush(categoryDescription);

        int databaseSizeBeforeDelete = categoryDescriptionRepository.findAll().size();

        // Delete the categoryDescription
        restCategoryDescriptionMockMvc.perform(delete("/api/category-descriptions/{id}", categoryDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoryDescription> categoryDescriptionList = categoryDescriptionRepository.findAll();
        assertThat(categoryDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryDescription.class);
        CategoryDescription categoryDescription1 = new CategoryDescription();
        categoryDescription1.setId(1L);
        CategoryDescription categoryDescription2 = new CategoryDescription();
        categoryDescription2.setId(categoryDescription1.getId());
        assertThat(categoryDescription1).isEqualTo(categoryDescription2);
        categoryDescription2.setId(2L);
        assertThat(categoryDescription1).isNotEqualTo(categoryDescription2);
        categoryDescription1.setId(null);
        assertThat(categoryDescription1).isNotEqualTo(categoryDescription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryDescriptionDTO.class);
        CategoryDescriptionDTO categoryDescriptionDTO1 = new CategoryDescriptionDTO();
        categoryDescriptionDTO1.setId(1L);
        CategoryDescriptionDTO categoryDescriptionDTO2 = new CategoryDescriptionDTO();
        assertThat(categoryDescriptionDTO1).isNotEqualTo(categoryDescriptionDTO2);
        categoryDescriptionDTO2.setId(categoryDescriptionDTO1.getId());
        assertThat(categoryDescriptionDTO1).isEqualTo(categoryDescriptionDTO2);
        categoryDescriptionDTO2.setId(2L);
        assertThat(categoryDescriptionDTO1).isNotEqualTo(categoryDescriptionDTO2);
        categoryDescriptionDTO1.setId(null);
        assertThat(categoryDescriptionDTO1).isNotEqualTo(categoryDescriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(categoryDescriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(categoryDescriptionMapper.fromId(null)).isNull();
    }
}
