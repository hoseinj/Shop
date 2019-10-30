package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.OrderTotal;
import com.mycompany.myapp.repository.OrderTotalRepository;
import com.mycompany.myapp.service.OrderTotalService;
import com.mycompany.myapp.service.dto.OrderTotalDTO;
import com.mycompany.myapp.service.mapper.OrderTotalMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrderTotalResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderTotalResourceIT {

    private static final BigDecimal DEFAULT_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALUE = new BigDecimal(2);

    @Autowired
    private OrderTotalRepository orderTotalRepository;

    @Autowired
    private OrderTotalMapper orderTotalMapper;

    @Autowired
    private OrderTotalService orderTotalService;

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

    private MockMvc restOrderTotalMockMvc;

    private OrderTotal orderTotal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderTotalResource orderTotalResource = new OrderTotalResource(orderTotalService);
        this.restOrderTotalMockMvc = MockMvcBuilders.standaloneSetup(orderTotalResource)
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
    public static OrderTotal createEntity(EntityManager em) {
        OrderTotal orderTotal = new OrderTotal()
            .value(DEFAULT_VALUE);
        return orderTotal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderTotal createUpdatedEntity(EntityManager em) {
        OrderTotal orderTotal = new OrderTotal()
            .value(UPDATED_VALUE);
        return orderTotal;
    }

    @BeforeEach
    public void initTest() {
        orderTotal = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderTotal() throws Exception {
        int databaseSizeBeforeCreate = orderTotalRepository.findAll().size();

        // Create the OrderTotal
        OrderTotalDTO orderTotalDTO = orderTotalMapper.toDto(orderTotal);
        restOrderTotalMockMvc.perform(post("/api/order-totals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTotalDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderTotal in the database
        List<OrderTotal> orderTotalList = orderTotalRepository.findAll();
        assertThat(orderTotalList).hasSize(databaseSizeBeforeCreate + 1);
        OrderTotal testOrderTotal = orderTotalList.get(orderTotalList.size() - 1);
        assertThat(testOrderTotal.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createOrderTotalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderTotalRepository.findAll().size();

        // Create the OrderTotal with an existing ID
        orderTotal.setId(1L);
        OrderTotalDTO orderTotalDTO = orderTotalMapper.toDto(orderTotal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderTotalMockMvc.perform(post("/api/order-totals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTotalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderTotal in the database
        List<OrderTotal> orderTotalList = orderTotalRepository.findAll();
        assertThat(orderTotalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderTotals() throws Exception {
        // Initialize the database
        orderTotalRepository.saveAndFlush(orderTotal);

        // Get all the orderTotalList
        restOrderTotalMockMvc.perform(get("/api/order-totals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderTotal.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.intValue())));
    }
    
    @Test
    @Transactional
    public void getOrderTotal() throws Exception {
        // Initialize the database
        orderTotalRepository.saveAndFlush(orderTotal);

        // Get the orderTotal
        restOrderTotalMockMvc.perform(get("/api/order-totals/{id}", orderTotal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderTotal.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderTotal() throws Exception {
        // Get the orderTotal
        restOrderTotalMockMvc.perform(get("/api/order-totals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderTotal() throws Exception {
        // Initialize the database
        orderTotalRepository.saveAndFlush(orderTotal);

        int databaseSizeBeforeUpdate = orderTotalRepository.findAll().size();

        // Update the orderTotal
        OrderTotal updatedOrderTotal = orderTotalRepository.findById(orderTotal.getId()).get();
        // Disconnect from session so that the updates on updatedOrderTotal are not directly saved in db
        em.detach(updatedOrderTotal);
        updatedOrderTotal
            .value(UPDATED_VALUE);
        OrderTotalDTO orderTotalDTO = orderTotalMapper.toDto(updatedOrderTotal);

        restOrderTotalMockMvc.perform(put("/api/order-totals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTotalDTO)))
            .andExpect(status().isOk());

        // Validate the OrderTotal in the database
        List<OrderTotal> orderTotalList = orderTotalRepository.findAll();
        assertThat(orderTotalList).hasSize(databaseSizeBeforeUpdate);
        OrderTotal testOrderTotal = orderTotalList.get(orderTotalList.size() - 1);
        assertThat(testOrderTotal.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderTotal() throws Exception {
        int databaseSizeBeforeUpdate = orderTotalRepository.findAll().size();

        // Create the OrderTotal
        OrderTotalDTO orderTotalDTO = orderTotalMapper.toDto(orderTotal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderTotalMockMvc.perform(put("/api/order-totals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTotalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderTotal in the database
        List<OrderTotal> orderTotalList = orderTotalRepository.findAll();
        assertThat(orderTotalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderTotal() throws Exception {
        // Initialize the database
        orderTotalRepository.saveAndFlush(orderTotal);

        int databaseSizeBeforeDelete = orderTotalRepository.findAll().size();

        // Delete the orderTotal
        restOrderTotalMockMvc.perform(delete("/api/order-totals/{id}", orderTotal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderTotal> orderTotalList = orderTotalRepository.findAll();
        assertThat(orderTotalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderTotal.class);
        OrderTotal orderTotal1 = new OrderTotal();
        orderTotal1.setId(1L);
        OrderTotal orderTotal2 = new OrderTotal();
        orderTotal2.setId(orderTotal1.getId());
        assertThat(orderTotal1).isEqualTo(orderTotal2);
        orderTotal2.setId(2L);
        assertThat(orderTotal1).isNotEqualTo(orderTotal2);
        orderTotal1.setId(null);
        assertThat(orderTotal1).isNotEqualTo(orderTotal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderTotalDTO.class);
        OrderTotalDTO orderTotalDTO1 = new OrderTotalDTO();
        orderTotalDTO1.setId(1L);
        OrderTotalDTO orderTotalDTO2 = new OrderTotalDTO();
        assertThat(orderTotalDTO1).isNotEqualTo(orderTotalDTO2);
        orderTotalDTO2.setId(orderTotalDTO1.getId());
        assertThat(orderTotalDTO1).isEqualTo(orderTotalDTO2);
        orderTotalDTO2.setId(2L);
        assertThat(orderTotalDTO1).isNotEqualTo(orderTotalDTO2);
        orderTotalDTO1.setId(null);
        assertThat(orderTotalDTO1).isNotEqualTo(orderTotalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderTotalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderTotalMapper.fromId(null)).isNull();
    }
}
