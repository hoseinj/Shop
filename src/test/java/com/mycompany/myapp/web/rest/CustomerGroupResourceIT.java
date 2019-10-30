package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.CustomerGroup;
import com.mycompany.myapp.repository.CustomerGroupRepository;
import com.mycompany.myapp.service.CustomerGroupService;
import com.mycompany.myapp.service.dto.CustomerGroupDTO;
import com.mycompany.myapp.service.mapper.CustomerGroupMapper;
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
 * Integration tests for the {@link CustomerGroupResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class CustomerGroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Autowired
    private CustomerGroupService customerGroupService;

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

    private MockMvc restCustomerGroupMockMvc;

    private CustomerGroup customerGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerGroupResource customerGroupResource = new CustomerGroupResource(customerGroupService);
        this.restCustomerGroupMockMvc = MockMvcBuilders.standaloneSetup(customerGroupResource)
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
    public static CustomerGroup createEntity(EntityManager em) {
        CustomerGroup customerGroup = new CustomerGroup()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return customerGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomerGroup createUpdatedEntity(EntityManager em) {
        CustomerGroup customerGroup = new CustomerGroup()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return customerGroup;
    }

    @BeforeEach
    public void initTest() {
        customerGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerGroup() throws Exception {
        int databaseSizeBeforeCreate = customerGroupRepository.findAll().size();

        // Create the CustomerGroup
        CustomerGroupDTO customerGroupDTO = customerGroupMapper.toDto(customerGroup);
        restCustomerGroupMockMvc.perform(post("/api/customer-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerGroup in the database
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
        assertThat(customerGroupList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerGroup testCustomerGroup = customerGroupList.get(customerGroupList.size() - 1);
        assertThat(testCustomerGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomerGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomerGroup.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCustomerGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomerGroup.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testCustomerGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCustomerGroup.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createCustomerGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerGroupRepository.findAll().size();

        // Create the CustomerGroup with an existing ID
        customerGroup.setId(1L);
        CustomerGroupDTO customerGroupDTO = customerGroupMapper.toDto(customerGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerGroupMockMvc.perform(post("/api/customer-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerGroup in the database
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
        assertThat(customerGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomerGroups() throws Exception {
        // Initialize the database
        customerGroupRepository.saveAndFlush(customerGroup);

        // Get all the customerGroupList
        restCustomerGroupMockMvc.perform(get("/api/customer-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getCustomerGroup() throws Exception {
        // Initialize the database
        customerGroupRepository.saveAndFlush(customerGroup);

        // Get the customerGroup
        restCustomerGroupMockMvc.perform(get("/api/customer-groups/{id}", customerGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerGroup() throws Exception {
        // Get the customerGroup
        restCustomerGroupMockMvc.perform(get("/api/customer-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerGroup() throws Exception {
        // Initialize the database
        customerGroupRepository.saveAndFlush(customerGroup);

        int databaseSizeBeforeUpdate = customerGroupRepository.findAll().size();

        // Update the customerGroup
        CustomerGroup updatedCustomerGroup = customerGroupRepository.findById(customerGroup.getId()).get();
        // Disconnect from session so that the updates on updatedCustomerGroup are not directly saved in db
        em.detach(updatedCustomerGroup);
        updatedCustomerGroup
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        CustomerGroupDTO customerGroupDTO = customerGroupMapper.toDto(updatedCustomerGroup);

        restCustomerGroupMockMvc.perform(put("/api/customer-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerGroupDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerGroup in the database
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
        assertThat(customerGroupList).hasSize(databaseSizeBeforeUpdate);
        CustomerGroup testCustomerGroup = customerGroupList.get(customerGroupList.size() - 1);
        assertThat(testCustomerGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomerGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomerGroup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCustomerGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomerGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testCustomerGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCustomerGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerGroup() throws Exception {
        int databaseSizeBeforeUpdate = customerGroupRepository.findAll().size();

        // Create the CustomerGroup
        CustomerGroupDTO customerGroupDTO = customerGroupMapper.toDto(customerGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerGroupMockMvc.perform(put("/api/customer-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerGroup in the database
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
        assertThat(customerGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomerGroup() throws Exception {
        // Initialize the database
        customerGroupRepository.saveAndFlush(customerGroup);

        int databaseSizeBeforeDelete = customerGroupRepository.findAll().size();

        // Delete the customerGroup
        restCustomerGroupMockMvc.perform(delete("/api/customer-groups/{id}", customerGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
        assertThat(customerGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerGroup.class);
        CustomerGroup customerGroup1 = new CustomerGroup();
        customerGroup1.setId(1L);
        CustomerGroup customerGroup2 = new CustomerGroup();
        customerGroup2.setId(customerGroup1.getId());
        assertThat(customerGroup1).isEqualTo(customerGroup2);
        customerGroup2.setId(2L);
        assertThat(customerGroup1).isNotEqualTo(customerGroup2);
        customerGroup1.setId(null);
        assertThat(customerGroup1).isNotEqualTo(customerGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerGroupDTO.class);
        CustomerGroupDTO customerGroupDTO1 = new CustomerGroupDTO();
        customerGroupDTO1.setId(1L);
        CustomerGroupDTO customerGroupDTO2 = new CustomerGroupDTO();
        assertThat(customerGroupDTO1).isNotEqualTo(customerGroupDTO2);
        customerGroupDTO2.setId(customerGroupDTO1.getId());
        assertThat(customerGroupDTO1).isEqualTo(customerGroupDTO2);
        customerGroupDTO2.setId(2L);
        assertThat(customerGroupDTO1).isNotEqualTo(customerGroupDTO2);
        customerGroupDTO1.setId(null);
        assertThat(customerGroupDTO1).isNotEqualTo(customerGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerGroupMapper.fromId(null)).isNull();
    }
}
