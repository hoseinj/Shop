package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.OrderLog;
import com.mycompany.myapp.repository.OrderLogRepository;
import com.mycompany.myapp.service.OrderLogService;
import com.mycompany.myapp.service.dto.OrderLogDTO;
import com.mycompany.myapp.service.mapper.OrderLogMapper;
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
 * Integration tests for the {@link OrderLogResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class OrderLogResourceIT {

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INVOCE_PERFIX = "AAAAAAAAAA";
    private static final String UPDATED_INVOCE_PERFIX = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_FRISTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_FRISTNAME = "BBBBBBBBBB";

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

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final Integer DEFAULT_REWARD = 1;
    private static final Integer UPDATED_REWARD = 2;

    private static final BigDecimal DEFAULT_COMMISION = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMMISION = new BigDecimal(2);

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CURRENCY_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENCY_VALUE = new BigDecimal(2);

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYMENT_FLAG = 1;
    private static final Integer UPDATED_PAYMENT_FLAG = 2;

    private static final String DEFAULT_ORDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NAME = "BBBBBBBBBB";

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
    private OrderLogRepository orderLogRepository;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private OrderLogService orderLogService;

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

    private MockMvc restOrderLogMockMvc;

    private OrderLog orderLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderLogResource orderLogResource = new OrderLogResource(orderLogService);
        this.restOrderLogMockMvc = MockMvcBuilders.standaloneSetup(orderLogResource)
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
    public static OrderLog createEntity(EntityManager em) {
        OrderLog orderLog = new OrderLog()
            .invoiceNo(DEFAULT_INVOICE_NO)
            .invocePerfix(DEFAULT_INVOCE_PERFIX)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .email(DEFAULT_EMAIL)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .shippingFristname(DEFAULT_SHIPPING_FRISTNAME)
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
            .orderName(DEFAULT_ORDER_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return orderLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderLog createUpdatedEntity(EntityManager em) {
        OrderLog orderLog = new OrderLog()
            .invoiceNo(UPDATED_INVOICE_NO)
            .invocePerfix(UPDATED_INVOCE_PERFIX)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .shippingFristname(UPDATED_SHIPPING_FRISTNAME)
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
            .orderName(UPDATED_ORDER_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return orderLog;
    }

    @BeforeEach
    public void initTest() {
        orderLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderLog() throws Exception {
        int databaseSizeBeforeCreate = orderLogRepository.findAll().size();

        // Create the OrderLog
        OrderLogDTO orderLogDTO = orderLogMapper.toDto(orderLog);
        restOrderLogMockMvc.perform(post("/api/order-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLogDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderLog in the database
        List<OrderLog> orderLogList = orderLogRepository.findAll();
        assertThat(orderLogList).hasSize(databaseSizeBeforeCreate + 1);
        OrderLog testOrderLog = orderLogList.get(orderLogList.size() - 1);
        assertThat(testOrderLog.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testOrderLog.getInvocePerfix()).isEqualTo(DEFAULT_INVOCE_PERFIX);
        assertThat(testOrderLog.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testOrderLog.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testOrderLog.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrderLog.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testOrderLog.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testOrderLog.getShippingFristname()).isEqualTo(DEFAULT_SHIPPING_FRISTNAME);
        assertThat(testOrderLog.getShippingLastname()).isEqualTo(DEFAULT_SHIPPING_LASTNAME);
        assertThat(testOrderLog.getShippingCompany()).isEqualTo(DEFAULT_SHIPPING_COMPANY);
        assertThat(testOrderLog.getShippingAddress1()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_1);
        assertThat(testOrderLog.getShippingAddress2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_2);
        assertThat(testOrderLog.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testOrderLog.getShippingPostcode()).isEqualTo(DEFAULT_SHIPPING_POSTCODE);
        assertThat(testOrderLog.getShippingCountry()).isEqualTo(DEFAULT_SHIPPING_COUNTRY);
        assertThat(testOrderLog.getShippingZone()).isEqualTo(DEFAULT_SHIPPING_ZONE);
        assertThat(testOrderLog.getShippingAddressFormat()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_FORMAT);
        assertThat(testOrderLog.getShippingMethod()).isEqualTo(DEFAULT_SHIPPING_METHOD);
        assertThat(testOrderLog.getPaymentFirstname()).isEqualTo(DEFAULT_PAYMENT_FIRSTNAME);
        assertThat(testOrderLog.getPaymentLastname()).isEqualTo(DEFAULT_PAYMENT_LASTNAME);
        assertThat(testOrderLog.getPaymentCompany()).isEqualTo(DEFAULT_PAYMENT_COMPANY);
        assertThat(testOrderLog.getPaymentAddress1()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_1);
        assertThat(testOrderLog.getPaymentAddress2()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_2);
        assertThat(testOrderLog.getPaymentCity()).isEqualTo(DEFAULT_PAYMENT_CITY);
        assertThat(testOrderLog.getPaymentPostcode()).isEqualTo(DEFAULT_PAYMENT_POSTCODE);
        assertThat(testOrderLog.getPaymentCountry()).isEqualTo(DEFAULT_PAYMENT_COUNTRY);
        assertThat(testOrderLog.getPaymentZone()).isEqualTo(DEFAULT_PAYMENT_ZONE);
        assertThat(testOrderLog.getPaymentAddressFormat()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_FORMAT);
        assertThat(testOrderLog.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testOrderLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testOrderLog.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testOrderLog.getReward()).isEqualTo(DEFAULT_REWARD);
        assertThat(testOrderLog.getCommision()).isEqualTo(DEFAULT_COMMISION);
        assertThat(testOrderLog.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testOrderLog.getCurrencyValue()).isEqualTo(DEFAULT_CURRENCY_VALUE);
        assertThat(testOrderLog.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testOrderLog.getPaymentFlag()).isEqualTo(DEFAULT_PAYMENT_FLAG);
        assertThat(testOrderLog.getOrderName()).isEqualTo(DEFAULT_ORDER_NAME);
        assertThat(testOrderLog.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderLog.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderLog.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testOrderLog.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOrderLog.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrderLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderLogRepository.findAll().size();

        // Create the OrderLog with an existing ID
        orderLog.setId(1L);
        OrderLogDTO orderLogDTO = orderLogMapper.toDto(orderLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderLogMockMvc.perform(post("/api/order-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLog in the database
        List<OrderLog> orderLogList = orderLogRepository.findAll();
        assertThat(orderLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderLogs() throws Exception {
        // Initialize the database
        orderLogRepository.saveAndFlush(orderLog);

        // Get all the orderLogList
        restOrderLogMockMvc.perform(get("/api/order-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].invocePerfix").value(hasItem(DEFAULT_INVOCE_PERFIX)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].shippingFristname").value(hasItem(DEFAULT_SHIPPING_FRISTNAME)))
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
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].reward").value(hasItem(DEFAULT_REWARD)))
            .andExpect(jsonPath("$.[*].commision").value(hasItem(DEFAULT_COMMISION.intValue())))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].currencyValue").value(hasItem(DEFAULT_CURRENCY_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].paymentFlag").value(hasItem(DEFAULT_PAYMENT_FLAG)))
            .andExpect(jsonPath("$.[*].orderName").value(hasItem(DEFAULT_ORDER_NAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getOrderLog() throws Exception {
        // Initialize the database
        orderLogRepository.saveAndFlush(orderLog);

        // Get the orderLog
        restOrderLogMockMvc.perform(get("/api/order-logs/{id}", orderLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderLog.getId().intValue()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.invocePerfix").value(DEFAULT_INVOCE_PERFIX))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.shippingFristname").value(DEFAULT_SHIPPING_FRISTNAME))
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
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.reward").value(DEFAULT_REWARD))
            .andExpect(jsonPath("$.commision").value(DEFAULT_COMMISION.intValue()))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.currencyValue").value(DEFAULT_CURRENCY_VALUE.intValue()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.paymentFlag").value(DEFAULT_PAYMENT_FLAG))
            .andExpect(jsonPath("$.orderName").value(DEFAULT_ORDER_NAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingOrderLog() throws Exception {
        // Get the orderLog
        restOrderLogMockMvc.perform(get("/api/order-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderLog() throws Exception {
        // Initialize the database
        orderLogRepository.saveAndFlush(orderLog);

        int databaseSizeBeforeUpdate = orderLogRepository.findAll().size();

        // Update the orderLog
        OrderLog updatedOrderLog = orderLogRepository.findById(orderLog.getId()).get();
        // Disconnect from session so that the updates on updatedOrderLog are not directly saved in db
        em.detach(updatedOrderLog);
        updatedOrderLog
            .invoiceNo(UPDATED_INVOICE_NO)
            .invocePerfix(UPDATED_INVOCE_PERFIX)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .shippingFristname(UPDATED_SHIPPING_FRISTNAME)
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
            .orderName(UPDATED_ORDER_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        OrderLogDTO orderLogDTO = orderLogMapper.toDto(updatedOrderLog);

        restOrderLogMockMvc.perform(put("/api/order-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLogDTO)))
            .andExpect(status().isOk());

        // Validate the OrderLog in the database
        List<OrderLog> orderLogList = orderLogRepository.findAll();
        assertThat(orderLogList).hasSize(databaseSizeBeforeUpdate);
        OrderLog testOrderLog = orderLogList.get(orderLogList.size() - 1);
        assertThat(testOrderLog.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testOrderLog.getInvocePerfix()).isEqualTo(UPDATED_INVOCE_PERFIX);
        assertThat(testOrderLog.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testOrderLog.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testOrderLog.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrderLog.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testOrderLog.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testOrderLog.getShippingFristname()).isEqualTo(UPDATED_SHIPPING_FRISTNAME);
        assertThat(testOrderLog.getShippingLastname()).isEqualTo(UPDATED_SHIPPING_LASTNAME);
        assertThat(testOrderLog.getShippingCompany()).isEqualTo(UPDATED_SHIPPING_COMPANY);
        assertThat(testOrderLog.getShippingAddress1()).isEqualTo(UPDATED_SHIPPING_ADDRESS_1);
        assertThat(testOrderLog.getShippingAddress2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_2);
        assertThat(testOrderLog.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testOrderLog.getShippingPostcode()).isEqualTo(UPDATED_SHIPPING_POSTCODE);
        assertThat(testOrderLog.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testOrderLog.getShippingZone()).isEqualTo(UPDATED_SHIPPING_ZONE);
        assertThat(testOrderLog.getShippingAddressFormat()).isEqualTo(UPDATED_SHIPPING_ADDRESS_FORMAT);
        assertThat(testOrderLog.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testOrderLog.getPaymentFirstname()).isEqualTo(UPDATED_PAYMENT_FIRSTNAME);
        assertThat(testOrderLog.getPaymentLastname()).isEqualTo(UPDATED_PAYMENT_LASTNAME);
        assertThat(testOrderLog.getPaymentCompany()).isEqualTo(UPDATED_PAYMENT_COMPANY);
        assertThat(testOrderLog.getPaymentAddress1()).isEqualTo(UPDATED_PAYMENT_ADDRESS_1);
        assertThat(testOrderLog.getPaymentAddress2()).isEqualTo(UPDATED_PAYMENT_ADDRESS_2);
        assertThat(testOrderLog.getPaymentCity()).isEqualTo(UPDATED_PAYMENT_CITY);
        assertThat(testOrderLog.getPaymentPostcode()).isEqualTo(UPDATED_PAYMENT_POSTCODE);
        assertThat(testOrderLog.getPaymentCountry()).isEqualTo(UPDATED_PAYMENT_COUNTRY);
        assertThat(testOrderLog.getPaymentZone()).isEqualTo(UPDATED_PAYMENT_ZONE);
        assertThat(testOrderLog.getPaymentAddressFormat()).isEqualTo(UPDATED_PAYMENT_ADDRESS_FORMAT);
        assertThat(testOrderLog.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testOrderLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testOrderLog.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testOrderLog.getReward()).isEqualTo(UPDATED_REWARD);
        assertThat(testOrderLog.getCommision()).isEqualTo(UPDATED_COMMISION);
        assertThat(testOrderLog.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testOrderLog.getCurrencyValue()).isEqualTo(UPDATED_CURRENCY_VALUE);
        assertThat(testOrderLog.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testOrderLog.getPaymentFlag()).isEqualTo(UPDATED_PAYMENT_FLAG);
        assertThat(testOrderLog.getOrderName()).isEqualTo(UPDATED_ORDER_NAME);
        assertThat(testOrderLog.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderLog.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderLog.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testOrderLog.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOrderLog.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderLog() throws Exception {
        int databaseSizeBeforeUpdate = orderLogRepository.findAll().size();

        // Create the OrderLog
        OrderLogDTO orderLogDTO = orderLogMapper.toDto(orderLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderLogMockMvc.perform(put("/api/order-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLog in the database
        List<OrderLog> orderLogList = orderLogRepository.findAll();
        assertThat(orderLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderLog() throws Exception {
        // Initialize the database
        orderLogRepository.saveAndFlush(orderLog);

        int databaseSizeBeforeDelete = orderLogRepository.findAll().size();

        // Delete the orderLog
        restOrderLogMockMvc.perform(delete("/api/order-logs/{id}", orderLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderLog> orderLogList = orderLogRepository.findAll();
        assertThat(orderLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLog.class);
        OrderLog orderLog1 = new OrderLog();
        orderLog1.setId(1L);
        OrderLog orderLog2 = new OrderLog();
        orderLog2.setId(orderLog1.getId());
        assertThat(orderLog1).isEqualTo(orderLog2);
        orderLog2.setId(2L);
        assertThat(orderLog1).isNotEqualTo(orderLog2);
        orderLog1.setId(null);
        assertThat(orderLog1).isNotEqualTo(orderLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLogDTO.class);
        OrderLogDTO orderLogDTO1 = new OrderLogDTO();
        orderLogDTO1.setId(1L);
        OrderLogDTO orderLogDTO2 = new OrderLogDTO();
        assertThat(orderLogDTO1).isNotEqualTo(orderLogDTO2);
        orderLogDTO2.setId(orderLogDTO1.getId());
        assertThat(orderLogDTO1).isEqualTo(orderLogDTO2);
        orderLogDTO2.setId(2L);
        assertThat(orderLogDTO1).isNotEqualTo(orderLogDTO2);
        orderLogDTO1.setId(null);
        assertThat(orderLogDTO1).isNotEqualTo(orderLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderLogMapper.fromId(null)).isNull();
    }
}
