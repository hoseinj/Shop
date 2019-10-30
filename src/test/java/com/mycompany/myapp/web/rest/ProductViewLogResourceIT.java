package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ProductViewLog;
import com.mycompany.myapp.repository.ProductViewLogRepository;
import com.mycompany.myapp.service.ProductViewLogService;
import com.mycompany.myapp.service.dto.ProductViewLogDTO;
import com.mycompany.myapp.service.mapper.ProductViewLogMapper;
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
 * Integration tests for the {@link ProductViewLogResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ProductViewLogResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOBILE = 1;
    private static final Integer UPDATED_MOBILE = 2;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

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
    private ProductViewLogRepository productViewLogRepository;

    @Autowired
    private ProductViewLogMapper productViewLogMapper;

    @Autowired
    private ProductViewLogService productViewLogService;

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

    private MockMvc restProductViewLogMockMvc;

    private ProductViewLog productViewLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductViewLogResource productViewLogResource = new ProductViewLogResource(productViewLogService);
        this.restProductViewLogMockMvc = MockMvcBuilders.standaloneSetup(productViewLogResource)
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
    public static ProductViewLog createEntity(EntityManager em) {
        ProductViewLog productViewLog = new ProductViewLog()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .userName(DEFAULT_USER_NAME)
            .mobile(DEFAULT_MOBILE)
            .address(DEFAULT_ADDRESS)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return productViewLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductViewLog createUpdatedEntity(EntityManager em) {
        ProductViewLog productViewLog = new ProductViewLog()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .userName(UPDATED_USER_NAME)
            .mobile(UPDATED_MOBILE)
            .address(UPDATED_ADDRESS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return productViewLog;
    }

    @BeforeEach
    public void initTest() {
        productViewLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductViewLog() throws Exception {
        int databaseSizeBeforeCreate = productViewLogRepository.findAll().size();

        // Create the ProductViewLog
        ProductViewLogDTO productViewLogDTO = productViewLogMapper.toDto(productViewLog);
        restProductViewLogMockMvc.perform(post("/api/product-view-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productViewLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductViewLog in the database
        List<ProductViewLog> productViewLogList = productViewLogRepository.findAll();
        assertThat(productViewLogList).hasSize(databaseSizeBeforeCreate + 1);
        ProductViewLog testProductViewLog = productViewLogList.get(productViewLogList.size() - 1);
        assertThat(testProductViewLog.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testProductViewLog.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testProductViewLog.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProductViewLog.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testProductViewLog.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testProductViewLog.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testProductViewLog.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProductViewLog.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductViewLog.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProductViewLog.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductViewLog.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductViewLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productViewLogRepository.findAll().size();

        // Create the ProductViewLog with an existing ID
        productViewLog.setId(1L);
        ProductViewLogDTO productViewLogDTO = productViewLogMapper.toDto(productViewLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductViewLogMockMvc.perform(post("/api/product-view-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productViewLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductViewLog in the database
        List<ProductViewLog> productViewLogList = productViewLogRepository.findAll();
        assertThat(productViewLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductViewLogs() throws Exception {
        // Initialize the database
        productViewLogRepository.saveAndFlush(productViewLog);

        // Get all the productViewLogList
        restProductViewLogMockMvc.perform(get("/api/product-view-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productViewLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getProductViewLog() throws Exception {
        // Initialize the database
        productViewLogRepository.saveAndFlush(productViewLog);

        // Get the productViewLog
        restProductViewLogMockMvc.perform(get("/api/product-view-logs/{id}", productViewLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productViewLog.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingProductViewLog() throws Exception {
        // Get the productViewLog
        restProductViewLogMockMvc.perform(get("/api/product-view-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductViewLog() throws Exception {
        // Initialize the database
        productViewLogRepository.saveAndFlush(productViewLog);

        int databaseSizeBeforeUpdate = productViewLogRepository.findAll().size();

        // Update the productViewLog
        ProductViewLog updatedProductViewLog = productViewLogRepository.findById(productViewLog.getId()).get();
        // Disconnect from session so that the updates on updatedProductViewLog are not directly saved in db
        em.detach(updatedProductViewLog);
        updatedProductViewLog
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .userName(UPDATED_USER_NAME)
            .mobile(UPDATED_MOBILE)
            .address(UPDATED_ADDRESS)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ProductViewLogDTO productViewLogDTO = productViewLogMapper.toDto(updatedProductViewLog);

        restProductViewLogMockMvc.perform(put("/api/product-view-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productViewLogDTO)))
            .andExpect(status().isOk());

        // Validate the ProductViewLog in the database
        List<ProductViewLog> productViewLogList = productViewLogRepository.findAll();
        assertThat(productViewLogList).hasSize(databaseSizeBeforeUpdate);
        ProductViewLog testProductViewLog = productViewLogList.get(productViewLogList.size() - 1);
        assertThat(testProductViewLog.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testProductViewLog.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testProductViewLog.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProductViewLog.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testProductViewLog.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testProductViewLog.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testProductViewLog.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProductViewLog.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductViewLog.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProductViewLog.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductViewLog.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductViewLog() throws Exception {
        int databaseSizeBeforeUpdate = productViewLogRepository.findAll().size();

        // Create the ProductViewLog
        ProductViewLogDTO productViewLogDTO = productViewLogMapper.toDto(productViewLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductViewLogMockMvc.perform(put("/api/product-view-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productViewLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductViewLog in the database
        List<ProductViewLog> productViewLogList = productViewLogRepository.findAll();
        assertThat(productViewLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductViewLog() throws Exception {
        // Initialize the database
        productViewLogRepository.saveAndFlush(productViewLog);

        int databaseSizeBeforeDelete = productViewLogRepository.findAll().size();

        // Delete the productViewLog
        restProductViewLogMockMvc.perform(delete("/api/product-view-logs/{id}", productViewLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductViewLog> productViewLogList = productViewLogRepository.findAll();
        assertThat(productViewLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductViewLog.class);
        ProductViewLog productViewLog1 = new ProductViewLog();
        productViewLog1.setId(1L);
        ProductViewLog productViewLog2 = new ProductViewLog();
        productViewLog2.setId(productViewLog1.getId());
        assertThat(productViewLog1).isEqualTo(productViewLog2);
        productViewLog2.setId(2L);
        assertThat(productViewLog1).isNotEqualTo(productViewLog2);
        productViewLog1.setId(null);
        assertThat(productViewLog1).isNotEqualTo(productViewLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductViewLogDTO.class);
        ProductViewLogDTO productViewLogDTO1 = new ProductViewLogDTO();
        productViewLogDTO1.setId(1L);
        ProductViewLogDTO productViewLogDTO2 = new ProductViewLogDTO();
        assertThat(productViewLogDTO1).isNotEqualTo(productViewLogDTO2);
        productViewLogDTO2.setId(productViewLogDTO1.getId());
        assertThat(productViewLogDTO1).isEqualTo(productViewLogDTO2);
        productViewLogDTO2.setId(2L);
        assertThat(productViewLogDTO1).isNotEqualTo(productViewLogDTO2);
        productViewLogDTO1.setId(null);
        assertThat(productViewLogDTO1).isNotEqualTo(productViewLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productViewLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productViewLogMapper.fromId(null)).isNull();
    }
}
