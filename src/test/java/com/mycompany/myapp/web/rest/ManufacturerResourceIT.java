package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Manufacturer;
import com.mycompany.myapp.repository.ManufacturerRepository;
import com.mycompany.myapp.service.ManufacturerService;
import com.mycompany.myapp.service.dto.ManufacturerDTO;
import com.mycompany.myapp.service.mapper.ManufacturerMapper;
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
 * Integration tests for the {@link ManufacturerResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ManufacturerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

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
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Autowired
    private ManufacturerService manufacturerService;

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

    private MockMvc restManufacturerMockMvc;

    private Manufacturer manufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ManufacturerResource manufacturerResource = new ManufacturerResource(manufacturerService);
        this.restManufacturerMockMvc = MockMvcBuilders.standaloneSetup(manufacturerResource)
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
    public static Manufacturer createEntity(EntityManager em) {
        Manufacturer manufacturer = new Manufacturer()
            .name(DEFAULT_NAME)
            .image(DEFAULT_IMAGE)
            .imagePath(DEFAULT_IMAGE_PATH)
            .sortOrder(DEFAULT_SORT_ORDER)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return manufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manufacturer createUpdatedEntity(EntityManager em) {
        Manufacturer manufacturer = new Manufacturer()
            .name(UPDATED_NAME)
            .image(UPDATED_IMAGE)
            .imagePath(UPDATED_IMAGE_PATH)
            .sortOrder(UPDATED_SORT_ORDER)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return manufacturer;
    }

    @BeforeEach
    public void initTest() {
        manufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createManufacturer() throws Exception {
        int databaseSizeBeforeCreate = manufacturerRepository.findAll().size();

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);
        restManufacturerMockMvc.perform(post("/api/manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testManufacturer.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testManufacturer.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testManufacturer.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testManufacturer.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testManufacturer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testManufacturer.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testManufacturer.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufacturerRepository.findAll().size();

        // Create the Manufacturer with an existing ID
        manufacturer.setId(1L);
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturerMockMvc.perform(post("/api/manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManufacturers() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get all the manufacturerList
        restManufacturerMockMvc.perform(get("/api/manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufacturer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        // Get the manufacturer
        restManufacturerMockMvc.perform(get("/api/manufacturers/{id}", manufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manufacturer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingManufacturer() throws Exception {
        // Get the manufacturer
        restManufacturerMockMvc.perform(get("/api/manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();

        // Update the manufacturer
        Manufacturer updatedManufacturer = manufacturerRepository.findById(manufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedManufacturer are not directly saved in db
        em.detach(updatedManufacturer);
        updatedManufacturer
            .name(UPDATED_NAME)
            .image(UPDATED_IMAGE)
            .imagePath(UPDATED_IMAGE_PATH)
            .sortOrder(UPDATED_SORT_ORDER)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(updatedManufacturer);

        restManufacturerMockMvc.perform(put("/api/manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
        Manufacturer testManufacturer = manufacturerList.get(manufacturerList.size() - 1);
        assertThat(testManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testManufacturer.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testManufacturer.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testManufacturer.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testManufacturer.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testManufacturer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testManufacturer.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testManufacturer.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testManufacturer.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = manufacturerRepository.findAll().size();

        // Create the Manufacturer
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDto(manufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufacturerMockMvc.perform(put("/api/manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Manufacturer in the database
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManufacturer() throws Exception {
        // Initialize the database
        manufacturerRepository.saveAndFlush(manufacturer);

        int databaseSizeBeforeDelete = manufacturerRepository.findAll().size();

        // Delete the manufacturer
        restManufacturerMockMvc.perform(delete("/api/manufacturers/{id}", manufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        assertThat(manufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturer.class);
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setId(1L);
        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setId(manufacturer1.getId());
        assertThat(manufacturer1).isEqualTo(manufacturer2);
        manufacturer2.setId(2L);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
        manufacturer1.setId(null);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufacturerDTO.class);
        ManufacturerDTO manufacturerDTO1 = new ManufacturerDTO();
        manufacturerDTO1.setId(1L);
        ManufacturerDTO manufacturerDTO2 = new ManufacturerDTO();
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
        manufacturerDTO2.setId(manufacturerDTO1.getId());
        assertThat(manufacturerDTO1).isEqualTo(manufacturerDTO2);
        manufacturerDTO2.setId(2L);
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
        manufacturerDTO1.setId(null);
        assertThat(manufacturerDTO1).isNotEqualTo(manufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(manufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(manufacturerMapper.fromId(null)).isNull();
    }
}
