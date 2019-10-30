package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ProductRating;
import com.mycompany.myapp.repository.ProductRatingRepository;
import com.mycompany.myapp.service.ProductRatingService;
import com.mycompany.myapp.service.dto.ProductRatingDTO;
import com.mycompany.myapp.service.mapper.ProductRatingMapper;
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
 * Integration tests for the {@link ProductRatingResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ProductRatingResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final String DEFAULT_REVIEW = "AAAAAAAAAA";
    private static final String UPDATED_REVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

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
    private ProductRatingRepository productRatingRepository;

    @Autowired
    private ProductRatingMapper productRatingMapper;

    @Autowired
    private ProductRatingService productRatingService;

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

    private MockMvc restProductRatingMockMvc;

    private ProductRating productRating;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductRatingResource productRatingResource = new ProductRatingResource(productRatingService);
        this.restProductRatingMockMvc = MockMvcBuilders.standaloneSetup(productRatingResource)
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
    public static ProductRating createEntity(EntityManager em) {
        ProductRating productRating = new ProductRating()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .email(DEFAULT_EMAIL)
            .rating(DEFAULT_RATING)
            .review(DEFAULT_REVIEW)
            .imagePath(DEFAULT_IMAGE_PATH)
            .image(DEFAULT_IMAGE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return productRating;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductRating createUpdatedEntity(EntityManager em) {
        ProductRating productRating = new ProductRating()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .rating(UPDATED_RATING)
            .review(UPDATED_REVIEW)
            .imagePath(UPDATED_IMAGE_PATH)
            .image(UPDATED_IMAGE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return productRating;
    }

    @BeforeEach
    public void initTest() {
        productRating = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductRating() throws Exception {
        int databaseSizeBeforeCreate = productRatingRepository.findAll().size();

        // Create the ProductRating
        ProductRatingDTO productRatingDTO = productRatingMapper.toDto(productRating);
        restProductRatingMockMvc.perform(post("/api/product-ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRatingDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductRating in the database
        List<ProductRating> productRatingList = productRatingRepository.findAll();
        assertThat(productRatingList).hasSize(databaseSizeBeforeCreate + 1);
        ProductRating testProductRating = productRatingList.get(productRatingList.size() - 1);
        assertThat(testProductRating.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testProductRating.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testProductRating.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProductRating.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testProductRating.getReview()).isEqualTo(DEFAULT_REVIEW);
        assertThat(testProductRating.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testProductRating.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testProductRating.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProductRating.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductRating.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProductRating.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductRating.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductRatingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRatingRepository.findAll().size();

        // Create the ProductRating with an existing ID
        productRating.setId(1L);
        ProductRatingDTO productRatingDTO = productRatingMapper.toDto(productRating);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductRatingMockMvc.perform(post("/api/product-ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRating in the database
        List<ProductRating> productRatingList = productRatingRepository.findAll();
        assertThat(productRatingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductRatings() throws Exception {
        // Initialize the database
        productRatingRepository.saveAndFlush(productRating);

        // Get all the productRatingList
        restProductRatingMockMvc.perform(get("/api/product-ratings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productRating.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)))
            .andExpect(jsonPath("$.[*].review").value(hasItem(DEFAULT_REVIEW)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getProductRating() throws Exception {
        // Initialize the database
        productRatingRepository.saveAndFlush(productRating);

        // Get the productRating
        restProductRatingMockMvc.perform(get("/api/product-ratings/{id}", productRating.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productRating.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING))
            .andExpect(jsonPath("$.review").value(DEFAULT_REVIEW))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingProductRating() throws Exception {
        // Get the productRating
        restProductRatingMockMvc.perform(get("/api/product-ratings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductRating() throws Exception {
        // Initialize the database
        productRatingRepository.saveAndFlush(productRating);

        int databaseSizeBeforeUpdate = productRatingRepository.findAll().size();

        // Update the productRating
        ProductRating updatedProductRating = productRatingRepository.findById(productRating.getId()).get();
        // Disconnect from session so that the updates on updatedProductRating are not directly saved in db
        em.detach(updatedProductRating);
        updatedProductRating
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .rating(UPDATED_RATING)
            .review(UPDATED_REVIEW)
            .imagePath(UPDATED_IMAGE_PATH)
            .image(UPDATED_IMAGE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ProductRatingDTO productRatingDTO = productRatingMapper.toDto(updatedProductRating);

        restProductRatingMockMvc.perform(put("/api/product-ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRatingDTO)))
            .andExpect(status().isOk());

        // Validate the ProductRating in the database
        List<ProductRating> productRatingList = productRatingRepository.findAll();
        assertThat(productRatingList).hasSize(databaseSizeBeforeUpdate);
        ProductRating testProductRating = productRatingList.get(productRatingList.size() - 1);
        assertThat(testProductRating.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testProductRating.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testProductRating.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProductRating.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testProductRating.getReview()).isEqualTo(UPDATED_REVIEW);
        assertThat(testProductRating.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testProductRating.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testProductRating.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProductRating.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductRating.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProductRating.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductRating.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductRating() throws Exception {
        int databaseSizeBeforeUpdate = productRatingRepository.findAll().size();

        // Create the ProductRating
        ProductRatingDTO productRatingDTO = productRatingMapper.toDto(productRating);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductRatingMockMvc.perform(put("/api/product-ratings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRating in the database
        List<ProductRating> productRatingList = productRatingRepository.findAll();
        assertThat(productRatingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductRating() throws Exception {
        // Initialize the database
        productRatingRepository.saveAndFlush(productRating);

        int databaseSizeBeforeDelete = productRatingRepository.findAll().size();

        // Delete the productRating
        restProductRatingMockMvc.perform(delete("/api/product-ratings/{id}", productRating.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductRating> productRatingList = productRatingRepository.findAll();
        assertThat(productRatingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRating.class);
        ProductRating productRating1 = new ProductRating();
        productRating1.setId(1L);
        ProductRating productRating2 = new ProductRating();
        productRating2.setId(productRating1.getId());
        assertThat(productRating1).isEqualTo(productRating2);
        productRating2.setId(2L);
        assertThat(productRating1).isNotEqualTo(productRating2);
        productRating1.setId(null);
        assertThat(productRating1).isNotEqualTo(productRating2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRatingDTO.class);
        ProductRatingDTO productRatingDTO1 = new ProductRatingDTO();
        productRatingDTO1.setId(1L);
        ProductRatingDTO productRatingDTO2 = new ProductRatingDTO();
        assertThat(productRatingDTO1).isNotEqualTo(productRatingDTO2);
        productRatingDTO2.setId(productRatingDTO1.getId());
        assertThat(productRatingDTO1).isEqualTo(productRatingDTO2);
        productRatingDTO2.setId(2L);
        assertThat(productRatingDTO1).isNotEqualTo(productRatingDTO2);
        productRatingDTO1.setId(null);
        assertThat(productRatingDTO1).isNotEqualTo(productRatingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productRatingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productRatingMapper.fromId(null)).isNull();
    }
}
