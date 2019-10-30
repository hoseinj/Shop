package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ProductTag;
import com.mycompany.myapp.repository.ProductTagRepository;
import com.mycompany.myapp.service.ProductTagService;
import com.mycompany.myapp.service.dto.ProductTagDTO;
import com.mycompany.myapp.service.mapper.ProductTagMapper;
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
 * Integration tests for the {@link ProductTagResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ProductTagResourceIT {

    private static final String DEFAULT_PRODUCT_TAGNAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TAGNAME = "BBBBBBBBBB";

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
    private ProductTagRepository productTagRepository;

    @Autowired
    private ProductTagMapper productTagMapper;

    @Autowired
    private ProductTagService productTagService;

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

    private MockMvc restProductTagMockMvc;

    private ProductTag productTag;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductTagResource productTagResource = new ProductTagResource(productTagService);
        this.restProductTagMockMvc = MockMvcBuilders.standaloneSetup(productTagResource)
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
    public static ProductTag createEntity(EntityManager em) {
        ProductTag productTag = new ProductTag()
            .productTagname(DEFAULT_PRODUCT_TAGNAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return productTag;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductTag createUpdatedEntity(EntityManager em) {
        ProductTag productTag = new ProductTag()
            .productTagname(UPDATED_PRODUCT_TAGNAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return productTag;
    }

    @BeforeEach
    public void initTest() {
        productTag = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductTag() throws Exception {
        int databaseSizeBeforeCreate = productTagRepository.findAll().size();

        // Create the ProductTag
        ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);
        restProductTagMockMvc.perform(post("/api/product-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductTag in the database
        List<ProductTag> productTagList = productTagRepository.findAll();
        assertThat(productTagList).hasSize(databaseSizeBeforeCreate + 1);
        ProductTag testProductTag = productTagList.get(productTagList.size() - 1);
        assertThat(testProductTag.getProductTagname()).isEqualTo(DEFAULT_PRODUCT_TAGNAME);
        assertThat(testProductTag.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProductTag.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductTag.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProductTag.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductTag.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productTagRepository.findAll().size();

        // Create the ProductTag with an existing ID
        productTag.setId(1L);
        ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductTagMockMvc.perform(post("/api/product-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductTag in the database
        List<ProductTag> productTagList = productTagRepository.findAll();
        assertThat(productTagList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductTags() throws Exception {
        // Initialize the database
        productTagRepository.saveAndFlush(productTag);

        // Get all the productTagList
        restProductTagMockMvc.perform(get("/api/product-tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].productTagname").value(hasItem(DEFAULT_PRODUCT_TAGNAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getProductTag() throws Exception {
        // Initialize the database
        productTagRepository.saveAndFlush(productTag);

        // Get the productTag
        restProductTagMockMvc.perform(get("/api/product-tags/{id}", productTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productTag.getId().intValue()))
            .andExpect(jsonPath("$.productTagname").value(DEFAULT_PRODUCT_TAGNAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingProductTag() throws Exception {
        // Get the productTag
        restProductTagMockMvc.perform(get("/api/product-tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductTag() throws Exception {
        // Initialize the database
        productTagRepository.saveAndFlush(productTag);

        int databaseSizeBeforeUpdate = productTagRepository.findAll().size();

        // Update the productTag
        ProductTag updatedProductTag = productTagRepository.findById(productTag.getId()).get();
        // Disconnect from session so that the updates on updatedProductTag are not directly saved in db
        em.detach(updatedProductTag);
        updatedProductTag
            .productTagname(UPDATED_PRODUCT_TAGNAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ProductTagDTO productTagDTO = productTagMapper.toDto(updatedProductTag);

        restProductTagMockMvc.perform(put("/api/product-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
            .andExpect(status().isOk());

        // Validate the ProductTag in the database
        List<ProductTag> productTagList = productTagRepository.findAll();
        assertThat(productTagList).hasSize(databaseSizeBeforeUpdate);
        ProductTag testProductTag = productTagList.get(productTagList.size() - 1);
        assertThat(testProductTag.getProductTagname()).isEqualTo(UPDATED_PRODUCT_TAGNAME);
        assertThat(testProductTag.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProductTag.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductTag.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProductTag.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductTag.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductTag() throws Exception {
        int databaseSizeBeforeUpdate = productTagRepository.findAll().size();

        // Create the ProductTag
        ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTagMockMvc.perform(put("/api/product-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductTag in the database
        List<ProductTag> productTagList = productTagRepository.findAll();
        assertThat(productTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductTag() throws Exception {
        // Initialize the database
        productTagRepository.saveAndFlush(productTag);

        int databaseSizeBeforeDelete = productTagRepository.findAll().size();

        // Delete the productTag
        restProductTagMockMvc.perform(delete("/api/product-tags/{id}", productTag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductTag> productTagList = productTagRepository.findAll();
        assertThat(productTagList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductTag.class);
        ProductTag productTag1 = new ProductTag();
        productTag1.setId(1L);
        ProductTag productTag2 = new ProductTag();
        productTag2.setId(productTag1.getId());
        assertThat(productTag1).isEqualTo(productTag2);
        productTag2.setId(2L);
        assertThat(productTag1).isNotEqualTo(productTag2);
        productTag1.setId(null);
        assertThat(productTag1).isNotEqualTo(productTag2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductTagDTO.class);
        ProductTagDTO productTagDTO1 = new ProductTagDTO();
        productTagDTO1.setId(1L);
        ProductTagDTO productTagDTO2 = new ProductTagDTO();
        assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
        productTagDTO2.setId(productTagDTO1.getId());
        assertThat(productTagDTO1).isEqualTo(productTagDTO2);
        productTagDTO2.setId(2L);
        assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
        productTagDTO1.setId(null);
        assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productTagMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productTagMapper.fromId(null)).isNull();
    }
}
