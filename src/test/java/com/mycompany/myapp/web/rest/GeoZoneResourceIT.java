package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.GeoZone;
import com.mycompany.myapp.repository.GeoZoneRepository;
import com.mycompany.myapp.service.GeoZoneService;
import com.mycompany.myapp.service.dto.GeoZoneDTO;
import com.mycompany.myapp.service.mapper.GeoZoneMapper;
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
 * Integration tests for the {@link GeoZoneResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class GeoZoneResourceIT {

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
    private GeoZoneRepository geoZoneRepository;

    @Autowired
    private GeoZoneMapper geoZoneMapper;

    @Autowired
    private GeoZoneService geoZoneService;

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

    private MockMvc restGeoZoneMockMvc;

    private GeoZone geoZone;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeoZoneResource geoZoneResource = new GeoZoneResource(geoZoneService);
        this.restGeoZoneMockMvc = MockMvcBuilders.standaloneSetup(geoZoneResource)
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
    public static GeoZone createEntity(EntityManager em) {
        GeoZone geoZone = new GeoZone()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return geoZone;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoZone createUpdatedEntity(EntityManager em) {
        GeoZone geoZone = new GeoZone()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return geoZone;
    }

    @BeforeEach
    public void initTest() {
        geoZone = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoZone() throws Exception {
        int databaseSizeBeforeCreate = geoZoneRepository.findAll().size();

        // Create the GeoZone
        GeoZoneDTO geoZoneDTO = geoZoneMapper.toDto(geoZone);
        restGeoZoneMockMvc.perform(post("/api/geo-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoZoneDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoZone in the database
        List<GeoZone> geoZoneList = geoZoneRepository.findAll();
        assertThat(geoZoneList).hasSize(databaseSizeBeforeCreate + 1);
        GeoZone testGeoZone = geoZoneList.get(geoZoneList.size() - 1);
        assertThat(testGeoZone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGeoZone.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGeoZone.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testGeoZone.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGeoZone.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGeoZone.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGeoZone.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createGeoZoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoZoneRepository.findAll().size();

        // Create the GeoZone with an existing ID
        geoZone.setId(1L);
        GeoZoneDTO geoZoneDTO = geoZoneMapper.toDto(geoZone);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoZoneMockMvc.perform(post("/api/geo-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoZoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoZone in the database
        List<GeoZone> geoZoneList = geoZoneRepository.findAll();
        assertThat(geoZoneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoZones() throws Exception {
        // Initialize the database
        geoZoneRepository.saveAndFlush(geoZone);

        // Get all the geoZoneList
        restGeoZoneMockMvc.perform(get("/api/geo-zones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoZone.getId().intValue())))
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
    public void getGeoZone() throws Exception {
        // Initialize the database
        geoZoneRepository.saveAndFlush(geoZone);

        // Get the geoZone
        restGeoZoneMockMvc.perform(get("/api/geo-zones/{id}", geoZone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(geoZone.getId().intValue()))
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
    public void getNonExistingGeoZone() throws Exception {
        // Get the geoZone
        restGeoZoneMockMvc.perform(get("/api/geo-zones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoZone() throws Exception {
        // Initialize the database
        geoZoneRepository.saveAndFlush(geoZone);

        int databaseSizeBeforeUpdate = geoZoneRepository.findAll().size();

        // Update the geoZone
        GeoZone updatedGeoZone = geoZoneRepository.findById(geoZone.getId()).get();
        // Disconnect from session so that the updates on updatedGeoZone are not directly saved in db
        em.detach(updatedGeoZone);
        updatedGeoZone
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        GeoZoneDTO geoZoneDTO = geoZoneMapper.toDto(updatedGeoZone);

        restGeoZoneMockMvc.perform(put("/api/geo-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoZoneDTO)))
            .andExpect(status().isOk());

        // Validate the GeoZone in the database
        List<GeoZone> geoZoneList = geoZoneRepository.findAll();
        assertThat(geoZoneList).hasSize(databaseSizeBeforeUpdate);
        GeoZone testGeoZone = geoZoneList.get(geoZoneList.size() - 1);
        assertThat(testGeoZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGeoZone.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGeoZone.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testGeoZone.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGeoZone.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGeoZone.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGeoZone.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoZone() throws Exception {
        int databaseSizeBeforeUpdate = geoZoneRepository.findAll().size();

        // Create the GeoZone
        GeoZoneDTO geoZoneDTO = geoZoneMapper.toDto(geoZone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoZoneMockMvc.perform(put("/api/geo-zones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoZoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoZone in the database
        List<GeoZone> geoZoneList = geoZoneRepository.findAll();
        assertThat(geoZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoZone() throws Exception {
        // Initialize the database
        geoZoneRepository.saveAndFlush(geoZone);

        int databaseSizeBeforeDelete = geoZoneRepository.findAll().size();

        // Delete the geoZone
        restGeoZoneMockMvc.perform(delete("/api/geo-zones/{id}", geoZone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoZone> geoZoneList = geoZoneRepository.findAll();
        assertThat(geoZoneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoZone.class);
        GeoZone geoZone1 = new GeoZone();
        geoZone1.setId(1L);
        GeoZone geoZone2 = new GeoZone();
        geoZone2.setId(geoZone1.getId());
        assertThat(geoZone1).isEqualTo(geoZone2);
        geoZone2.setId(2L);
        assertThat(geoZone1).isNotEqualTo(geoZone2);
        geoZone1.setId(null);
        assertThat(geoZone1).isNotEqualTo(geoZone2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoZoneDTO.class);
        GeoZoneDTO geoZoneDTO1 = new GeoZoneDTO();
        geoZoneDTO1.setId(1L);
        GeoZoneDTO geoZoneDTO2 = new GeoZoneDTO();
        assertThat(geoZoneDTO1).isNotEqualTo(geoZoneDTO2);
        geoZoneDTO2.setId(geoZoneDTO1.getId());
        assertThat(geoZoneDTO1).isEqualTo(geoZoneDTO2);
        geoZoneDTO2.setId(2L);
        assertThat(geoZoneDTO1).isNotEqualTo(geoZoneDTO2);
        geoZoneDTO1.setId(null);
        assertThat(geoZoneDTO1).isNotEqualTo(geoZoneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(geoZoneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(geoZoneMapper.fromId(null)).isNull();
    }
}
