package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.OrderHistory;
import com.mycompany.myapp.repository.OrderHistoryRepository;
import com.mycompany.myapp.service.OrderHistoryService;
import com.mycompany.myapp.service.dto.OrderHistoryDTO;
import com.mycompany.myapp.service.mapper.OrderHistoryMapper;
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
 * Integration tests for the {@link OrderHistoryResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderHistoryResourceIT {

    private static final String DEFAULT_NOTIFY = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFY = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_ADDED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_ADDED = "BBBBBBBBBB";

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
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Autowired
    private OrderHistoryService orderHistoryService;

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

    private MockMvc restOrderHistoryMockMvc;

    private OrderHistory orderHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderHistoryResource orderHistoryResource = new OrderHistoryResource(orderHistoryService);
        this.restOrderHistoryMockMvc = MockMvcBuilders.standaloneSetup(orderHistoryResource)
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
    public static OrderHistory createEntity(EntityManager em) {
        OrderHistory orderHistory = new OrderHistory()
            .notify(DEFAULT_NOTIFY)
            .comment(DEFAULT_COMMENT)
            .dateAdded(DEFAULT_DATE_ADDED)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return orderHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderHistory createUpdatedEntity(EntityManager em) {
        OrderHistory orderHistory = new OrderHistory()
            .notify(UPDATED_NOTIFY)
            .comment(UPDATED_COMMENT)
            .dateAdded(UPDATED_DATE_ADDED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return orderHistory;
    }

    @BeforeEach
    public void initTest() {
        orderHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderHistory() throws Exception {
        int databaseSizeBeforeCreate = orderHistoryRepository.findAll().size();

        // Create the OrderHistory
        OrderHistoryDTO orderHistoryDTO = orderHistoryMapper.toDto(orderHistory);
        restOrderHistoryMockMvc.perform(post("/api/order-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderHistory in the database
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        assertThat(orderHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        OrderHistory testOrderHistory = orderHistoryList.get(orderHistoryList.size() - 1);
        assertThat(testOrderHistory.getNotify()).isEqualTo(DEFAULT_NOTIFY);
        assertThat(testOrderHistory.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testOrderHistory.getDateAdded()).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testOrderHistory.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderHistory.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testOrderHistory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderHistory.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrderHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderHistoryRepository.findAll().size();

        // Create the OrderHistory with an existing ID
        orderHistory.setId(1L);
        OrderHistoryDTO orderHistoryDTO = orderHistoryMapper.toDto(orderHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderHistoryMockMvc.perform(post("/api/order-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderHistory in the database
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        assertThat(orderHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderHistories() throws Exception {
        // Initialize the database
        orderHistoryRepository.saveAndFlush(orderHistory);

        // Get all the orderHistoryList
        restOrderHistoryMockMvc.perform(get("/api/order-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].notify").value(hasItem(DEFAULT_NOTIFY)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateAdded").value(hasItem(DEFAULT_DATE_ADDED)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getOrderHistory() throws Exception {
        // Initialize the database
        orderHistoryRepository.saveAndFlush(orderHistory);

        // Get the orderHistory
        restOrderHistoryMockMvc.perform(get("/api/order-histories/{id}", orderHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderHistory.getId().intValue()))
            .andExpect(jsonPath("$.notify").value(DEFAULT_NOTIFY))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.dateAdded").value(DEFAULT_DATE_ADDED))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingOrderHistory() throws Exception {
        // Get the orderHistory
        restOrderHistoryMockMvc.perform(get("/api/order-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderHistory() throws Exception {
        // Initialize the database
        orderHistoryRepository.saveAndFlush(orderHistory);

        int databaseSizeBeforeUpdate = orderHistoryRepository.findAll().size();

        // Update the orderHistory
        OrderHistory updatedOrderHistory = orderHistoryRepository.findById(orderHistory.getId()).get();
        // Disconnect from session so that the updates on updatedOrderHistory are not directly saved in db
        em.detach(updatedOrderHistory);
        updatedOrderHistory
            .notify(UPDATED_NOTIFY)
            .comment(UPDATED_COMMENT)
            .dateAdded(UPDATED_DATE_ADDED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        OrderHistoryDTO orderHistoryDTO = orderHistoryMapper.toDto(updatedOrderHistory);

        restOrderHistoryMockMvc.perform(put("/api/order-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the OrderHistory in the database
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        assertThat(orderHistoryList).hasSize(databaseSizeBeforeUpdate);
        OrderHistory testOrderHistory = orderHistoryList.get(orderHistoryList.size() - 1);
        assertThat(testOrderHistory.getNotify()).isEqualTo(UPDATED_NOTIFY);
        assertThat(testOrderHistory.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testOrderHistory.getDateAdded()).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testOrderHistory.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderHistory.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testOrderHistory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderHistory.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderHistory() throws Exception {
        int databaseSizeBeforeUpdate = orderHistoryRepository.findAll().size();

        // Create the OrderHistory
        OrderHistoryDTO orderHistoryDTO = orderHistoryMapper.toDto(orderHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderHistoryMockMvc.perform(put("/api/order-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderHistory in the database
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        assertThat(orderHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderHistory() throws Exception {
        // Initialize the database
        orderHistoryRepository.saveAndFlush(orderHistory);

        int databaseSizeBeforeDelete = orderHistoryRepository.findAll().size();

        // Delete the orderHistory
        restOrderHistoryMockMvc.perform(delete("/api/order-histories/{id}", orderHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAll();
        assertThat(orderHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderHistory.class);
        OrderHistory orderHistory1 = new OrderHistory();
        orderHistory1.setId(1L);
        OrderHistory orderHistory2 = new OrderHistory();
        orderHistory2.setId(orderHistory1.getId());
        assertThat(orderHistory1).isEqualTo(orderHistory2);
        orderHistory2.setId(2L);
        assertThat(orderHistory1).isNotEqualTo(orderHistory2);
        orderHistory1.setId(null);
        assertThat(orderHistory1).isNotEqualTo(orderHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderHistoryDTO.class);
        OrderHistoryDTO orderHistoryDTO1 = new OrderHistoryDTO();
        orderHistoryDTO1.setId(1L);
        OrderHistoryDTO orderHistoryDTO2 = new OrderHistoryDTO();
        assertThat(orderHistoryDTO1).isNotEqualTo(orderHistoryDTO2);
        orderHistoryDTO2.setId(orderHistoryDTO1.getId());
        assertThat(orderHistoryDTO1).isEqualTo(orderHistoryDTO2);
        orderHistoryDTO2.setId(2L);
        assertThat(orderHistoryDTO1).isNotEqualTo(orderHistoryDTO2);
        orderHistoryDTO1.setId(null);
        assertThat(orderHistoryDTO1).isNotEqualTo(orderHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderHistoryMapper.fromId(null)).isNull();
    }
}
