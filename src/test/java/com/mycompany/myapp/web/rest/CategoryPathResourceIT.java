package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CategoryPath;
import com.mycompany.myapp.repository.CategoryPathRepository;
import com.mycompany.myapp.service.CategoryPathService;
import com.mycompany.myapp.service.dto.CategoryPathDTO;
import com.mycompany.myapp.service.mapper.CategoryPathMapper;
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
 * Integration tests for the {@link CategoryPathResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CategoryPathResourceIT {

    private static final Long DEFAULT_LEVEL = 1L;
    private static final Long UPDATED_LEVEL = 2L;

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
    private CategoryPathRepository categoryPathRepository;

    @Autowired
    private CategoryPathMapper categoryPathMapper;

    @Autowired
    private CategoryPathService categoryPathService;

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

    private MockMvc restCategoryPathMockMvc;

    private CategoryPath categoryPath;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoryPathResource categoryPathResource = new CategoryPathResource(categoryPathService);
        this.restCategoryPathMockMvc = MockMvcBuilders.standaloneSetup(categoryPathResource)
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
    public static CategoryPath createEntity(EntityManager em) {
        CategoryPath categoryPath = new CategoryPath()
            .level(DEFAULT_LEVEL)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return categoryPath;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryPath createUpdatedEntity(EntityManager em) {
        CategoryPath categoryPath = new CategoryPath()
            .level(UPDATED_LEVEL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return categoryPath;
    }

    @BeforeEach
    public void initTest() {
        categoryPath = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoryPath() throws Exception {
        int databaseSizeBeforeCreate = categoryPathRepository.findAll().size();

        // Create the CategoryPath
        CategoryPathDTO categoryPathDTO = categoryPathMapper.toDto(categoryPath);
        restCategoryPathMockMvc.perform(post("/api/category-paths")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryPathDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoryPath in the database
        List<CategoryPath> categoryPathList = categoryPathRepository.findAll();
        assertThat(categoryPathList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryPath testCategoryPath = categoryPathList.get(categoryPathList.size() - 1);
        assertThat(testCategoryPath.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCategoryPath.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCategoryPath.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCategoryPath.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCategoryPath.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCategoryPath.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCategoryPathWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryPathRepository.findAll().size();

        // Create the CategoryPath with an existing ID
        categoryPath.setId(1L);
        CategoryPathDTO categoryPathDTO = categoryPathMapper.toDto(categoryPath);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryPathMockMvc.perform(post("/api/category-paths")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryPathDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryPath in the database
        List<CategoryPath> categoryPathList = categoryPathRepository.findAll();
        assertThat(categoryPathList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategoryPaths() throws Exception {
        // Initialize the database
        categoryPathRepository.saveAndFlush(categoryPath);

        // Get all the categoryPathList
        restCategoryPathMockMvc.perform(get("/api/category-paths?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryPath.getId().intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCategoryPath() throws Exception {
        // Initialize the database
        categoryPathRepository.saveAndFlush(categoryPath);

        // Get the categoryPath
        restCategoryPathMockMvc.perform(get("/api/category-paths/{id}", categoryPath.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoryPath.getId().intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCategoryPath() throws Exception {
        // Get the categoryPath
        restCategoryPathMockMvc.perform(get("/api/category-paths/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryPath() throws Exception {
        // Initialize the database
        categoryPathRepository.saveAndFlush(categoryPath);

        int databaseSizeBeforeUpdate = categoryPathRepository.findAll().size();

        // Update the categoryPath
        CategoryPath updatedCategoryPath = categoryPathRepository.findById(categoryPath.getId()).get();
        // Disconnect from session so that the updates on updatedCategoryPath are not directly saved in db
        em.detach(updatedCategoryPath);
        updatedCategoryPath
            .level(UPDATED_LEVEL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CategoryPathDTO categoryPathDTO = categoryPathMapper.toDto(updatedCategoryPath);

        restCategoryPathMockMvc.perform(put("/api/category-paths")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryPathDTO)))
            .andExpect(status().isOk());

        // Validate the CategoryPath in the database
        List<CategoryPath> categoryPathList = categoryPathRepository.findAll();
        assertThat(categoryPathList).hasSize(databaseSizeBeforeUpdate);
        CategoryPath testCategoryPath = categoryPathList.get(categoryPathList.size() - 1);
        assertThat(testCategoryPath.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCategoryPath.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCategoryPath.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCategoryPath.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCategoryPath.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCategoryPath.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoryPath() throws Exception {
        int databaseSizeBeforeUpdate = categoryPathRepository.findAll().size();

        // Create the CategoryPath
        CategoryPathDTO categoryPathDTO = categoryPathMapper.toDto(categoryPath);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryPathMockMvc.perform(put("/api/category-paths")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryPathDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryPath in the database
        List<CategoryPath> categoryPathList = categoryPathRepository.findAll();
        assertThat(categoryPathList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoryPath() throws Exception {
        // Initialize the database
        categoryPathRepository.saveAndFlush(categoryPath);

        int databaseSizeBeforeDelete = categoryPathRepository.findAll().size();

        // Delete the categoryPath
        restCategoryPathMockMvc.perform(delete("/api/category-paths/{id}", categoryPath.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoryPath> categoryPathList = categoryPathRepository.findAll();
        assertThat(categoryPathList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryPath.class);
        CategoryPath categoryPath1 = new CategoryPath();
        categoryPath1.setId(1L);
        CategoryPath categoryPath2 = new CategoryPath();
        categoryPath2.setId(categoryPath1.getId());
        assertThat(categoryPath1).isEqualTo(categoryPath2);
        categoryPath2.setId(2L);
        assertThat(categoryPath1).isNotEqualTo(categoryPath2);
        categoryPath1.setId(null);
        assertThat(categoryPath1).isNotEqualTo(categoryPath2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryPathDTO.class);
        CategoryPathDTO categoryPathDTO1 = new CategoryPathDTO();
        categoryPathDTO1.setId(1L);
        CategoryPathDTO categoryPathDTO2 = new CategoryPathDTO();
        assertThat(categoryPathDTO1).isNotEqualTo(categoryPathDTO2);
        categoryPathDTO2.setId(categoryPathDTO1.getId());
        assertThat(categoryPathDTO1).isEqualTo(categoryPathDTO2);
        categoryPathDTO2.setId(2L);
        assertThat(categoryPathDTO1).isNotEqualTo(categoryPathDTO2);
        categoryPathDTO1.setId(null);
        assertThat(categoryPathDTO1).isNotEqualTo(categoryPathDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(categoryPathMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(categoryPathMapper.fromId(null)).isNull();
    }
}
