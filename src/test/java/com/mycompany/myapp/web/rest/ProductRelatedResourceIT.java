package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ProductRelated;
import com.mycompany.myapp.repository.ProductRelatedRepository;
import com.mycompany.myapp.service.ProductRelatedService;
import com.mycompany.myapp.service.dto.ProductRelatedDTO;
import com.mycompany.myapp.service.mapper.ProductRelatedMapper;
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
 * Integration tests for the {@link ProductRelatedResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ProductRelatedResourceIT {

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
    private ProductRelatedRepository productRelatedRepository;

    @Autowired
    private ProductRelatedMapper productRelatedMapper;

    @Autowired
    private ProductRelatedService productRelatedService;

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

    private MockMvc restProductRelatedMockMvc;

    private ProductRelated productRelated;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductRelatedResource productRelatedResource = new ProductRelatedResource(productRelatedService);
        this.restProductRelatedMockMvc = MockMvcBuilders.standaloneSetup(productRelatedResource)
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
    public static ProductRelated createEntity(EntityManager em) {
        ProductRelated productRelated = new ProductRelated()
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return productRelated;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductRelated createUpdatedEntity(EntityManager em) {
        ProductRelated productRelated = new ProductRelated()
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return productRelated;
    }

    @BeforeEach
    public void initTest() {
        productRelated = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductRelated() throws Exception {
        int databaseSizeBeforeCreate = productRelatedRepository.findAll().size();

        // Create the ProductRelated
        ProductRelatedDTO productRelatedDTO = productRelatedMapper.toDto(productRelated);
        restProductRelatedMockMvc.perform(post("/api/product-relateds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRelatedDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductRelated in the database
        List<ProductRelated> productRelatedList = productRelatedRepository.findAll();
        assertThat(productRelatedList).hasSize(databaseSizeBeforeCreate + 1);
        ProductRelated testProductRelated = productRelatedList.get(productRelatedList.size() - 1);
        assertThat(testProductRelated.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProductRelated.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductRelated.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProductRelated.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductRelated.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductRelatedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRelatedRepository.findAll().size();

        // Create the ProductRelated with an existing ID
        productRelated.setId(1L);
        ProductRelatedDTO productRelatedDTO = productRelatedMapper.toDto(productRelated);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductRelatedMockMvc.perform(post("/api/product-relateds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRelatedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRelated in the database
        List<ProductRelated> productRelatedList = productRelatedRepository.findAll();
        assertThat(productRelatedList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductRelateds() throws Exception {
        // Initialize the database
        productRelatedRepository.saveAndFlush(productRelated);

        // Get all the productRelatedList
        restProductRelatedMockMvc.perform(get("/api/product-relateds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productRelated.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getProductRelated() throws Exception {
        // Initialize the database
        productRelatedRepository.saveAndFlush(productRelated);

        // Get the productRelated
        restProductRelatedMockMvc.perform(get("/api/product-relateds/{id}", productRelated.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productRelated.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingProductRelated() throws Exception {
        // Get the productRelated
        restProductRelatedMockMvc.perform(get("/api/product-relateds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductRelated() throws Exception {
        // Initialize the database
        productRelatedRepository.saveAndFlush(productRelated);

        int databaseSizeBeforeUpdate = productRelatedRepository.findAll().size();

        // Update the productRelated
        ProductRelated updatedProductRelated = productRelatedRepository.findById(productRelated.getId()).get();
        // Disconnect from session so that the updates on updatedProductRelated are not directly saved in db
        em.detach(updatedProductRelated);
        updatedProductRelated
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ProductRelatedDTO productRelatedDTO = productRelatedMapper.toDto(updatedProductRelated);

        restProductRelatedMockMvc.perform(put("/api/product-relateds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRelatedDTO)))
            .andExpect(status().isOk());

        // Validate the ProductRelated in the database
        List<ProductRelated> productRelatedList = productRelatedRepository.findAll();
        assertThat(productRelatedList).hasSize(databaseSizeBeforeUpdate);
        ProductRelated testProductRelated = productRelatedList.get(productRelatedList.size() - 1);
        assertThat(testProductRelated.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProductRelated.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductRelated.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProductRelated.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductRelated.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductRelated() throws Exception {
        int databaseSizeBeforeUpdate = productRelatedRepository.findAll().size();

        // Create the ProductRelated
        ProductRelatedDTO productRelatedDTO = productRelatedMapper.toDto(productRelated);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductRelatedMockMvc.perform(put("/api/product-relateds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRelatedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRelated in the database
        List<ProductRelated> productRelatedList = productRelatedRepository.findAll();
        assertThat(productRelatedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductRelated() throws Exception {
        // Initialize the database
        productRelatedRepository.saveAndFlush(productRelated);

        int databaseSizeBeforeDelete = productRelatedRepository.findAll().size();

        // Delete the productRelated
        restProductRelatedMockMvc.perform(delete("/api/product-relateds/{id}", productRelated.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductRelated> productRelatedList = productRelatedRepository.findAll();
        assertThat(productRelatedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRelated.class);
        ProductRelated productRelated1 = new ProductRelated();
        productRelated1.setId(1L);
        ProductRelated productRelated2 = new ProductRelated();
        productRelated2.setId(productRelated1.getId());
        assertThat(productRelated1).isEqualTo(productRelated2);
        productRelated2.setId(2L);
        assertThat(productRelated1).isNotEqualTo(productRelated2);
        productRelated1.setId(null);
        assertThat(productRelated1).isNotEqualTo(productRelated2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRelatedDTO.class);
        ProductRelatedDTO productRelatedDTO1 = new ProductRelatedDTO();
        productRelatedDTO1.setId(1L);
        ProductRelatedDTO productRelatedDTO2 = new ProductRelatedDTO();
        assertThat(productRelatedDTO1).isNotEqualTo(productRelatedDTO2);
        productRelatedDTO2.setId(productRelatedDTO1.getId());
        assertThat(productRelatedDTO1).isEqualTo(productRelatedDTO2);
        productRelatedDTO2.setId(2L);
        assertThat(productRelatedDTO1).isNotEqualTo(productRelatedDTO2);
        productRelatedDTO1.setId(null);
        assertThat(productRelatedDTO1).isNotEqualTo(productRelatedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productRelatedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productRelatedMapper.fromId(null)).isNull();
    }
}
