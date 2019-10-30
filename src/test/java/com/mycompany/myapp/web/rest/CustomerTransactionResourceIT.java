package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CustomerTransaction;
import com.mycompany.myapp.repository.CustomerTransactionRepository;
import com.mycompany.myapp.service.CustomerTransactionService;
import com.mycompany.myapp.service.dto.CustomerTransactionDTO;
import com.mycompany.myapp.service.mapper.CustomerTransactionMapper;
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
 * Integration tests for the {@link CustomerTransactionResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CustomerTransactionResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

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
    private CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    private CustomerTransactionMapper customerTransactionMapper;

    @Autowired
    private CustomerTransactionService customerTransactionService;

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

    private MockMvc restCustomerTransactionMockMvc;

    private CustomerTransaction customerTransaction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerTransactionResource customerTransactionResource = new CustomerTransactionResource(customerTransactionService);
        this.restCustomerTransactionMockMvc = MockMvcBuilders.standaloneSetup(customerTransactionResource)
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
    public static CustomerTransaction createEntity(EntityManager em) {
        CustomerTransaction customerTransaction = new CustomerTransaction()
            .description(DEFAULT_DESCRIPTION)
            .amount(DEFAULT_AMOUNT)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return customerTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerTransaction createUpdatedEntity(EntityManager em) {
        CustomerTransaction customerTransaction = new CustomerTransaction()
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return customerTransaction;
    }

    @BeforeEach
    public void initTest() {
        customerTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerTransaction() throws Exception {
        int databaseSizeBeforeCreate = customerTransactionRepository.findAll().size();

        // Create the CustomerTransaction
        CustomerTransactionDTO customerTransactionDTO = customerTransactionMapper.toDto(customerTransaction);
        restCustomerTransactionMockMvc.perform(post("/api/customer-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerTransaction in the database
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.findAll();
        assertThat(customerTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerTransaction testCustomerTransaction = customerTransactionList.get(customerTransactionList.size() - 1);
        assertThat(testCustomerTransaction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomerTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCustomerTransaction.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCustomerTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomerTransaction.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCustomerTransaction.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomerTransaction.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCustomerTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerTransactionRepository.findAll().size();

        // Create the CustomerTransaction with an existing ID
        customerTransaction.setId(1L);
        CustomerTransactionDTO customerTransactionDTO = customerTransactionMapper.toDto(customerTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerTransactionMockMvc.perform(post("/api/customer-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerTransaction in the database
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.findAll();
        assertThat(customerTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerTransactions() throws Exception {
        // Initialize the database
        customerTransactionRepository.saveAndFlush(customerTransaction);

        // Get all the customerTransactionList
        restCustomerTransactionMockMvc.perform(get("/api/customer-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCustomerTransaction() throws Exception {
        // Initialize the database
        customerTransactionRepository.saveAndFlush(customerTransaction);

        // Get the customerTransaction
        restCustomerTransactionMockMvc.perform(get("/api/customer-transactions/{id}", customerTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerTransaction.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerTransaction() throws Exception {
        // Get the customerTransaction
        restCustomerTransactionMockMvc.perform(get("/api/customer-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerTransaction() throws Exception {
        // Initialize the database
        customerTransactionRepository.saveAndFlush(customerTransaction);

        int databaseSizeBeforeUpdate = customerTransactionRepository.findAll().size();

        // Update the customerTransaction
        CustomerTransaction updatedCustomerTransaction = customerTransactionRepository.findById(customerTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerTransaction are not directly saved in db
        em.detach(updatedCustomerTransaction);
        updatedCustomerTransaction
            .description(UPDATED_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CustomerTransactionDTO customerTransactionDTO = customerTransactionMapper.toDto(updatedCustomerTransaction);

        restCustomerTransactionMockMvc.perform(put("/api/customer-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerTransaction in the database
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.findAll();
        assertThat(customerTransactionList).hasSize(databaseSizeBeforeUpdate);
        CustomerTransaction testCustomerTransaction = customerTransactionList.get(customerTransactionList.size() - 1);
        assertThat(testCustomerTransaction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomerTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCustomerTransaction.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCustomerTransaction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomerTransaction.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCustomerTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomerTransaction.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerTransaction() throws Exception {
        int databaseSizeBeforeUpdate = customerTransactionRepository.findAll().size();

        // Create the CustomerTransaction
        CustomerTransactionDTO customerTransactionDTO = customerTransactionMapper.toDto(customerTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerTransactionMockMvc.perform(put("/api/customer-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerTransaction in the database
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.findAll();
        assertThat(customerTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerTransaction() throws Exception {
        // Initialize the database
        customerTransactionRepository.saveAndFlush(customerTransaction);

        int databaseSizeBeforeDelete = customerTransactionRepository.findAll().size();

        // Delete the customerTransaction
        restCustomerTransactionMockMvc.perform(delete("/api/customer-transactions/{id}", customerTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.findAll();
        assertThat(customerTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerTransaction.class);
        CustomerTransaction customerTransaction1 = new CustomerTransaction();
        customerTransaction1.setId(1L);
        CustomerTransaction customerTransaction2 = new CustomerTransaction();
        customerTransaction2.setId(customerTransaction1.getId());
        assertThat(customerTransaction1).isEqualTo(customerTransaction2);
        customerTransaction2.setId(2L);
        assertThat(customerTransaction1).isNotEqualTo(customerTransaction2);
        customerTransaction1.setId(null);
        assertThat(customerTransaction1).isNotEqualTo(customerTransaction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerTransactionDTO.class);
        CustomerTransactionDTO customerTransactionDTO1 = new CustomerTransactionDTO();
        customerTransactionDTO1.setId(1L);
        CustomerTransactionDTO customerTransactionDTO2 = new CustomerTransactionDTO();
        assertThat(customerTransactionDTO1).isNotEqualTo(customerTransactionDTO2);
        customerTransactionDTO2.setId(customerTransactionDTO1.getId());
        assertThat(customerTransactionDTO1).isEqualTo(customerTransactionDTO2);
        customerTransactionDTO2.setId(2L);
        assertThat(customerTransactionDTO1).isNotEqualTo(customerTransactionDTO2);
        customerTransactionDTO1.setId(null);
        assertThat(customerTransactionDTO1).isNotEqualTo(customerTransactionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerTransactionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerTransactionMapper.fromId(null)).isNull();
    }
}
