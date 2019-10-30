package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CustomerIp;
import com.mycompany.myapp.repository.CustomerIpRepository;
import com.mycompany.myapp.service.CustomerIpService;
import com.mycompany.myapp.service.dto.CustomerIpDTO;
import com.mycompany.myapp.service.mapper.CustomerIpMapper;
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
 * Integration tests for the {@link CustomerIpResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CustomerIpResourceIT {

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

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
    private CustomerIpRepository customerIpRepository;

    @Autowired
    private CustomerIpMapper customerIpMapper;

    @Autowired
    private CustomerIpService customerIpService;

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

    private MockMvc restCustomerIpMockMvc;

    private CustomerIp customerIp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerIpResource customerIpResource = new CustomerIpResource(customerIpService);
        this.restCustomerIpMockMvc = MockMvcBuilders.standaloneSetup(customerIpResource)
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
    public static CustomerIp createEntity(EntityManager em) {
        CustomerIp customerIp = new CustomerIp()
            .ip(DEFAULT_IP)
            .dateAdded(DEFAULT_DATE_ADDED)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return customerIp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerIp createUpdatedEntity(EntityManager em) {
        CustomerIp customerIp = new CustomerIp()
            .ip(UPDATED_IP)
            .dateAdded(UPDATED_DATE_ADDED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return customerIp;
    }

    @BeforeEach
    public void initTest() {
        customerIp = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerIp() throws Exception {
        int databaseSizeBeforeCreate = customerIpRepository.findAll().size();

        // Create the CustomerIp
        CustomerIpDTO customerIpDTO = customerIpMapper.toDto(customerIp);
        restCustomerIpMockMvc.perform(post("/api/customer-ips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerIpDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerIp in the database
        List<CustomerIp> customerIpList = customerIpRepository.findAll();
        assertThat(customerIpList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerIp testCustomerIp = customerIpList.get(customerIpList.size() - 1);
        assertThat(testCustomerIp.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testCustomerIp.getDateAdded()).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testCustomerIp.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCustomerIp.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomerIp.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCustomerIp.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomerIp.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCustomerIpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerIpRepository.findAll().size();

        // Create the CustomerIp with an existing ID
        customerIp.setId(1L);
        CustomerIpDTO customerIpDTO = customerIpMapper.toDto(customerIp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerIpMockMvc.perform(post("/api/customer-ips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerIpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerIp in the database
        List<CustomerIp> customerIpList = customerIpRepository.findAll();
        assertThat(customerIpList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerIps() throws Exception {
        // Initialize the database
        customerIpRepository.saveAndFlush(customerIp);

        // Get all the customerIpList
        restCustomerIpMockMvc.perform(get("/api/customer-ips?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerIp.getId().intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].dateAdded").value(hasItem(DEFAULT_DATE_ADDED)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCustomerIp() throws Exception {
        // Initialize the database
        customerIpRepository.saveAndFlush(customerIp);

        // Get the customerIp
        restCustomerIpMockMvc.perform(get("/api/customer-ips/{id}", customerIp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerIp.getId().intValue()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.dateAdded").value(DEFAULT_DATE_ADDED))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerIp() throws Exception {
        // Get the customerIp
        restCustomerIpMockMvc.perform(get("/api/customer-ips/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerIp() throws Exception {
        // Initialize the database
        customerIpRepository.saveAndFlush(customerIp);

        int databaseSizeBeforeUpdate = customerIpRepository.findAll().size();

        // Update the customerIp
        CustomerIp updatedCustomerIp = customerIpRepository.findById(customerIp.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerIp are not directly saved in db
        em.detach(updatedCustomerIp);
        updatedCustomerIp
            .ip(UPDATED_IP)
            .dateAdded(UPDATED_DATE_ADDED)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CustomerIpDTO customerIpDTO = customerIpMapper.toDto(updatedCustomerIp);

        restCustomerIpMockMvc.perform(put("/api/customer-ips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerIpDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerIp in the database
        List<CustomerIp> customerIpList = customerIpRepository.findAll();
        assertThat(customerIpList).hasSize(databaseSizeBeforeUpdate);
        CustomerIp testCustomerIp = customerIpList.get(customerIpList.size() - 1);
        assertThat(testCustomerIp.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testCustomerIp.getDateAdded()).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testCustomerIp.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCustomerIp.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomerIp.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCustomerIp.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomerIp.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerIp() throws Exception {
        int databaseSizeBeforeUpdate = customerIpRepository.findAll().size();

        // Create the CustomerIp
        CustomerIpDTO customerIpDTO = customerIpMapper.toDto(customerIp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerIpMockMvc.perform(put("/api/customer-ips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerIpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerIp in the database
        List<CustomerIp> customerIpList = customerIpRepository.findAll();
        assertThat(customerIpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerIp() throws Exception {
        // Initialize the database
        customerIpRepository.saveAndFlush(customerIp);

        int databaseSizeBeforeDelete = customerIpRepository.findAll().size();

        // Delete the customerIp
        restCustomerIpMockMvc.perform(delete("/api/customer-ips/{id}", customerIp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerIp> customerIpList = customerIpRepository.findAll();
        assertThat(customerIpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerIp.class);
        CustomerIp customerIp1 = new CustomerIp();
        customerIp1.setId(1L);
        CustomerIp customerIp2 = new CustomerIp();
        customerIp2.setId(customerIp1.getId());
        assertThat(customerIp1).isEqualTo(customerIp2);
        customerIp2.setId(2L);
        assertThat(customerIp1).isNotEqualTo(customerIp2);
        customerIp1.setId(null);
        assertThat(customerIp1).isNotEqualTo(customerIp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerIpDTO.class);
        CustomerIpDTO customerIpDTO1 = new CustomerIpDTO();
        customerIpDTO1.setId(1L);
        CustomerIpDTO customerIpDTO2 = new CustomerIpDTO();
        assertThat(customerIpDTO1).isNotEqualTo(customerIpDTO2);
        customerIpDTO2.setId(customerIpDTO1.getId());
        assertThat(customerIpDTO1).isEqualTo(customerIpDTO2);
        customerIpDTO2.setId(2L);
        assertThat(customerIpDTO1).isNotEqualTo(customerIpDTO2);
        customerIpDTO1.setId(null);
        assertThat(customerIpDTO1).isNotEqualTo(customerIpDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerIpMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerIpMapper.fromId(null)).isNull();
    }
}
