package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Customer;
import com.mycompany.myapp.repository.CustomerRepository;
import com.mycompany.myapp.service.CustomerService;
import com.mycompany.myapp.service.dto.CustomerDTO;
import com.mycompany.myapp.service.mapper.CustomerMapper;
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
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CustomerResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR_PATH = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAIL_STATUS = 1;
    private static final Integer UPDATED_MAIL_STATUS = 2;

    private static final Integer DEFAULT_DELETE_FLAG = 1;
    private static final Integer UPDATED_DELETE_FLAG = 2;

    private static final String DEFAULT_LASTLOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LASTLOGIN = "BBBBBBBBBB";

    private static final Long DEFAULT_NEWSLETTER = 1L;
    private static final Long UPDATED_NEWSLETTER = 2L;

    private static final Long DEFAULT_SAFE = 1L;
    private static final Long UPDATED_SAFE = 2L;

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

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
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

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

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerResource customerResource = new CustomerResource(customerService);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
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
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .username(DEFAULT_USERNAME)
            .email(DEFAULT_EMAIL)
            .password(DEFAULT_PASSWORD)
            .mobile(DEFAULT_MOBILE)
            .address(DEFAULT_ADDRESS)
            .city(DEFAULT_CITY)
            .pincode(DEFAULT_PINCODE)
            .avatar(DEFAULT_AVATAR)
            .avatarPath(DEFAULT_AVATAR_PATH)
            .mailStatus(DEFAULT_MAIL_STATUS)
            .deleteFlag(DEFAULT_DELETE_FLAG)
            .lastlogin(DEFAULT_LASTLOGIN)
            .newsletter(DEFAULT_NEWSLETTER)
            .safe(DEFAULT_SAFE)
            .ip(DEFAULT_IP)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return customer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .username(UPDATED_USERNAME)
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .mobile(UPDATED_MOBILE)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .pincode(UPDATED_PINCODE)
            .avatar(UPDATED_AVATAR)
            .avatarPath(UPDATED_AVATAR_PATH)
            .mailStatus(UPDATED_MAIL_STATUS)
            .deleteFlag(UPDATED_DELETE_FLAG)
            .lastlogin(UPDATED_LASTLOGIN)
            .newsletter(UPDATED_NEWSLETTER)
            .safe(UPDATED_SAFE)
            .ip(UPDATED_IP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testCustomer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomer.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testCustomer.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testCustomer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomer.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCustomer.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testCustomer.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testCustomer.getAvatarPath()).isEqualTo(DEFAULT_AVATAR_PATH);
        assertThat(testCustomer.getMailStatus()).isEqualTo(DEFAULT_MAIL_STATUS);
        assertThat(testCustomer.getDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testCustomer.getLastlogin()).isEqualTo(DEFAULT_LASTLOGIN);
        assertThat(testCustomer.getNewsletter()).isEqualTo(DEFAULT_NEWSLETTER);
        assertThat(testCustomer.getSafe()).isEqualTo(DEFAULT_SAFE);
        assertThat(testCustomer.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testCustomer.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomer.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCustomer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomer.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId(1L);
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].avatarPath").value(hasItem(DEFAULT_AVATAR_PATH)))
            .andExpect(jsonPath("$.[*].mailStatus").value(hasItem(DEFAULT_MAIL_STATUS)))
            .andExpect(jsonPath("$.[*].deleteFlag").value(hasItem(DEFAULT_DELETE_FLAG)))
            .andExpect(jsonPath("$.[*].lastlogin").value(hasItem(DEFAULT_LASTLOGIN)))
            .andExpect(jsonPath("$.[*].newsletter").value(hasItem(DEFAULT_NEWSLETTER.intValue())))
            .andExpect(jsonPath("$.[*].safe").value(hasItem(DEFAULT_SAFE.intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.avatarPath").value(DEFAULT_AVATAR_PATH))
            .andExpect(jsonPath("$.mailStatus").value(DEFAULT_MAIL_STATUS))
            .andExpect(jsonPath("$.deleteFlag").value(DEFAULT_DELETE_FLAG))
            .andExpect(jsonPath("$.lastlogin").value(DEFAULT_LASTLOGIN))
            .andExpect(jsonPath("$.newsletter").value(DEFAULT_NEWSLETTER.intValue()))
            .andExpect(jsonPath("$.safe").value(DEFAULT_SAFE.intValue()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .username(UPDATED_USERNAME)
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .mobile(UPDATED_MOBILE)
            .address(UPDATED_ADDRESS)
            .city(UPDATED_CITY)
            .pincode(UPDATED_PINCODE)
            .avatar(UPDATED_AVATAR)
            .avatarPath(UPDATED_AVATAR_PATH)
            .mailStatus(UPDATED_MAIL_STATUS)
            .deleteFlag(UPDATED_DELETE_FLAG)
            .lastlogin(UPDATED_LASTLOGIN)
            .newsletter(UPDATED_NEWSLETTER)
            .safe(UPDATED_SAFE)
            .ip(UPDATED_IP)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CustomerDTO customerDTO = customerMapper.toDto(updatedCustomer);

        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testCustomer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomer.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testCustomer.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testCustomer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomer.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCustomer.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testCustomer.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testCustomer.getAvatarPath()).isEqualTo(UPDATED_AVATAR_PATH);
        assertThat(testCustomer.getMailStatus()).isEqualTo(UPDATED_MAIL_STATUS);
        assertThat(testCustomer.getDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testCustomer.getLastlogin()).isEqualTo(UPDATED_LASTLOGIN);
        assertThat(testCustomer.getNewsletter()).isEqualTo(UPDATED_NEWSLETTER);
        assertThat(testCustomer.getSafe()).isEqualTo(UPDATED_SAFE);
        assertThat(testCustomer.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testCustomer.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomer.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCustomer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomer.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);
        customer2.setId(2L);
        assertThat(customer1).isNotEqualTo(customer2);
        customer1.setId(null);
        assertThat(customer1).isNotEqualTo(customer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerDTO.class);
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1L);
        CustomerDTO customerDTO2 = new CustomerDTO();
        assertThat(customerDTO1).isNotEqualTo(customerDTO2);
        customerDTO2.setId(customerDTO1.getId());
        assertThat(customerDTO1).isEqualTo(customerDTO2);
        customerDTO2.setId(2L);
        assertThat(customerDTO1).isNotEqualTo(customerDTO2);
        customerDTO1.setId(null);
        assertThat(customerDTO1).isNotEqualTo(customerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerMapper.fromId(null)).isNull();
    }
}
