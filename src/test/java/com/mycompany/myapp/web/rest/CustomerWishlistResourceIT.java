package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CustomerWishlist;
import com.mycompany.myapp.repository.CustomerWishlistRepository;
import com.mycompany.myapp.service.CustomerWishlistService;
import com.mycompany.myapp.service.dto.CustomerWishlistDTO;
import com.mycompany.myapp.service.mapper.CustomerWishlistMapper;
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
 * Integration tests for the {@link CustomerWishlistResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CustomerWishlistResourceIT {

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
    private CustomerWishlistRepository customerWishlistRepository;

    @Autowired
    private CustomerWishlistMapper customerWishlistMapper;

    @Autowired
    private CustomerWishlistService customerWishlistService;

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

    private MockMvc restCustomerWishlistMockMvc;

    private CustomerWishlist customerWishlist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerWishlistResource customerWishlistResource = new CustomerWishlistResource(customerWishlistService);
        this.restCustomerWishlistMockMvc = MockMvcBuilders.standaloneSetup(customerWishlistResource)
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
    public static CustomerWishlist createEntity(EntityManager em) {
        CustomerWishlist customerWishlist = new CustomerWishlist()
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return customerWishlist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerWishlist createUpdatedEntity(EntityManager em) {
        CustomerWishlist customerWishlist = new CustomerWishlist()
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return customerWishlist;
    }

    @BeforeEach
    public void initTest() {
        customerWishlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerWishlist() throws Exception {
        int databaseSizeBeforeCreate = customerWishlistRepository.findAll().size();

        // Create the CustomerWishlist
        CustomerWishlistDTO customerWishlistDTO = customerWishlistMapper.toDto(customerWishlist);
        restCustomerWishlistMockMvc.perform(post("/api/customer-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerWishlistDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerWishlist in the database
        List<CustomerWishlist> customerWishlistList = customerWishlistRepository.findAll();
        assertThat(customerWishlistList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerWishlist testCustomerWishlist = customerWishlistList.get(customerWishlistList.size() - 1);
        assertThat(testCustomerWishlist.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCustomerWishlist.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomerWishlist.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCustomerWishlist.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomerWishlist.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCustomerWishlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerWishlistRepository.findAll().size();

        // Create the CustomerWishlist with an existing ID
        customerWishlist.setId(1L);
        CustomerWishlistDTO customerWishlistDTO = customerWishlistMapper.toDto(customerWishlist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerWishlistMockMvc.perform(post("/api/customer-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerWishlistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerWishlist in the database
        List<CustomerWishlist> customerWishlistList = customerWishlistRepository.findAll();
        assertThat(customerWishlistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerWishlists() throws Exception {
        // Initialize the database
        customerWishlistRepository.saveAndFlush(customerWishlist);

        // Get all the customerWishlistList
        restCustomerWishlistMockMvc.perform(get("/api/customer-wishlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerWishlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCustomerWishlist() throws Exception {
        // Initialize the database
        customerWishlistRepository.saveAndFlush(customerWishlist);

        // Get the customerWishlist
        restCustomerWishlistMockMvc.perform(get("/api/customer-wishlists/{id}", customerWishlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerWishlist.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerWishlist() throws Exception {
        // Get the customerWishlist
        restCustomerWishlistMockMvc.perform(get("/api/customer-wishlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerWishlist() throws Exception {
        // Initialize the database
        customerWishlistRepository.saveAndFlush(customerWishlist);

        int databaseSizeBeforeUpdate = customerWishlistRepository.findAll().size();

        // Update the customerWishlist
        CustomerWishlist updatedCustomerWishlist = customerWishlistRepository.findById(customerWishlist.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerWishlist are not directly saved in db
        em.detach(updatedCustomerWishlist);
        updatedCustomerWishlist
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CustomerWishlistDTO customerWishlistDTO = customerWishlistMapper.toDto(updatedCustomerWishlist);

        restCustomerWishlistMockMvc.perform(put("/api/customer-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerWishlistDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerWishlist in the database
        List<CustomerWishlist> customerWishlistList = customerWishlistRepository.findAll();
        assertThat(customerWishlistList).hasSize(databaseSizeBeforeUpdate);
        CustomerWishlist testCustomerWishlist = customerWishlistList.get(customerWishlistList.size() - 1);
        assertThat(testCustomerWishlist.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCustomerWishlist.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomerWishlist.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCustomerWishlist.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomerWishlist.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerWishlist() throws Exception {
        int databaseSizeBeforeUpdate = customerWishlistRepository.findAll().size();

        // Create the CustomerWishlist
        CustomerWishlistDTO customerWishlistDTO = customerWishlistMapper.toDto(customerWishlist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerWishlistMockMvc.perform(put("/api/customer-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerWishlistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerWishlist in the database
        List<CustomerWishlist> customerWishlistList = customerWishlistRepository.findAll();
        assertThat(customerWishlistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerWishlist() throws Exception {
        // Initialize the database
        customerWishlistRepository.saveAndFlush(customerWishlist);

        int databaseSizeBeforeDelete = customerWishlistRepository.findAll().size();

        // Delete the customerWishlist
        restCustomerWishlistMockMvc.perform(delete("/api/customer-wishlists/{id}", customerWishlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerWishlist> customerWishlistList = customerWishlistRepository.findAll();
        assertThat(customerWishlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerWishlist.class);
        CustomerWishlist customerWishlist1 = new CustomerWishlist();
        customerWishlist1.setId(1L);
        CustomerWishlist customerWishlist2 = new CustomerWishlist();
        customerWishlist2.setId(customerWishlist1.getId());
        assertThat(customerWishlist1).isEqualTo(customerWishlist2);
        customerWishlist2.setId(2L);
        assertThat(customerWishlist1).isNotEqualTo(customerWishlist2);
        customerWishlist1.setId(null);
        assertThat(customerWishlist1).isNotEqualTo(customerWishlist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerWishlistDTO.class);
        CustomerWishlistDTO customerWishlistDTO1 = new CustomerWishlistDTO();
        customerWishlistDTO1.setId(1L);
        CustomerWishlistDTO customerWishlistDTO2 = new CustomerWishlistDTO();
        assertThat(customerWishlistDTO1).isNotEqualTo(customerWishlistDTO2);
        customerWishlistDTO2.setId(customerWishlistDTO1.getId());
        assertThat(customerWishlistDTO1).isEqualTo(customerWishlistDTO2);
        customerWishlistDTO2.setId(2L);
        assertThat(customerWishlistDTO1).isNotEqualTo(customerWishlistDTO2);
        customerWishlistDTO1.setId(null);
        assertThat(customerWishlistDTO1).isNotEqualTo(customerWishlistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerWishlistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerWishlistMapper.fromId(null)).isNull();
    }
}
