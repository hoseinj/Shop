package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.StockStatus;
import com.mycompany.myapp.repository.StockStatusRepository;
import com.mycompany.myapp.service.StockStatusService;
import com.mycompany.myapp.service.dto.StockStatusDTO;
import com.mycompany.myapp.service.mapper.StockStatusMapper;
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
 * Integration tests for the {@link StockStatusResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class StockStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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
    private StockStatusRepository stockStatusRepository;

    @Autowired
    private StockStatusMapper stockStatusMapper;

    @Autowired
    private StockStatusService stockStatusService;

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

    private MockMvc restStockStatusMockMvc;

    private StockStatus stockStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StockStatusResource stockStatusResource = new StockStatusResource(stockStatusService);
        this.restStockStatusMockMvc = MockMvcBuilders.standaloneSetup(stockStatusResource)
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
    public static StockStatus createEntity(EntityManager em) {
        StockStatus stockStatus = new StockStatus()
            .name(DEFAULT_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return stockStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockStatus createUpdatedEntity(EntityManager em) {
        StockStatus stockStatus = new StockStatus()
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return stockStatus;
    }

    @BeforeEach
    public void initTest() {
        stockStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockStatus() throws Exception {
        int databaseSizeBeforeCreate = stockStatusRepository.findAll().size();

        // Create the StockStatus
        StockStatusDTO stockStatusDTO = stockStatusMapper.toDto(stockStatus);
        restStockStatusMockMvc.perform(post("/api/stock-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the StockStatus in the database
        List<StockStatus> stockStatusList = stockStatusRepository.findAll();
        assertThat(stockStatusList).hasSize(databaseSizeBeforeCreate + 1);
        StockStatus testStockStatus = stockStatusList.get(stockStatusList.size() - 1);
        assertThat(testStockStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStockStatus.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testStockStatus.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testStockStatus.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testStockStatus.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStockStatus.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createStockStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockStatusRepository.findAll().size();

        // Create the StockStatus with an existing ID
        stockStatus.setId(1L);
        StockStatusDTO stockStatusDTO = stockStatusMapper.toDto(stockStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockStatusMockMvc.perform(post("/api/stock-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockStatus in the database
        List<StockStatus> stockStatusList = stockStatusRepository.findAll();
        assertThat(stockStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStockStatuses() throws Exception {
        // Initialize the database
        stockStatusRepository.saveAndFlush(stockStatus);

        // Get all the stockStatusList
        restStockStatusMockMvc.perform(get("/api/stock-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getStockStatus() throws Exception {
        // Initialize the database
        stockStatusRepository.saveAndFlush(stockStatus);

        // Get the stockStatus
        restStockStatusMockMvc.perform(get("/api/stock-statuses/{id}", stockStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stockStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingStockStatus() throws Exception {
        // Get the stockStatus
        restStockStatusMockMvc.perform(get("/api/stock-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockStatus() throws Exception {
        // Initialize the database
        stockStatusRepository.saveAndFlush(stockStatus);

        int databaseSizeBeforeUpdate = stockStatusRepository.findAll().size();

        // Update the stockStatus
        StockStatus updatedStockStatus = stockStatusRepository.findById(stockStatus.getId()).get();
        // Disconnect from session so that the updates on updatedStockStatus are not directly saved in db
        em.detach(updatedStockStatus);
        updatedStockStatus
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        StockStatusDTO stockStatusDTO = stockStatusMapper.toDto(updatedStockStatus);

        restStockStatusMockMvc.perform(put("/api/stock-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockStatusDTO)))
            .andExpect(status().isOk());

        // Validate the StockStatus in the database
        List<StockStatus> stockStatusList = stockStatusRepository.findAll();
        assertThat(stockStatusList).hasSize(databaseSizeBeforeUpdate);
        StockStatus testStockStatus = stockStatusList.get(stockStatusList.size() - 1);
        assertThat(testStockStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStockStatus.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testStockStatus.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testStockStatus.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testStockStatus.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStockStatus.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingStockStatus() throws Exception {
        int databaseSizeBeforeUpdate = stockStatusRepository.findAll().size();

        // Create the StockStatus
        StockStatusDTO stockStatusDTO = stockStatusMapper.toDto(stockStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockStatusMockMvc.perform(put("/api/stock-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stockStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StockStatus in the database
        List<StockStatus> stockStatusList = stockStatusRepository.findAll();
        assertThat(stockStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStockStatus() throws Exception {
        // Initialize the database
        stockStatusRepository.saveAndFlush(stockStatus);

        int databaseSizeBeforeDelete = stockStatusRepository.findAll().size();

        // Delete the stockStatus
        restStockStatusMockMvc.perform(delete("/api/stock-statuses/{id}", stockStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockStatus> stockStatusList = stockStatusRepository.findAll();
        assertThat(stockStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockStatus.class);
        StockStatus stockStatus1 = new StockStatus();
        stockStatus1.setId(1L);
        StockStatus stockStatus2 = new StockStatus();
        stockStatus2.setId(stockStatus1.getId());
        assertThat(stockStatus1).isEqualTo(stockStatus2);
        stockStatus2.setId(2L);
        assertThat(stockStatus1).isNotEqualTo(stockStatus2);
        stockStatus1.setId(null);
        assertThat(stockStatus1).isNotEqualTo(stockStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockStatusDTO.class);
        StockStatusDTO stockStatusDTO1 = new StockStatusDTO();
        stockStatusDTO1.setId(1L);
        StockStatusDTO stockStatusDTO2 = new StockStatusDTO();
        assertThat(stockStatusDTO1).isNotEqualTo(stockStatusDTO2);
        stockStatusDTO2.setId(stockStatusDTO1.getId());
        assertThat(stockStatusDTO1).isEqualTo(stockStatusDTO2);
        stockStatusDTO2.setId(2L);
        assertThat(stockStatusDTO1).isNotEqualTo(stockStatusDTO2);
        stockStatusDTO1.setId(null);
        assertThat(stockStatusDTO1).isNotEqualTo(stockStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stockStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stockStatusMapper.fromId(null)).isNull();
    }
}
