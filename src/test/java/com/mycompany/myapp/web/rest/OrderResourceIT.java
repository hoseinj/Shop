package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.repository.OrderRepository;
import com.mycompany.myapp.service.OrderService;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.mapper.OrderMapper;
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
 * Integration tests for the {@link OrderResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderResourceIT {

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_TELEPHONE = 1;
    private static final Integer UPDATED_TELEPHONE = 2;

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_POSTCODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_POSTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_ADDRESS_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_ADDRESS_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_POSTCODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_POSTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ADDRESS_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ADDRESS_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    private static final Integer DEFAULT_REWARD = 1;
    private static final Integer UPDATED_REWARD = 2;

    private static final Integer DEFAULT_COMMISION = 1;
    private static final Integer UPDATED_COMMISION = 2;

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CURRENCY_VALUE = 1;
    private static final Integer UPDATED_CURRENCY_VALUE = 2;

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYMENT_FLAG = 1;
    private static final Integer UPDATED_PAYMENT_FLAG = 2;

    private static final String DEFAULT_ORDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NAME = "BBBBBBBBBB";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

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

    private MockMvc restOrderMockMvc;

    private Order order;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderResource orderResource = new OrderResource(orderService);
        this.restOrderMockMvc = MockMvcBuilders.standaloneSetup(orderResource)
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
    public static Order createEntity(EntityManager em) {
        Order order = new Order()
            .invoiceNo(DEFAULT_INVOICE_NO)
            .invoicePrefix(DEFAULT_INVOICE_PREFIX)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .email(DEFAULT_EMAIL)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .shippingFirstname(DEFAULT_SHIPPING_FIRSTNAME)
            .shippingLastname(DEFAULT_SHIPPING_LASTNAME)
            .shippingCompany(DEFAULT_SHIPPING_COMPANY)
            .shippingAddress1(DEFAULT_SHIPPING_ADDRESS_1)
            .shippingAddress2(DEFAULT_SHIPPING_ADDRESS_2)
            .shippingCity(DEFAULT_SHIPPING_CITY)
            .shippingPostcode(DEFAULT_SHIPPING_POSTCODE)
            .shippingCountry(DEFAULT_SHIPPING_COUNTRY)
            .shippingZone(DEFAULT_SHIPPING_ZONE)
            .shippingAddressFormat(DEFAULT_SHIPPING_ADDRESS_FORMAT)
            .shippingMethod(DEFAULT_SHIPPING_METHOD)
            .paymentFirstname(DEFAULT_PAYMENT_FIRSTNAME)
            .paymentLastname(DEFAULT_PAYMENT_LASTNAME)
            .paymentCompany(DEFAULT_PAYMENT_COMPANY)
            .paymentAddress1(DEFAULT_PAYMENT_ADDRESS_1)
            .paymentAddress2(DEFAULT_PAYMENT_ADDRESS_2)
            .paymentCity(DEFAULT_PAYMENT_CITY)
            .paymentPostcode(DEFAULT_PAYMENT_POSTCODE)
            .paymentCountry(DEFAULT_PAYMENT_COUNTRY)
            .paymentZone(DEFAULT_PAYMENT_ZONE)
            .paymentAddressFormat(DEFAULT_PAYMENT_ADDRESS_FORMAT)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .comment(DEFAULT_COMMENT)
            .total(DEFAULT_TOTAL)
            .reward(DEFAULT_REWARD)
            .commision(DEFAULT_COMMISION)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .currencyValue(DEFAULT_CURRENCY_VALUE)
            .ip(DEFAULT_IP)
            .paymentFlag(DEFAULT_PAYMENT_FLAG)
            .orderName(DEFAULT_ORDER_NAME);
        return order;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity(EntityManager em) {
        Order order = new Order()
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .shippingFirstname(UPDATED_SHIPPING_FIRSTNAME)
            .shippingLastname(UPDATED_SHIPPING_LASTNAME)
            .shippingCompany(UPDATED_SHIPPING_COMPANY)
            .shippingAddress1(UPDATED_SHIPPING_ADDRESS_1)
            .shippingAddress2(UPDATED_SHIPPING_ADDRESS_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingPostcode(UPDATED_SHIPPING_POSTCODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingZone(UPDATED_SHIPPING_ZONE)
            .shippingAddressFormat(UPDATED_SHIPPING_ADDRESS_FORMAT)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .paymentFirstname(UPDATED_PAYMENT_FIRSTNAME)
            .paymentLastname(UPDATED_PAYMENT_LASTNAME)
            .paymentCompany(UPDATED_PAYMENT_COMPANY)
            .paymentAddress1(UPDATED_PAYMENT_ADDRESS_1)
            .paymentAddress2(UPDATED_PAYMENT_ADDRESS_2)
            .paymentCity(UPDATED_PAYMENT_CITY)
            .paymentPostcode(UPDATED_PAYMENT_POSTCODE)
            .paymentCountry(UPDATED_PAYMENT_COUNTRY)
            .paymentZone(UPDATED_PAYMENT_ZONE)
            .paymentAddressFormat(UPDATED_PAYMENT_ADDRESS_FORMAT)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .comment(UPDATED_COMMENT)
            .total(UPDATED_TOTAL)
            .reward(UPDATED_REWARD)
            .commision(UPDATED_COMMISION)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .currencyValue(UPDATED_CURRENCY_VALUE)
            .ip(UPDATED_IP)
            .paymentFlag(UPDATED_PAYMENT_FLAG)
            .orderName(UPDATED_ORDER_NAME);
        return order;
    }

    @BeforeEach
    public void initTest() {
        order = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testOrder.getInvoicePrefix()).isEqualTo(DEFAULT_INVOICE_PREFIX);
        assertThat(testOrder.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testOrder.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testOrder.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrder.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testOrder.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testOrder.getShippingFirstname()).isEqualTo(DEFAULT_SHIPPING_FIRSTNAME);
        assertThat(testOrder.getShippingLastname()).isEqualTo(DEFAULT_SHIPPING_LASTNAME);
        assertThat(testOrder.getShippingCompany()).isEqualTo(DEFAULT_SHIPPING_COMPANY);
        assertThat(testOrder.getShippingAddress1()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_1);
        assertThat(testOrder.getShippingAddress2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_2);
        assertThat(testOrder.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testOrder.getShippingPostcode()).isEqualTo(DEFAULT_SHIPPING_POSTCODE);
        assertThat(testOrder.getShippingCountry()).isEqualTo(DEFAULT_SHIPPING_COUNTRY);
        assertThat(testOrder.getShippingZone()).isEqualTo(DEFAULT_SHIPPING_ZONE);
        assertThat(testOrder.getShippingAddressFormat()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_FORMAT);
        assertThat(testOrder.getShippingMethod()).isEqualTo(DEFAULT_SHIPPING_METHOD);
        assertThat(testOrder.getPaymentFirstname()).isEqualTo(DEFAULT_PAYMENT_FIRSTNAME);
        assertThat(testOrder.getPaymentLastname()).isEqualTo(DEFAULT_PAYMENT_LASTNAME);
        assertThat(testOrder.getPaymentCompany()).isEqualTo(DEFAULT_PAYMENT_COMPANY);
        assertThat(testOrder.getPaymentAddress1()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_1);
        assertThat(testOrder.getPaymentAddress2()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_2);
        assertThat(testOrder.getPaymentCity()).isEqualTo(DEFAULT_PAYMENT_CITY);
        assertThat(testOrder.getPaymentPostcode()).isEqualTo(DEFAULT_PAYMENT_POSTCODE);
        assertThat(testOrder.getPaymentCountry()).isEqualTo(DEFAULT_PAYMENT_COUNTRY);
        assertThat(testOrder.getPaymentZone()).isEqualTo(DEFAULT_PAYMENT_ZONE);
        assertThat(testOrder.getPaymentAddressFormat()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_FORMAT);
        assertThat(testOrder.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testOrder.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testOrder.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testOrder.getReward()).isEqualTo(DEFAULT_REWARD);
        assertThat(testOrder.getCommision()).isEqualTo(DEFAULT_COMMISION);
        assertThat(testOrder.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testOrder.getCurrencyValue()).isEqualTo(DEFAULT_CURRENCY_VALUE);
        assertThat(testOrder.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testOrder.getPaymentFlag()).isEqualTo(DEFAULT_PAYMENT_FLAG);
        assertThat(testOrder.getOrderName()).isEqualTo(DEFAULT_ORDER_NAME);
    }

    @Test
    @Transactional
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId(1L);
        OrderDTO orderDTO = orderMapper.toDto(order);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].invoicePrefix").value(hasItem(DEFAULT_INVOICE_PREFIX)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].shippingFirstname").value(hasItem(DEFAULT_SHIPPING_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].shippingLastname").value(hasItem(DEFAULT_SHIPPING_LASTNAME)))
            .andExpect(jsonPath("$.[*].shippingCompany").value(hasItem(DEFAULT_SHIPPING_COMPANY)))
            .andExpect(jsonPath("$.[*].shippingAddress1").value(hasItem(DEFAULT_SHIPPING_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].shippingAddress2").value(hasItem(DEFAULT_SHIPPING_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].shippingCity").value(hasItem(DEFAULT_SHIPPING_CITY)))
            .andExpect(jsonPath("$.[*].shippingPostcode").value(hasItem(DEFAULT_SHIPPING_POSTCODE)))
            .andExpect(jsonPath("$.[*].shippingCountry").value(hasItem(DEFAULT_SHIPPING_COUNTRY)))
            .andExpect(jsonPath("$.[*].shippingZone").value(hasItem(DEFAULT_SHIPPING_ZONE)))
            .andExpect(jsonPath("$.[*].shippingAddressFormat").value(hasItem(DEFAULT_SHIPPING_ADDRESS_FORMAT)))
            .andExpect(jsonPath("$.[*].shippingMethod").value(hasItem(DEFAULT_SHIPPING_METHOD)))
            .andExpect(jsonPath("$.[*].paymentFirstname").value(hasItem(DEFAULT_PAYMENT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].paymentLastname").value(hasItem(DEFAULT_PAYMENT_LASTNAME)))
            .andExpect(jsonPath("$.[*].paymentCompany").value(hasItem(DEFAULT_PAYMENT_COMPANY)))
            .andExpect(jsonPath("$.[*].paymentAddress1").value(hasItem(DEFAULT_PAYMENT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].paymentAddress2").value(hasItem(DEFAULT_PAYMENT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].paymentCity").value(hasItem(DEFAULT_PAYMENT_CITY)))
            .andExpect(jsonPath("$.[*].paymentPostcode").value(hasItem(DEFAULT_PAYMENT_POSTCODE)))
            .andExpect(jsonPath("$.[*].paymentCountry").value(hasItem(DEFAULT_PAYMENT_COUNTRY)))
            .andExpect(jsonPath("$.[*].paymentZone").value(hasItem(DEFAULT_PAYMENT_ZONE)))
            .andExpect(jsonPath("$.[*].paymentAddressFormat").value(hasItem(DEFAULT_PAYMENT_ADDRESS_FORMAT)))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].reward").value(hasItem(DEFAULT_REWARD)))
            .andExpect(jsonPath("$.[*].commision").value(hasItem(DEFAULT_COMMISION)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].currencyValue").value(hasItem(DEFAULT_CURRENCY_VALUE)))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].paymentFlag").value(hasItem(DEFAULT_PAYMENT_FLAG)))
            .andExpect(jsonPath("$.[*].orderName").value(hasItem(DEFAULT_ORDER_NAME)));
    }
    
    @Test
    @Transactional
    public void getOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId().intValue()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.invoicePrefix").value(DEFAULT_INVOICE_PREFIX))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.shippingFirstname").value(DEFAULT_SHIPPING_FIRSTNAME))
            .andExpect(jsonPath("$.shippingLastname").value(DEFAULT_SHIPPING_LASTNAME))
            .andExpect(jsonPath("$.shippingCompany").value(DEFAULT_SHIPPING_COMPANY))
            .andExpect(jsonPath("$.shippingAddress1").value(DEFAULT_SHIPPING_ADDRESS_1))
            .andExpect(jsonPath("$.shippingAddress2").value(DEFAULT_SHIPPING_ADDRESS_2))
            .andExpect(jsonPath("$.shippingCity").value(DEFAULT_SHIPPING_CITY))
            .andExpect(jsonPath("$.shippingPostcode").value(DEFAULT_SHIPPING_POSTCODE))
            .andExpect(jsonPath("$.shippingCountry").value(DEFAULT_SHIPPING_COUNTRY))
            .andExpect(jsonPath("$.shippingZone").value(DEFAULT_SHIPPING_ZONE))
            .andExpect(jsonPath("$.shippingAddressFormat").value(DEFAULT_SHIPPING_ADDRESS_FORMAT))
            .andExpect(jsonPath("$.shippingMethod").value(DEFAULT_SHIPPING_METHOD))
            .andExpect(jsonPath("$.paymentFirstname").value(DEFAULT_PAYMENT_FIRSTNAME))
            .andExpect(jsonPath("$.paymentLastname").value(DEFAULT_PAYMENT_LASTNAME))
            .andExpect(jsonPath("$.paymentCompany").value(DEFAULT_PAYMENT_COMPANY))
            .andExpect(jsonPath("$.paymentAddress1").value(DEFAULT_PAYMENT_ADDRESS_1))
            .andExpect(jsonPath("$.paymentAddress2").value(DEFAULT_PAYMENT_ADDRESS_2))
            .andExpect(jsonPath("$.paymentCity").value(DEFAULT_PAYMENT_CITY))
            .andExpect(jsonPath("$.paymentPostcode").value(DEFAULT_PAYMENT_POSTCODE))
            .andExpect(jsonPath("$.paymentCountry").value(DEFAULT_PAYMENT_COUNTRY))
            .andExpect(jsonPath("$.paymentZone").value(DEFAULT_PAYMENT_ZONE))
            .andExpect(jsonPath("$.paymentAddressFormat").value(DEFAULT_PAYMENT_ADDRESS_FORMAT))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.reward").value(DEFAULT_REWARD))
            .andExpect(jsonPath("$.commision").value(DEFAULT_COMMISION))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.currencyValue").value(DEFAULT_CURRENCY_VALUE))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.paymentFlag").value(DEFAULT_PAYMENT_FLAG))
            .andExpect(jsonPath("$.orderName").value(DEFAULT_ORDER_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(updatedOrder);
        updatedOrder
            .invoiceNo(UPDATED_INVOICE_NO)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .shippingFirstname(UPDATED_SHIPPING_FIRSTNAME)
            .shippingLastname(UPDATED_SHIPPING_LASTNAME)
            .shippingCompany(UPDATED_SHIPPING_COMPANY)
            .shippingAddress1(UPDATED_SHIPPING_ADDRESS_1)
            .shippingAddress2(UPDATED_SHIPPING_ADDRESS_2)
            .shippingCity(UPDATED_SHIPPING_CITY)
            .shippingPostcode(UPDATED_SHIPPING_POSTCODE)
            .shippingCountry(UPDATED_SHIPPING_COUNTRY)
            .shippingZone(UPDATED_SHIPPING_ZONE)
            .shippingAddressFormat(UPDATED_SHIPPING_ADDRESS_FORMAT)
            .shippingMethod(UPDATED_SHIPPING_METHOD)
            .paymentFirstname(UPDATED_PAYMENT_FIRSTNAME)
            .paymentLastname(UPDATED_PAYMENT_LASTNAME)
            .paymentCompany(UPDATED_PAYMENT_COMPANY)
            .paymentAddress1(UPDATED_PAYMENT_ADDRESS_1)
            .paymentAddress2(UPDATED_PAYMENT_ADDRESS_2)
            .paymentCity(UPDATED_PAYMENT_CITY)
            .paymentPostcode(UPDATED_PAYMENT_POSTCODE)
            .paymentCountry(UPDATED_PAYMENT_COUNTRY)
            .paymentZone(UPDATED_PAYMENT_ZONE)
            .paymentAddressFormat(UPDATED_PAYMENT_ADDRESS_FORMAT)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .comment(UPDATED_COMMENT)
            .total(UPDATED_TOTAL)
            .reward(UPDATED_REWARD)
            .commision(UPDATED_COMMISION)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .currencyValue(UPDATED_CURRENCY_VALUE)
            .ip(UPDATED_IP)
            .paymentFlag(UPDATED_PAYMENT_FLAG)
            .orderName(UPDATED_ORDER_NAME);
        OrderDTO orderDTO = orderMapper.toDto(updatedOrder);

        restOrderMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testOrder.getInvoicePrefix()).isEqualTo(UPDATED_INVOICE_PREFIX);
        assertThat(testOrder.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testOrder.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testOrder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrder.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testOrder.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testOrder.getShippingFirstname()).isEqualTo(UPDATED_SHIPPING_FIRSTNAME);
        assertThat(testOrder.getShippingLastname()).isEqualTo(UPDATED_SHIPPING_LASTNAME);
        assertThat(testOrder.getShippingCompany()).isEqualTo(UPDATED_SHIPPING_COMPANY);
        assertThat(testOrder.getShippingAddress1()).isEqualTo(UPDATED_SHIPPING_ADDRESS_1);
        assertThat(testOrder.getShippingAddress2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_2);
        assertThat(testOrder.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testOrder.getShippingPostcode()).isEqualTo(UPDATED_SHIPPING_POSTCODE);
        assertThat(testOrder.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testOrder.getShippingZone()).isEqualTo(UPDATED_SHIPPING_ZONE);
        assertThat(testOrder.getShippingAddressFormat()).isEqualTo(UPDATED_SHIPPING_ADDRESS_FORMAT);
        assertThat(testOrder.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testOrder.getPaymentFirstname()).isEqualTo(UPDATED_PAYMENT_FIRSTNAME);
        assertThat(testOrder.getPaymentLastname()).isEqualTo(UPDATED_PAYMENT_LASTNAME);
        assertThat(testOrder.getPaymentCompany()).isEqualTo(UPDATED_PAYMENT_COMPANY);
        assertThat(testOrder.getPaymentAddress1()).isEqualTo(UPDATED_PAYMENT_ADDRESS_1);
        assertThat(testOrder.getPaymentAddress2()).isEqualTo(UPDATED_PAYMENT_ADDRESS_2);
        assertThat(testOrder.getPaymentCity()).isEqualTo(UPDATED_PAYMENT_CITY);
        assertThat(testOrder.getPaymentPostcode()).isEqualTo(UPDATED_PAYMENT_POSTCODE);
        assertThat(testOrder.getPaymentCountry()).isEqualTo(UPDATED_PAYMENT_COUNTRY);
        assertThat(testOrder.getPaymentZone()).isEqualTo(UPDATED_PAYMENT_ZONE);
        assertThat(testOrder.getPaymentAddressFormat()).isEqualTo(UPDATED_PAYMENT_ADDRESS_FORMAT);
        assertThat(testOrder.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testOrder.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testOrder.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testOrder.getReward()).isEqualTo(UPDATED_REWARD);
        assertThat(testOrder.getCommision()).isEqualTo(UPDATED_COMMISION);
        assertThat(testOrder.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testOrder.getCurrencyValue()).isEqualTo(UPDATED_CURRENCY_VALUE);
        assertThat(testOrder.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testOrder.getPaymentFlag()).isEqualTo(UPDATED_PAYMENT_FLAG);
        assertThat(testOrder.getOrderName()).isEqualTo(UPDATED_ORDER_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Order.class);
        Order order1 = new Order();
        order1.setId(1L);
        Order order2 = new Order();
        order2.setId(order1.getId());
        assertThat(order1).isEqualTo(order2);
        order2.setId(2L);
        assertThat(order1).isNotEqualTo(order2);
        order1.setId(null);
        assertThat(order1).isNotEqualTo(order2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDTO.class);
        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setId(1L);
        OrderDTO orderDTO2 = new OrderDTO();
        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
        orderDTO2.setId(orderDTO1.getId());
        assertThat(orderDTO1).isEqualTo(orderDTO2);
        orderDTO2.setId(2L);
        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
        orderDTO1.setId(null);
        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderMapper.fromId(null)).isNull();
    }
}
