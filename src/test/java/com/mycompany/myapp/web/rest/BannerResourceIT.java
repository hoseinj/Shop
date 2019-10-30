package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Banner;
import com.mycompany.myapp.repository.BannerRepository;
import com.mycompany.myapp.service.BannerService;
import com.mycompany.myapp.service.dto.BannerDTO;
import com.mycompany.myapp.service.mapper.BannerMapper;
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
 * Integration tests for the {@link BannerResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class BannerResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VIEW_PAGE_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_VIEW_PAGE_COUNT = "BBBBBBBBBB";

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
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerService bannerService;

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

    private MockMvc restBannerMockMvc;

    private Banner banner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BannerResource bannerResource = new BannerResource(bannerService);
        this.restBannerMockMvc = MockMvcBuilders.standaloneSetup(bannerResource)
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
    public static Banner createEntity(EntityManager em) {
        Banner banner = new Banner()
            .title(DEFAULT_TITLE)
            .url(DEFAULT_URL)
            .link(DEFAULT_LINK)
            .content(DEFAULT_CONTENT)
            .position(DEFAULT_POSITION)
            .image(DEFAULT_IMAGE)
            .imagePath(DEFAULT_IMAGE_PATH)
            .containerName(DEFAULT_CONTAINER_NAME)
            .viewPageCount(DEFAULT_VIEW_PAGE_COUNT)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return banner;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banner createUpdatedEntity(EntityManager em) {
        Banner banner = new Banner()
            .title(UPDATED_TITLE)
            .url(UPDATED_URL)
            .link(UPDATED_LINK)
            .content(UPDATED_CONTENT)
            .position(UPDATED_POSITION)
            .image(UPDATED_IMAGE)
            .imagePath(UPDATED_IMAGE_PATH)
            .containerName(UPDATED_CONTAINER_NAME)
            .viewPageCount(UPDATED_VIEW_PAGE_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return banner;
    }

    @BeforeEach
    public void initTest() {
        banner = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanner() throws Exception {
        int databaseSizeBeforeCreate = bannerRepository.findAll().size();

        // Create the Banner
        BannerDTO bannerDTO = bannerMapper.toDto(banner);
        restBannerMockMvc.perform(post("/api/banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerDTO)))
            .andExpect(status().isCreated());

        // Validate the Banner in the database
        List<Banner> bannerList = bannerRepository.findAll();
        assertThat(bannerList).hasSize(databaseSizeBeforeCreate + 1);
        Banner testBanner = bannerList.get(bannerList.size() - 1);
        assertThat(testBanner.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBanner.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testBanner.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testBanner.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testBanner.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testBanner.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testBanner.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testBanner.getContainerName()).isEqualTo(DEFAULT_CONTAINER_NAME);
        assertThat(testBanner.getViewPageCount()).isEqualTo(DEFAULT_VIEW_PAGE_COUNT);
        assertThat(testBanner.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testBanner.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBanner.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testBanner.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBanner.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBannerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bannerRepository.findAll().size();

        // Create the Banner with an existing ID
        banner.setId(1L);
        BannerDTO bannerDTO = bannerMapper.toDto(banner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBannerMockMvc.perform(post("/api/banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Banner in the database
        List<Banner> bannerList = bannerRepository.findAll();
        assertThat(bannerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBanners() throws Exception {
        // Initialize the database
        bannerRepository.saveAndFlush(banner);

        // Get all the bannerList
        restBannerMockMvc.perform(get("/api/banners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banner.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].containerName").value(hasItem(DEFAULT_CONTAINER_NAME)))
            .andExpect(jsonPath("$.[*].viewPageCount").value(hasItem(DEFAULT_VIEW_PAGE_COUNT)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getBanner() throws Exception {
        // Initialize the database
        bannerRepository.saveAndFlush(banner);

        // Get the banner
        restBannerMockMvc.perform(get("/api/banners/{id}", banner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(banner.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH))
            .andExpect(jsonPath("$.containerName").value(DEFAULT_CONTAINER_NAME))
            .andExpect(jsonPath("$.viewPageCount").value(DEFAULT_VIEW_PAGE_COUNT))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingBanner() throws Exception {
        // Get the banner
        restBannerMockMvc.perform(get("/api/banners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanner() throws Exception {
        // Initialize the database
        bannerRepository.saveAndFlush(banner);

        int databaseSizeBeforeUpdate = bannerRepository.findAll().size();

        // Update the banner
        Banner updatedBanner = bannerRepository.findById(banner.getId()).get();
        // Disconnect from session so that the updates on updatedBanner are not directly saved in db
        em.detach(updatedBanner);
        updatedBanner
            .title(UPDATED_TITLE)
            .url(UPDATED_URL)
            .link(UPDATED_LINK)
            .content(UPDATED_CONTENT)
            .position(UPDATED_POSITION)
            .image(UPDATED_IMAGE)
            .imagePath(UPDATED_IMAGE_PATH)
            .containerName(UPDATED_CONTAINER_NAME)
            .viewPageCount(UPDATED_VIEW_PAGE_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        BannerDTO bannerDTO = bannerMapper.toDto(updatedBanner);

        restBannerMockMvc.perform(put("/api/banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerDTO)))
            .andExpect(status().isOk());

        // Validate the Banner in the database
        List<Banner> bannerList = bannerRepository.findAll();
        assertThat(bannerList).hasSize(databaseSizeBeforeUpdate);
        Banner testBanner = bannerList.get(bannerList.size() - 1);
        assertThat(testBanner.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBanner.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testBanner.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testBanner.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testBanner.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testBanner.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testBanner.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testBanner.getContainerName()).isEqualTo(UPDATED_CONTAINER_NAME);
        assertThat(testBanner.getViewPageCount()).isEqualTo(UPDATED_VIEW_PAGE_COUNT);
        assertThat(testBanner.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testBanner.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBanner.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBanner.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBanner.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBanner() throws Exception {
        int databaseSizeBeforeUpdate = bannerRepository.findAll().size();

        // Create the Banner
        BannerDTO bannerDTO = bannerMapper.toDto(banner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBannerMockMvc.perform(put("/api/banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Banner in the database
        List<Banner> bannerList = bannerRepository.findAll();
        assertThat(bannerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBanner() throws Exception {
        // Initialize the database
        bannerRepository.saveAndFlush(banner);

        int databaseSizeBeforeDelete = bannerRepository.findAll().size();

        // Delete the banner
        restBannerMockMvc.perform(delete("/api/banners/{id}", banner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Banner> bannerList = bannerRepository.findAll();
        assertThat(bannerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Banner.class);
        Banner banner1 = new Banner();
        banner1.setId(1L);
        Banner banner2 = new Banner();
        banner2.setId(banner1.getId());
        assertThat(banner1).isEqualTo(banner2);
        banner2.setId(2L);
        assertThat(banner1).isNotEqualTo(banner2);
        banner1.setId(null);
        assertThat(banner1).isNotEqualTo(banner2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BannerDTO.class);
        BannerDTO bannerDTO1 = new BannerDTO();
        bannerDTO1.setId(1L);
        BannerDTO bannerDTO2 = new BannerDTO();
        assertThat(bannerDTO1).isNotEqualTo(bannerDTO2);
        bannerDTO2.setId(bannerDTO1.getId());
        assertThat(bannerDTO1).isEqualTo(bannerDTO2);
        bannerDTO2.setId(2L);
        assertThat(bannerDTO1).isNotEqualTo(bannerDTO2);
        bannerDTO1.setId(null);
        assertThat(bannerDTO1).isNotEqualTo(bannerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bannerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bannerMapper.fromId(null)).isNull();
    }
}
