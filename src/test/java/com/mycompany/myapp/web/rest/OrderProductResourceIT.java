package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.OrderProduct;
import com.mycompany.myapp.repository.OrderProductRepository;
import com.mycompany.myapp.service.OrderProductService;
import com.mycompany.myapp.service.dto.OrderProductDTO;
import com.mycompany.myapp.service.mapper.OrderProductMapper;
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
 * Integration tests for the {@link OrderProductResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final BigDecimal DEFAULT_TRACE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRACE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAX = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX = new BigDecimal(2);

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
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderProductService orderProductService;

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

    private MockMvc restOrderProductMockMvc;

    private OrderProduct orderProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderProductResource orderProductResource = new OrderProductResource(orderProductService);
        this.restOrderProductMockMvc = MockMvcBuilders.standaloneSetup(orderProductResource)
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
    public static OrderProduct createEntity(EntityManager em) {
        OrderProduct orderProduct = new OrderProduct()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .quantity(DEFAULT_QUANTITY)
            .trace(DEFAULT_TRACE)
            .total(DEFAULT_TOTAL)
            .tax(DEFAULT_TAX)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return orderProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderProduct createUpdatedEntity(EntityManager em) {
        OrderProduct orderProduct = new OrderProduct()
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .quantity(UPDATED_QUANTITY)
            .trace(UPDATED_TRACE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return orderProduct;
    }

    @BeforeEach
    public void initTest() {
        orderProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderProduct() throws Exception {
        int databaseSizeBeforeCreate = orderProductRepository.findAll().size();

        // Create the OrderProduct
        OrderProductDTO orderProductDTO = orderProductMapper.toDto(orderProduct);
        restOrderProductMockMvc.perform(post("/api/order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderProductDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderProduct in the database
        List<OrderProduct> orderProductList = orderProductRepository.findAll();
        assertThat(orderProductList).hasSize(databaseSizeBeforeCreate + 1);
        OrderProduct testOrderProduct = orderProductList.get(orderProductList.size() - 1);
        assertThat(testOrderProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrderProduct.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testOrderProduct.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrderProduct.getTrace()).isEqualTo(DEFAULT_TRACE);
        assertThat(testOrderProduct.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testOrderProduct.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testOrderProduct.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderProduct.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderProduct.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testOrderProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderProduct.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrderProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderProductRepository.findAll().size();

        // Create the OrderProduct with an existing ID
        orderProduct.setId(1L);
        OrderProductDTO orderProductDTO = orderProductMapper.toDto(orderProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderProductMockMvc.perform(post("/api/order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProduct in the database
        List<OrderProduct> orderProductList = orderProductRepository.findAll();
        assertThat(orderProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderProducts() throws Exception {
        // Initialize the database
        orderProductRepository.saveAndFlush(orderProduct);

        // Get all the orderProductList
        restOrderProductMockMvc.perform(get("/api/order-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].trace").value(hasItem(DEFAULT_TRACE.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getOrderProduct() throws Exception {
        // Initialize the database
        orderProductRepository.saveAndFlush(orderProduct);

        // Get the orderProduct
        restOrderProductMockMvc.perform(get("/api/order-products/{id}", orderProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderProduct.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.trace").value(DEFAULT_TRACE.intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingOrderProduct() throws Exception {
        // Get the orderProduct
        restOrderProductMockMvc.perform(get("/api/order-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderProduct() throws Exception {
        // Initialize the database
        orderProductRepository.saveAndFlush(orderProduct);

        int databaseSizeBeforeUpdate = orderProductRepository.findAll().size();

        // Update the orderProduct
        OrderProduct updatedOrderProduct = orderProductRepository.findById(orderProduct.getId()).get();
        // Disconnect from session so that the updates on updatedOrderProduct are not directly saved in db
        em.detach(updatedOrderProduct);
        updatedOrderProduct
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .quantity(UPDATED_QUANTITY)
            .trace(UPDATED_TRACE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        OrderProductDTO orderProductDTO = orderProductMapper.toDto(updatedOrderProduct);

        restOrderProductMockMvc.perform(put("/api/order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderProductDTO)))
            .andExpect(status().isOk());

        // Validate the OrderProduct in the database
        List<OrderProduct> orderProductList = orderProductRepository.findAll();
        assertThat(orderProductList).hasSize(databaseSizeBeforeUpdate);
        OrderProduct testOrderProduct = orderProductList.get(orderProductList.size() - 1);
        assertThat(testOrderProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrderProduct.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testOrderProduct.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrderProduct.getTrace()).isEqualTo(UPDATED_TRACE);
        assertThat(testOrderProduct.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testOrderProduct.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testOrderProduct.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderProduct.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderProduct.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testOrderProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderProduct.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderProduct() throws Exception {
        int databaseSizeBeforeUpdate = orderProductRepository.findAll().size();

        // Create the OrderProduct
        OrderProductDTO orderProductDTO = orderProductMapper.toDto(orderProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderProductMockMvc.perform(put("/api/order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderProduct in the database
        List<OrderProduct> orderProductList = orderProductRepository.findAll();
        assertThat(orderProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderProduct() throws Exception {
        // Initialize the database
        orderProductRepository.saveAndFlush(orderProduct);

        int databaseSizeBeforeDelete = orderProductRepository.findAll().size();

        // Delete the orderProduct
        restOrderProductMockMvc.perform(delete("/api/order-products/{id}", orderProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderProduct> orderProductList = orderProductRepository.findAll();
        assertThat(orderProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderProduct.class);
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setId(1L);
        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setId(orderProduct1.getId());
        assertThat(orderProduct1).isEqualTo(orderProduct2);
        orderProduct2.setId(2L);
        assertThat(orderProduct1).isNotEqualTo(orderProduct2);
        orderProduct1.setId(null);
        assertThat(orderProduct1).isNotEqualTo(orderProduct2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderProductDTO.class);
        OrderProductDTO orderProductDTO1 = new OrderProductDTO();
        orderProductDTO1.setId(1L);
        OrderProductDTO orderProductDTO2 = new OrderProductDTO();
        assertThat(orderProductDTO1).isNotEqualTo(orderProductDTO2);
        orderProductDTO2.setId(orderProductDTO1.getId());
        assertThat(orderProductDTO1).isEqualTo(orderProductDTO2);
        orderProductDTO2.setId(2L);
        assertThat(orderProductDTO1).isNotEqualTo(orderProductDTO2);
        orderProductDTO1.setId(null);
        assertThat(orderProductDTO1).isNotEqualTo(orderProductDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderProductMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderProductMapper.fromId(null)).isNull();
    }
}
