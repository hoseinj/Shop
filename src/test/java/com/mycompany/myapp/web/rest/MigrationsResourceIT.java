package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Migrations;
import com.mycompany.myapp.repository.MigrationsRepository;
import com.mycompany.myapp.service.MigrationsService;
import com.mycompany.myapp.service.dto.MigrationsDTO;
import com.mycompany.myapp.service.mapper.MigrationsMapper;
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
 * Integration tests for the {@link MigrationsResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class MigrationsResourceIT {

    private static final BigDecimal DEFAULT_TIMESTAMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIMESTAMP = new BigDecimal(2);

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

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
    private MigrationsRepository migrationsRepository;

    @Autowired
    private MigrationsMapper migrationsMapper;

    @Autowired
    private MigrationsService migrationsService;

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

    private MockMvc restMigrationsMockMvc;

    private Migrations migrations;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MigrationsResource migrationsResource = new MigrationsResource(migrationsService);
        this.restMigrationsMockMvc = MockMvcBuilders.standaloneSetup(migrationsResource)
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
    public static Migrations createEntity(EntityManager em) {
        Migrations migrations = new Migrations()
            .timestamp(DEFAULT_TIMESTAMP)
            .name(DEFAULT_NAME)
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
            .orderName(DEFAULT_ORDER_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return migrations;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Migrations createUpdatedEntity(EntityManager em) {
        Migrations migrations = new Migrations()
            .timestamp(UPDATED_TIMESTAMP)
            .name(UPDATED_NAME)
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
            .orderName(UPDATED_ORDER_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return migrations;
    }

    @BeforeEach
    public void initTest() {
        migrations = createEntity(em);
    }

    @Test
    @Transactional
    public void createMigrations() throws Exception {
        int databaseSizeBeforeCreate = migrationsRepository.findAll().size();

        // Create the Migrations
        MigrationsDTO migrationsDTO = migrationsMapper.toDto(migrations);
        restMigrationsMockMvc.perform(post("/api/migrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(migrationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Migrations in the database
        List<Migrations> migrationsList = migrationsRepository.findAll();
        assertThat(migrationsList).hasSize(databaseSizeBeforeCreate + 1);
        Migrations testMigrations = migrationsList.get(migrationsList.size() - 1);
        assertThat(testMigrations.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testMigrations.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMigrations.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testMigrations.getInvoicePrefix()).isEqualTo(DEFAULT_INVOICE_PREFIX);
        assertThat(testMigrations.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testMigrations.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testMigrations.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMigrations.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMigrations.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testMigrations.getShippingFirstname()).isEqualTo(DEFAULT_SHIPPING_FIRSTNAME);
        assertThat(testMigrations.getShippingLastname()).isEqualTo(DEFAULT_SHIPPING_LASTNAME);
        assertThat(testMigrations.getShippingCompany()).isEqualTo(DEFAULT_SHIPPING_COMPANY);
        assertThat(testMigrations.getShippingAddress1()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_1);
        assertThat(testMigrations.getShippingAddress2()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_2);
        assertThat(testMigrations.getShippingCity()).isEqualTo(DEFAULT_SHIPPING_CITY);
        assertThat(testMigrations.getShippingPostcode()).isEqualTo(DEFAULT_SHIPPING_POSTCODE);
        assertThat(testMigrations.getShippingCountry()).isEqualTo(DEFAULT_SHIPPING_COUNTRY);
        assertThat(testMigrations.getShippingZone()).isEqualTo(DEFAULT_SHIPPING_ZONE);
        assertThat(testMigrations.getShippingAddressFormat()).isEqualTo(DEFAULT_SHIPPING_ADDRESS_FORMAT);
        assertThat(testMigrations.getShippingMethod()).isEqualTo(DEFAULT_SHIPPING_METHOD);
        assertThat(testMigrations.getPaymentFirstname()).isEqualTo(DEFAULT_PAYMENT_FIRSTNAME);
        assertThat(testMigrations.getPaymentLastname()).isEqualTo(DEFAULT_PAYMENT_LASTNAME);
        assertThat(testMigrations.getPaymentCompany()).isEqualTo(DEFAULT_PAYMENT_COMPANY);
        assertThat(testMigrations.getPaymentAddress1()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_1);
        assertThat(testMigrations.getPaymentAddress2()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_2);
        assertThat(testMigrations.getPaymentCity()).isEqualTo(DEFAULT_PAYMENT_CITY);
        assertThat(testMigrations.getPaymentPostcode()).isEqualTo(DEFAULT_PAYMENT_POSTCODE);
        assertThat(testMigrations.getPaymentCountry()).isEqualTo(DEFAULT_PAYMENT_COUNTRY);
        assertThat(testMigrations.getPaymentZone()).isEqualTo(DEFAULT_PAYMENT_ZONE);
        assertThat(testMigrations.getPaymentAddressFormat()).isEqualTo(DEFAULT_PAYMENT_ADDRESS_FORMAT);
        assertThat(testMigrations.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testMigrations.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testMigrations.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testMigrations.getReward()).isEqualTo(DEFAULT_REWARD);
        assertThat(testMigrations.getCommision()).isEqualTo(DEFAULT_COMMISION);
        assertThat(testMigrations.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testMigrations.getCurrencyValue()).isEqualTo(DEFAULT_CURRENCY_VALUE);
        assertThat(testMigrations.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testMigrations.getPaymentFlag()).isEqualTo(DEFAULT_PAYMENT_FLAG);
        assertThat(testMigrations.getOrderName()).isEqualTo(DEFAULT_ORDER_NAME);
        assertThat(testMigrations.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testMigrations.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMigrations.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testMigrations.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMigrations.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createMigrationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = migrationsRepository.findAll().size();

        // Create the Migrations with an existing ID
        migrations.setId(1L);
        MigrationsDTO migrationsDTO = migrationsMapper.toDto(migrations);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMigrationsMockMvc.perform(post("/api/migrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(migrationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Migrations in the database
        List<Migrations> migrationsList = migrationsRepository.findAll();
        assertThat(migrationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMigrations() throws Exception {
        // Initialize the database
        migrationsRepository.saveAndFlush(migrations);

        // Get all the migrationsList
        restMigrationsMockMvc.perform(get("/api/migrations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(migrations.getId().intValue())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
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
    public void getMigrations() throws Exception {
        // Initialize the database
        migrationsRepository.saveAndFlush(migrations);

        // Get the migrations
        restMigrationsMockMvc.perform(get("/api/migrations/{id}", migrations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(migrations.getId().intValue()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
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
    public void getNonExistingMigrations() throws Exception {
        // Get the migrations
        restMigrationsMockMvc.perform(get("/api/migrations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMigrations() throws Exception {
        // Initialize the database
        migrationsRepository.saveAndFlush(migrations);

        int databaseSizeBeforeUpdate = migrationsRepository.findAll().size();

        // Update the migrations
        Migrations updatedMigrations = migrationsRepository.findById(migrations.getId()).get();
        // Disconnect from session so that the updates on updatedMigrations are not directly saved in db
        em.detach(updatedMigrations);
        updatedMigrations
            .timestamp(UPDATED_TIMESTAMP)
            .name(UPDATED_NAME)
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
            .orderName(UPDATED_ORDER_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        MigrationsDTO migrationsDTO = migrationsMapper.toDto(updatedMigrations);

        restMigrationsMockMvc.perform(put("/api/migrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(migrationsDTO)))
            .andExpect(status().isOk());

        // Validate the Migrations in the database
        List<Migrations> migrationsList = migrationsRepository.findAll();
        assertThat(migrationsList).hasSize(databaseSizeBeforeUpdate);
        Migrations testMigrations = migrationsList.get(migrationsList.size() - 1);
        assertThat(testMigrations.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testMigrations.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMigrations.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testMigrations.getInvoicePrefix()).isEqualTo(UPDATED_INVOICE_PREFIX);
        assertThat(testMigrations.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testMigrations.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testMigrations.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMigrations.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMigrations.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testMigrations.getShippingFirstname()).isEqualTo(UPDATED_SHIPPING_FIRSTNAME);
        assertThat(testMigrations.getShippingLastname()).isEqualTo(UPDATED_SHIPPING_LASTNAME);
        assertThat(testMigrations.getShippingCompany()).isEqualTo(UPDATED_SHIPPING_COMPANY);
        assertThat(testMigrations.getShippingAddress1()).isEqualTo(UPDATED_SHIPPING_ADDRESS_1);
        assertThat(testMigrations.getShippingAddress2()).isEqualTo(UPDATED_SHIPPING_ADDRESS_2);
        assertThat(testMigrations.getShippingCity()).isEqualTo(UPDATED_SHIPPING_CITY);
        assertThat(testMigrations.getShippingPostcode()).isEqualTo(UPDATED_SHIPPING_POSTCODE);
        assertThat(testMigrations.getShippingCountry()).isEqualTo(UPDATED_SHIPPING_COUNTRY);
        assertThat(testMigrations.getShippingZone()).isEqualTo(UPDATED_SHIPPING_ZONE);
        assertThat(testMigrations.getShippingAddressFormat()).isEqualTo(UPDATED_SHIPPING_ADDRESS_FORMAT);
        assertThat(testMigrations.getShippingMethod()).isEqualTo(UPDATED_SHIPPING_METHOD);
        assertThat(testMigrations.getPaymentFirstname()).isEqualTo(UPDATED_PAYMENT_FIRSTNAME);
        assertThat(testMigrations.getPaymentLastname()).isEqualTo(UPDATED_PAYMENT_LASTNAME);
        assertThat(testMigrations.getPaymentCompany()).isEqualTo(UPDATED_PAYMENT_COMPANY);
        assertThat(testMigrations.getPaymentAddress1()).isEqualTo(UPDATED_PAYMENT_ADDRESS_1);
        assertThat(testMigrations.getPaymentAddress2()).isEqualTo(UPDATED_PAYMENT_ADDRESS_2);
        assertThat(testMigrations.getPaymentCity()).isEqualTo(UPDATED_PAYMENT_CITY);
        assertThat(testMigrations.getPaymentPostcode()).isEqualTo(UPDATED_PAYMENT_POSTCODE);
        assertThat(testMigrations.getPaymentCountry()).isEqualTo(UPDATED_PAYMENT_COUNTRY);
        assertThat(testMigrations.getPaymentZone()).isEqualTo(UPDATED_PAYMENT_ZONE);
        assertThat(testMigrations.getPaymentAddressFormat()).isEqualTo(UPDATED_PAYMENT_ADDRESS_FORMAT);
        assertThat(testMigrations.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testMigrations.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testMigrations.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testMigrations.getReward()).isEqualTo(UPDATED_REWARD);
        assertThat(testMigrations.getCommision()).isEqualTo(UPDATED_COMMISION);
        assertThat(testMigrations.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testMigrations.getCurrencyValue()).isEqualTo(UPDATED_CURRENCY_VALUE);
        assertThat(testMigrations.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testMigrations.getPaymentFlag()).isEqualTo(UPDATED_PAYMENT_FLAG);
        assertThat(testMigrations.getOrderName()).isEqualTo(UPDATED_ORDER_NAME);
        assertThat(testMigrations.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testMigrations.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMigrations.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMigrations.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMigrations.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMigrations() throws Exception {
        int databaseSizeBeforeUpdate = migrationsRepository.findAll().size();

        // Create the Migrations
        MigrationsDTO migrationsDTO = migrationsMapper.toDto(migrations);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMigrationsMockMvc.perform(put("/api/migrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(migrationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Migrations in the database
        List<Migrations> migrationsList = migrationsRepository.findAll();
        assertThat(migrationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMigrations() throws Exception {
        // Initialize the database
        migrationsRepository.saveAndFlush(migrations);

        int databaseSizeBeforeDelete = migrationsRepository.findAll().size();

        // Delete the migrations
        restMigrationsMockMvc.perform(delete("/api/migrations/{id}", migrations.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Migrations> migrationsList = migrationsRepository.findAll();
        assertThat(migrationsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Migrations.class);
        Migrations migrations1 = new Migrations();
        migrations1.setId(1L);
        Migrations migrations2 = new Migrations();
        migrations2.setId(migrations1.getId());
        assertThat(migrations1).isEqualTo(migrations2);
        migrations2.setId(2L);
        assertThat(migrations1).isNotEqualTo(migrations2);
        migrations1.setId(null);
        assertThat(migrations1).isNotEqualTo(migrations2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MigrationsDTO.class);
        MigrationsDTO migrationsDTO1 = new MigrationsDTO();
        migrationsDTO1.setId(1L);
        MigrationsDTO migrationsDTO2 = new MigrationsDTO();
        assertThat(migrationsDTO1).isNotEqualTo(migrationsDTO2);
        migrationsDTO2.setId(migrationsDTO1.getId());
        assertThat(migrationsDTO1).isEqualTo(migrationsDTO2);
        migrationsDTO2.setId(2L);
        assertThat(migrationsDTO1).isNotEqualTo(migrationsDTO2);
        migrationsDTO1.setId(null);
        assertThat(migrationsDTO1).isNotEqualTo(migrationsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(migrationsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(migrationsMapper.fromId(null)).isNull();
    }
}
