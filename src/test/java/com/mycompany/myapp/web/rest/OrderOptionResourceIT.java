package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.OrderOption;
import com.mycompany.myapp.repository.OrderOptionRepository;
import com.mycompany.myapp.service.OrderOptionService;
import com.mycompany.myapp.service.dto.OrderOptionDTO;
import com.mycompany.myapp.service.mapper.OrderOptionMapper;
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
 * Integration tests for the {@link OrderOptionResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderOptionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

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
    private OrderOptionRepository orderOptionRepository;

    @Autowired
    private OrderOptionMapper orderOptionMapper;

    @Autowired
    private OrderOptionService orderOptionService;

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

    private MockMvc restOrderOptionMockMvc;

    private OrderOption orderOption;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderOptionResource orderOptionResource = new OrderOptionResource(orderOptionService);
        this.restOrderOptionMockMvc = MockMvcBuilders.standaloneSetup(orderOptionResource)
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
    public static OrderOption createEntity(EntityManager em) {
        OrderOption orderOption = new OrderOption()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .type(DEFAULT_TYPE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return orderOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderOption createUpdatedEntity(EntityManager em) {
        OrderOption orderOption = new OrderOption()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return orderOption;
    }

    @BeforeEach
    public void initTest() {
        orderOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderOption() throws Exception {
        int databaseSizeBeforeCreate = orderOptionRepository.findAll().size();

        // Create the OrderOption
        OrderOptionDTO orderOptionDTO = orderOptionMapper.toDto(orderOption);
        restOrderOptionMockMvc.perform(post("/api/order-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderOption in the database
        List<OrderOption> orderOptionList = orderOptionRepository.findAll();
        assertThat(orderOptionList).hasSize(databaseSizeBeforeCreate + 1);
        OrderOption testOrderOption = orderOptionList.get(orderOptionList.size() - 1);
        assertThat(testOrderOption.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrderOption.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testOrderOption.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOrderOption.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderOption.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderOption.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testOrderOption.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderOption.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrderOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderOptionRepository.findAll().size();

        // Create the OrderOption with an existing ID
        orderOption.setId(1L);
        OrderOptionDTO orderOptionDTO = orderOptionMapper.toDto(orderOption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderOptionMockMvc.perform(post("/api/order-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderOption in the database
        List<OrderOption> orderOptionList = orderOptionRepository.findAll();
        assertThat(orderOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderOptions() throws Exception {
        // Initialize the database
        orderOptionRepository.saveAndFlush(orderOption);

        // Get all the orderOptionList
        restOrderOptionMockMvc.perform(get("/api/order-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getOrderOption() throws Exception {
        // Initialize the database
        orderOptionRepository.saveAndFlush(orderOption);

        // Get the orderOption
        restOrderOptionMockMvc.perform(get("/api/order-options/{id}", orderOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderOption.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingOrderOption() throws Exception {
        // Get the orderOption
        restOrderOptionMockMvc.perform(get("/api/order-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderOption() throws Exception {
        // Initialize the database
        orderOptionRepository.saveAndFlush(orderOption);

        int databaseSizeBeforeUpdate = orderOptionRepository.findAll().size();

        // Update the orderOption
        OrderOption updatedOrderOption = orderOptionRepository.findById(orderOption.getId()).get();
        // Disconnect from session so that the updates on updatedOrderOption are not directly saved in db
        em.detach(updatedOrderOption);
        updatedOrderOption
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        OrderOptionDTO orderOptionDTO = orderOptionMapper.toDto(updatedOrderOption);

        restOrderOptionMockMvc.perform(put("/api/order-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderOptionDTO)))
            .andExpect(status().isOk());

        // Validate the OrderOption in the database
        List<OrderOption> orderOptionList = orderOptionRepository.findAll();
        assertThat(orderOptionList).hasSize(databaseSizeBeforeUpdate);
        OrderOption testOrderOption = orderOptionList.get(orderOptionList.size() - 1);
        assertThat(testOrderOption.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrderOption.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testOrderOption.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOrderOption.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderOption.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderOption.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testOrderOption.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderOption.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderOption() throws Exception {
        int databaseSizeBeforeUpdate = orderOptionRepository.findAll().size();

        // Create the OrderOption
        OrderOptionDTO orderOptionDTO = orderOptionMapper.toDto(orderOption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderOptionMockMvc.perform(put("/api/order-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderOption in the database
        List<OrderOption> orderOptionList = orderOptionRepository.findAll();
        assertThat(orderOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderOption() throws Exception {
        // Initialize the database
        orderOptionRepository.saveAndFlush(orderOption);

        int databaseSizeBeforeDelete = orderOptionRepository.findAll().size();

        // Delete the orderOption
        restOrderOptionMockMvc.perform(delete("/api/order-options/{id}", orderOption.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderOption> orderOptionList = orderOptionRepository.findAll();
        assertThat(orderOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderOption.class);
        OrderOption orderOption1 = new OrderOption();
        orderOption1.setId(1L);
        OrderOption orderOption2 = new OrderOption();
        orderOption2.setId(orderOption1.getId());
        assertThat(orderOption1).isEqualTo(orderOption2);
        orderOption2.setId(2L);
        assertThat(orderOption1).isNotEqualTo(orderOption2);
        orderOption1.setId(null);
        assertThat(orderOption1).isNotEqualTo(orderOption2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderOptionDTO.class);
        OrderOptionDTO orderOptionDTO1 = new OrderOptionDTO();
        orderOptionDTO1.setId(1L);
        OrderOptionDTO orderOptionDTO2 = new OrderOptionDTO();
        assertThat(orderOptionDTO1).isNotEqualTo(orderOptionDTO2);
        orderOptionDTO2.setId(orderOptionDTO1.getId());
        assertThat(orderOptionDTO1).isEqualTo(orderOptionDTO2);
        orderOptionDTO2.setId(2L);
        assertThat(orderOptionDTO1).isNotEqualTo(orderOptionDTO2);
        orderOptionDTO1.setId(null);
        assertThat(orderOptionDTO1).isNotEqualTo(orderOptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderOptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderOptionMapper.fromId(null)).isNull();
    }
}
