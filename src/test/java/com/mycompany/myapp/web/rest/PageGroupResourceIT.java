package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.PageGroup;
import com.mycompany.myapp.repository.PageGroupRepository;
import com.mycompany.myapp.service.PageGroupService;
import com.mycompany.myapp.service.dto.PageGroupDTO;
import com.mycompany.myapp.service.mapper.PageGroupMapper;
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
 * Integration tests for the {@link PageGroupResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class PageGroupResourceIT {

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
    private PageGroupRepository pageGroupRepository;

    @Autowired
    private PageGroupMapper pageGroupMapper;

    @Autowired
    private PageGroupService pageGroupService;

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

    private MockMvc restPageGroupMockMvc;

    private PageGroup pageGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PageGroupResource pageGroupResource = new PageGroupResource(pageGroupService);
        this.restPageGroupMockMvc = MockMvcBuilders.standaloneSetup(pageGroupResource)
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
    public static PageGroup createEntity(EntityManager em) {
        PageGroup pageGroup = new PageGroup()
            .title(DEFAULT_TITLE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return pageGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PageGroup createUpdatedEntity(EntityManager em) {
        PageGroup pageGroup = new PageGroup()
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return pageGroup;
    }

    @BeforeEach
    public void initTest() {
        pageGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createPageGroup() throws Exception {
        int databaseSizeBeforeCreate = pageGroupRepository.findAll().size();

        // Create the PageGroup
        PageGroupDTO pageGroupDTO = pageGroupMapper.toDto(pageGroup);
        restPageGroupMockMvc.perform(post("/api/page-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the PageGroup in the database
        List<PageGroup> pageGroupList = pageGroupRepository.findAll();
        assertThat(pageGroupList).hasSize(databaseSizeBeforeCreate + 1);
        PageGroup testPageGroup = pageGroupList.get(pageGroupList.size() - 1);
        assertThat(testPageGroup.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPageGroup.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testPageGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPageGroup.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testPageGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPageGroup.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createPageGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pageGroupRepository.findAll().size();

        // Create the PageGroup with an existing ID
        pageGroup.setId(1L);
        PageGroupDTO pageGroupDTO = pageGroupMapper.toDto(pageGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPageGroupMockMvc.perform(post("/api/page-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PageGroup in the database
        List<PageGroup> pageGroupList = pageGroupRepository.findAll();
        assertThat(pageGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPageGroups() throws Exception {
        // Initialize the database
        pageGroupRepository.saveAndFlush(pageGroup);

        // Get all the pageGroupList
        restPageGroupMockMvc.perform(get("/api/page-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pageGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getPageGroup() throws Exception {
        // Initialize the database
        pageGroupRepository.saveAndFlush(pageGroup);

        // Get the pageGroup
        restPageGroupMockMvc.perform(get("/api/page-groups/{id}", pageGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pageGroup.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingPageGroup() throws Exception {
        // Get the pageGroup
        restPageGroupMockMvc.perform(get("/api/page-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePageGroup() throws Exception {
        // Initialize the database
        pageGroupRepository.saveAndFlush(pageGroup);

        int databaseSizeBeforeUpdate = pageGroupRepository.findAll().size();

        // Update the pageGroup
        PageGroup updatedPageGroup = pageGroupRepository.findById(pageGroup.getId()).get();
        // Disconnect from session so that the updates on updatedPageGroup are not directly saved in db
        em.detach(updatedPageGroup);
        updatedPageGroup
            .title(UPDATED_TITLE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        PageGroupDTO pageGroupDTO = pageGroupMapper.toDto(updatedPageGroup);

        restPageGroupMockMvc.perform(put("/api/page-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageGroupDTO)))
            .andExpect(status().isOk());

        // Validate the PageGroup in the database
        List<PageGroup> pageGroupList = pageGroupRepository.findAll();
        assertThat(pageGroupList).hasSize(databaseSizeBeforeUpdate);
        PageGroup testPageGroup = pageGroupList.get(pageGroupList.size() - 1);
        assertThat(testPageGroup.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPageGroup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPageGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPageGroup.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testPageGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPageGroup.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPageGroup() throws Exception {
        int databaseSizeBeforeUpdate = pageGroupRepository.findAll().size();

        // Create the PageGroup
        PageGroupDTO pageGroupDTO = pageGroupMapper.toDto(pageGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPageGroupMockMvc.perform(put("/api/page-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pageGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PageGroup in the database
        List<PageGroup> pageGroupList = pageGroupRepository.findAll();
        assertThat(pageGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePageGroup() throws Exception {
        // Initialize the database
        pageGroupRepository.saveAndFlush(pageGroup);

        int databaseSizeBeforeDelete = pageGroupRepository.findAll().size();

        // Delete the pageGroup
        restPageGroupMockMvc.perform(delete("/api/page-groups/{id}", pageGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PageGroup> pageGroupList = pageGroupRepository.findAll();
        assertThat(pageGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PageGroup.class);
        PageGroup pageGroup1 = new PageGroup();
        pageGroup1.setId(1L);
        PageGroup pageGroup2 = new PageGroup();
        pageGroup2.setId(pageGroup1.getId());
        assertThat(pageGroup1).isEqualTo(pageGroup2);
        pageGroup2.setId(2L);
        assertThat(pageGroup1).isNotEqualTo(pageGroup2);
        pageGroup1.setId(null);
        assertThat(pageGroup1).isNotEqualTo(pageGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PageGroupDTO.class);
        PageGroupDTO pageGroupDTO1 = new PageGroupDTO();
        pageGroupDTO1.setId(1L);
        PageGroupDTO pageGroupDTO2 = new PageGroupDTO();
        assertThat(pageGroupDTO1).isNotEqualTo(pageGroupDTO2);
        pageGroupDTO2.setId(pageGroupDTO1.getId());
        assertThat(pageGroupDTO1).isEqualTo(pageGroupDTO2);
        pageGroupDTO2.setId(2L);
        assertThat(pageGroupDTO1).isNotEqualTo(pageGroupDTO2);
        pageGroupDTO1.setId(null);
        assertThat(pageGroupDTO1).isNotEqualTo(pageGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pageGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pageGroupMapper.fromId(null)).isNull();
    }
}
