package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ShopPage;
import com.mycompany.myapp.repository.ShopPageRepository;
import com.mycompany.myapp.service.ShopPageService;
import com.mycompany.myapp.service.dto.ShopPageDTO;
import com.mycompany.myapp.service.mapper.ShopPageMapper;
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
 * Integration tests for the {@link ShopPageResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ShopPageResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_INTRO = "AAAAAAAAAA";
    private static final String UPDATED_INTRO = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FULL_TEXT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

    private static final String DEFAULT_META_TAG_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_META_TAG_DESCROPTION = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_DESCROPTION = "BBBBBBBBBB";

    private static final String DEFAULT_META_TAG_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_KEYWORDS = "BBBBBBBBBB";

    private static final Integer DEFAULT_VIEW_PAGE_COUNT = 1;
    private static final Integer UPDATED_VIEW_PAGE_COUNT = 2;

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
    private ShopPageRepository shopPageRepository;

    @Autowired
    private ShopPageMapper shopPageMapper;

    @Autowired
    private ShopPageService shopPageService;

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

    private MockMvc restShopPageMockMvc;

    private ShopPage shopPage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShopPageResource shopPageResource = new ShopPageResource(shopPageService);
        this.restShopPageMockMvc = MockMvcBuilders.standaloneSetup(shopPageResource)
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
    public static ShopPage createEntity(EntityManager em) {
        ShopPage shopPage = new ShopPage()
            .title(DEFAULT_TITLE)
            .intro(DEFAULT_INTRO)
            .fullText(DEFAULT_FULL_TEXT)
            .sortOrder(DEFAULT_SORT_ORDER)
            .metaTagTitle(DEFAULT_META_TAG_TITLE)
            .metaTagDescroption(DEFAULT_META_TAG_DESCROPTION)
            .metaTagKeywords(DEFAULT_META_TAG_KEYWORDS)
            .viewPageCount(DEFAULT_VIEW_PAGE_COUNT)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return shopPage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopPage createUpdatedEntity(EntityManager em) {
        ShopPage shopPage = new ShopPage()
            .title(UPDATED_TITLE)
            .intro(UPDATED_INTRO)
            .fullText(UPDATED_FULL_TEXT)
            .sortOrder(UPDATED_SORT_ORDER)
            .metaTagTitle(UPDATED_META_TAG_TITLE)
            .metaTagDescroption(UPDATED_META_TAG_DESCROPTION)
            .metaTagKeywords(UPDATED_META_TAG_KEYWORDS)
            .viewPageCount(UPDATED_VIEW_PAGE_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return shopPage;
    }

    @BeforeEach
    public void initTest() {
        shopPage = createEntity(em);
    }

    @Test
    @Transactional
    public void createShopPage() throws Exception {
        int databaseSizeBeforeCreate = shopPageRepository.findAll().size();

        // Create the ShopPage
        ShopPageDTO shopPageDTO = shopPageMapper.toDto(shopPage);
        restShopPageMockMvc.perform(post("/api/shop-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopPageDTO)))
            .andExpect(status().isCreated());

        // Validate the ShopPage in the database
        List<ShopPage> shopPageList = shopPageRepository.findAll();
        assertThat(shopPageList).hasSize(databaseSizeBeforeCreate + 1);
        ShopPage testShopPage = shopPageList.get(shopPageList.size() - 1);
        assertThat(testShopPage.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testShopPage.getIntro()).isEqualTo(DEFAULT_INTRO);
        assertThat(testShopPage.getFullText()).isEqualTo(DEFAULT_FULL_TEXT);
        assertThat(testShopPage.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testShopPage.getMetaTagTitle()).isEqualTo(DEFAULT_META_TAG_TITLE);
        assertThat(testShopPage.getMetaTagDescroption()).isEqualTo(DEFAULT_META_TAG_DESCROPTION);
        assertThat(testShopPage.getMetaTagKeywords()).isEqualTo(DEFAULT_META_TAG_KEYWORDS);
        assertThat(testShopPage.getViewPageCount()).isEqualTo(DEFAULT_VIEW_PAGE_COUNT);
        assertThat(testShopPage.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testShopPage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testShopPage.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testShopPage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testShopPage.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createShopPageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shopPageRepository.findAll().size();

        // Create the ShopPage with an existing ID
        shopPage.setId(1L);
        ShopPageDTO shopPageDTO = shopPageMapper.toDto(shopPage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopPageMockMvc.perform(post("/api/shop-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopPageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopPage in the database
        List<ShopPage> shopPageList = shopPageRepository.findAll();
        assertThat(shopPageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShopPages() throws Exception {
        // Initialize the database
        shopPageRepository.saveAndFlush(shopPage);

        // Get all the shopPageList
        restShopPageMockMvc.perform(get("/api/shop-pages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopPage.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].intro").value(hasItem(DEFAULT_INTRO)))
            .andExpect(jsonPath("$.[*].fullText").value(hasItem(DEFAULT_FULL_TEXT)))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].metaTagTitle").value(hasItem(DEFAULT_META_TAG_TITLE)))
            .andExpect(jsonPath("$.[*].metaTagDescroption").value(hasItem(DEFAULT_META_TAG_DESCROPTION)))
            .andExpect(jsonPath("$.[*].metaTagKeywords").value(hasItem(DEFAULT_META_TAG_KEYWORDS)))
            .andExpect(jsonPath("$.[*].viewPageCount").value(hasItem(DEFAULT_VIEW_PAGE_COUNT)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getShopPage() throws Exception {
        // Initialize the database
        shopPageRepository.saveAndFlush(shopPage);

        // Get the shopPage
        restShopPageMockMvc.perform(get("/api/shop-pages/{id}", shopPage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shopPage.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.intro").value(DEFAULT_INTRO))
            .andExpect(jsonPath("$.fullText").value(DEFAULT_FULL_TEXT))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.metaTagTitle").value(DEFAULT_META_TAG_TITLE))
            .andExpect(jsonPath("$.metaTagDescroption").value(DEFAULT_META_TAG_DESCROPTION))
            .andExpect(jsonPath("$.metaTagKeywords").value(DEFAULT_META_TAG_KEYWORDS))
            .andExpect(jsonPath("$.viewPageCount").value(DEFAULT_VIEW_PAGE_COUNT))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingShopPage() throws Exception {
        // Get the shopPage
        restShopPageMockMvc.perform(get("/api/shop-pages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShopPage() throws Exception {
        // Initialize the database
        shopPageRepository.saveAndFlush(shopPage);

        int databaseSizeBeforeUpdate = shopPageRepository.findAll().size();

        // Update the shopPage
        ShopPage updatedShopPage = shopPageRepository.findById(shopPage.getId()).get();
        // Disconnect from session so that the updates on updatedShopPage are not directly saved in db
        em.detach(updatedShopPage);
        updatedShopPage
            .title(UPDATED_TITLE)
            .intro(UPDATED_INTRO)
            .fullText(UPDATED_FULL_TEXT)
            .sortOrder(UPDATED_SORT_ORDER)
            .metaTagTitle(UPDATED_META_TAG_TITLE)
            .metaTagDescroption(UPDATED_META_TAG_DESCROPTION)
            .metaTagKeywords(UPDATED_META_TAG_KEYWORDS)
            .viewPageCount(UPDATED_VIEW_PAGE_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ShopPageDTO shopPageDTO = shopPageMapper.toDto(updatedShopPage);

        restShopPageMockMvc.perform(put("/api/shop-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopPageDTO)))
            .andExpect(status().isOk());

        // Validate the ShopPage in the database
        List<ShopPage> shopPageList = shopPageRepository.findAll();
        assertThat(shopPageList).hasSize(databaseSizeBeforeUpdate);
        ShopPage testShopPage = shopPageList.get(shopPageList.size() - 1);
        assertThat(testShopPage.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testShopPage.getIntro()).isEqualTo(UPDATED_INTRO);
        assertThat(testShopPage.getFullText()).isEqualTo(UPDATED_FULL_TEXT);
        assertThat(testShopPage.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testShopPage.getMetaTagTitle()).isEqualTo(UPDATED_META_TAG_TITLE);
        assertThat(testShopPage.getMetaTagDescroption()).isEqualTo(UPDATED_META_TAG_DESCROPTION);
        assertThat(testShopPage.getMetaTagKeywords()).isEqualTo(UPDATED_META_TAG_KEYWORDS);
        assertThat(testShopPage.getViewPageCount()).isEqualTo(UPDATED_VIEW_PAGE_COUNT);
        assertThat(testShopPage.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testShopPage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testShopPage.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testShopPage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testShopPage.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingShopPage() throws Exception {
        int databaseSizeBeforeUpdate = shopPageRepository.findAll().size();

        // Create the ShopPage
        ShopPageDTO shopPageDTO = shopPageMapper.toDto(shopPage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopPageMockMvc.perform(put("/api/shop-pages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopPageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopPage in the database
        List<ShopPage> shopPageList = shopPageRepository.findAll();
        assertThat(shopPageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShopPage() throws Exception {
        // Initialize the database
        shopPageRepository.saveAndFlush(shopPage);

        int databaseSizeBeforeDelete = shopPageRepository.findAll().size();

        // Delete the shopPage
        restShopPageMockMvc.perform(delete("/api/shop-pages/{id}", shopPage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShopPage> shopPageList = shopPageRepository.findAll();
        assertThat(shopPageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopPage.class);
        ShopPage shopPage1 = new ShopPage();
        shopPage1.setId(1L);
        ShopPage shopPage2 = new ShopPage();
        shopPage2.setId(shopPage1.getId());
        assertThat(shopPage1).isEqualTo(shopPage2);
        shopPage2.setId(2L);
        assertThat(shopPage1).isNotEqualTo(shopPage2);
        shopPage1.setId(null);
        assertThat(shopPage1).isNotEqualTo(shopPage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopPageDTO.class);
        ShopPageDTO shopPageDTO1 = new ShopPageDTO();
        shopPageDTO1.setId(1L);
        ShopPageDTO shopPageDTO2 = new ShopPageDTO();
        assertThat(shopPageDTO1).isNotEqualTo(shopPageDTO2);
        shopPageDTO2.setId(shopPageDTO1.getId());
        assertThat(shopPageDTO1).isEqualTo(shopPageDTO2);
        shopPageDTO2.setId(2L);
        assertThat(shopPageDTO1).isNotEqualTo(shopPageDTO2);
        shopPageDTO1.setId(null);
        assertThat(shopPageDTO1).isNotEqualTo(shopPageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shopPageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shopPageMapper.fromId(null)).isNull();
    }
}
