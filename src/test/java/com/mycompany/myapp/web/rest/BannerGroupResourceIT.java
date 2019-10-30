package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.BannerGroup;
import com.mycompany.myapp.repository.BannerGroupRepository;
import com.mycompany.myapp.service.BannerGroupService;
import com.mycompany.myapp.service.dto.BannerGroupDTO;
import com.mycompany.myapp.service.mapper.BannerGroupMapper;
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
 * Integration tests for the {@link BannerGroupResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class BannerGroupResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

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
    private BannerGroupRepository bannerGroupRepository;

    @Autowired
    private BannerGroupMapper bannerGroupMapper;

    @Autowired
    private BannerGroupService bannerGroupService;

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

    private MockMvc restBannerGroupMockMvc;

    private BannerGroup bannerGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BannerGroupResource bannerGroupResource = new BannerGroupResource(bannerGroupService);
        this.restBannerGroupMockMvc = MockMvcBuilders.standaloneSetup(bannerGroupResource)
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
    public static BannerGroup createEntity(EntityManager em) {
        BannerGroup bannerGroup = new BannerGroup()
            .title(DEFAULT_TITLE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return bannerGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BannerGroup createUpdatedEntity(EntityManager em) {
        BannerGroup bannerGroup = new BannerGroup()
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return bannerGroup;
    }

    @BeforeEach
    public void initTest() {
        bannerGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createBannerGroup() throws Exception {
        int databaseSizeBeforeCreate = bannerGroupRepository.findAll().size();

        // Create the BannerGroup
        BannerGroupDTO bannerGroupDTO = bannerGroupMapper.toDto(bannerGroup);
        restBannerGroupMockMvc.perform(post("/api/banner-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the BannerGroup in the database
        List<BannerGroup> bannerGroupList = bannerGroupRepository.findAll();
        assertThat(bannerGroupList).hasSize(databaseSizeBeforeCreate + 1);
        BannerGroup testBannerGroup = bannerGroupList.get(bannerGroupList.size() - 1);
        assertThat(testBannerGroup.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBannerGroup.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testBannerGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBannerGroup.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testBannerGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBannerGroup.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBannerGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bannerGroupRepository.findAll().size();

        // Create the BannerGroup with an existing ID
        bannerGroup.setId(1L);
        BannerGroupDTO bannerGroupDTO = bannerGroupMapper.toDto(bannerGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBannerGroupMockMvc.perform(post("/api/banner-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BannerGroup in the database
        List<BannerGroup> bannerGroupList = bannerGroupRepository.findAll();
        assertThat(bannerGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBannerGroups() throws Exception {
        // Initialize the database
        bannerGroupRepository.saveAndFlush(bannerGroup);

        // Get all the bannerGroupList
        restBannerGroupMockMvc.perform(get("/api/banner-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bannerGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getBannerGroup() throws Exception {
        // Initialize the database
        bannerGroupRepository.saveAndFlush(bannerGroup);

        // Get the bannerGroup
        restBannerGroupMockMvc.perform(get("/api/banner-groups/{id}", bannerGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bannerGroup.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingBannerGroup() throws Exception {
        // Get the bannerGroup
        restBannerGroupMockMvc.perform(get("/api/banner-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBannerGroup() throws Exception {
        // Initialize the database
        bannerGroupRepository.saveAndFlush(bannerGroup);

        int databaseSizeBeforeUpdate = bannerGroupRepository.findAll().size();

        // Update the bannerGroup
        BannerGroup updatedBannerGroup = bannerGroupRepository.findById(bannerGroup.getId()).get();
        // Disconnect from session so that the updates on updatedBannerGroup are not directly saved in db
        em.detach(updatedBannerGroup);
        updatedBannerGroup
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        BannerGroupDTO bannerGroupDTO = bannerGroupMapper.toDto(updatedBannerGroup);

        restBannerGroupMockMvc.perform(put("/api/banner-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerGroupDTO)))
            .andExpect(status().isOk());

        // Validate the BannerGroup in the database
        List<BannerGroup> bannerGroupList = bannerGroupRepository.findAll();
        assertThat(bannerGroupList).hasSize(databaseSizeBeforeUpdate);
        BannerGroup testBannerGroup = bannerGroupList.get(bannerGroupList.size() - 1);
        assertThat(testBannerGroup.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBannerGroup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testBannerGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBannerGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBannerGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBannerGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBannerGroup() throws Exception {
        int databaseSizeBeforeUpdate = bannerGroupRepository.findAll().size();

        // Create the BannerGroup
        BannerGroupDTO bannerGroupDTO = bannerGroupMapper.toDto(bannerGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBannerGroupMockMvc.perform(put("/api/banner-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BannerGroup in the database
        List<BannerGroup> bannerGroupList = bannerGroupRepository.findAll();
        assertThat(bannerGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBannerGroup() throws Exception {
        // Initialize the database
        bannerGroupRepository.saveAndFlush(bannerGroup);

        int databaseSizeBeforeDelete = bannerGroupRepository.findAll().size();

        // Delete the bannerGroup
        restBannerGroupMockMvc.perform(delete("/api/banner-groups/{id}", bannerGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BannerGroup> bannerGroupList = bannerGroupRepository.findAll();
        assertThat(bannerGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BannerGroup.class);
        BannerGroup bannerGroup1 = new BannerGroup();
        bannerGroup1.setId(1L);
        BannerGroup bannerGroup2 = new BannerGroup();
        bannerGroup2.setId(bannerGroup1.getId());
        assertThat(bannerGroup1).isEqualTo(bannerGroup2);
        bannerGroup2.setId(2L);
        assertThat(bannerGroup1).isNotEqualTo(bannerGroup2);
        bannerGroup1.setId(null);
        assertThat(bannerGroup1).isNotEqualTo(bannerGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BannerGroupDTO.class);
        BannerGroupDTO bannerGroupDTO1 = new BannerGroupDTO();
        bannerGroupDTO1.setId(1L);
        BannerGroupDTO bannerGroupDTO2 = new BannerGroupDTO();
        assertThat(bannerGroupDTO1).isNotEqualTo(bannerGroupDTO2);
        bannerGroupDTO2.setId(bannerGroupDTO1.getId());
        assertThat(bannerGroupDTO1).isEqualTo(bannerGroupDTO2);
        bannerGroupDTO2.setId(2L);
        assertThat(bannerGroupDTO1).isNotEqualTo(bannerGroupDTO2);
        bannerGroupDTO1.setId(null);
        assertThat(bannerGroupDTO1).isNotEqualTo(bannerGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bannerGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bannerGroupMapper.fromId(null)).isNull();
    }
}
