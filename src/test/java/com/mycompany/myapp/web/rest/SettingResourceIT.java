package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.Setting;
import com.mycompany.myapp.repository.SettingRepository;
import com.mycompany.myapp.service.SettingService;
import com.mycompany.myapp.service.dto.SettingDTO;
import com.mycompany.myapp.service.mapper.SettingMapper;
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
 * Integration tests for the {@link SettingResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class SettingResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_META_TAG_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_META_TAG_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_META_TAG_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_META_TAG_KEYWORDS = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_STORE_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STORE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_STORE_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_STORE_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_FAX = "AAAAAAAAAA";
    private static final String UPDATED_STORE_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_STORE_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_LOGO_PATH = "AAAAAAAAAA";
    private static final String UPDATED_STORE_LOGO_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAINTENANCE_MODE = 1;
    private static final Integer UPDATED_MAINTENANCE_MODE = 2;

    private static final String DEFAULT_STORE_LANGUAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_LANGUAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER_STATUS = 1;
    private static final Integer UPDATED_ORDER_STATUS = 2;

    private static final String DEFAULT_INVOICE_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_PREFIX = "BBBBBBBBBB";

    private static final Integer DEFAULT_ITEMS_PER_PAGE = 1;
    private static final Integer UPDATED_ITEMS_PER_PAGE = 2;

    private static final Integer DEFAULT_CATEGORY_PRODUCT_COUNT = 1;
    private static final Integer UPDATED_CATEGORY_PRODUCT_COUNT = 2;

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
    private SettingRepository settingRepository;

    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private SettingService settingService;

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

    private MockMvc restSettingMockMvc;

    private Setting setting;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SettingResource settingResource = new SettingResource(settingService);
        this.restSettingMockMvc = MockMvcBuilders.standaloneSetup(settingResource)
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
    public static Setting createEntity(EntityManager em) {
        Setting setting = new Setting()
            .url(DEFAULT_URL)
            .metaTagTitle(DEFAULT_META_TAG_TITLE)
            .metaTagDescription(DEFAULT_META_TAG_DESCRIPTION)
            .metaTagKeywords(DEFAULT_META_TAG_KEYWORDS)
            .storeName(DEFAULT_STORE_NAME)
            .storeOwner(DEFAULT_STORE_OWNER)
            .storeAddress(DEFAULT_STORE_ADDRESS)
            .storeEmail(DEFAULT_STORE_EMAIL)
            .storeTelephone(DEFAULT_STORE_TELEPHONE)
            .storeFax(DEFAULT_STORE_FAX)
            .storeLogo(DEFAULT_STORE_LOGO)
            .storeLogoPath(DEFAULT_STORE_LOGO_PATH)
            .maintenanceMode(DEFAULT_MAINTENANCE_MODE)
            .storeLanguageName(DEFAULT_STORE_LANGUAGE_NAME)
            .storeImage(DEFAULT_STORE_IMAGE)
            .storeImagePath(DEFAULT_STORE_IMAGE_PATH)
            .google(DEFAULT_GOOGLE)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .instagram(DEFAULT_INSTAGRAM)
            .orderStatus(DEFAULT_ORDER_STATUS)
            .invoicePrefix(DEFAULT_INVOICE_PREFIX)
            .itemsPerPage(DEFAULT_ITEMS_PER_PAGE)
            .categoryProductCount(DEFAULT_CATEGORY_PRODUCT_COUNT)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return setting;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Setting createUpdatedEntity(EntityManager em) {
        Setting setting = new Setting()
            .url(UPDATED_URL)
            .metaTagTitle(UPDATED_META_TAG_TITLE)
            .metaTagDescription(UPDATED_META_TAG_DESCRIPTION)
            .metaTagKeywords(UPDATED_META_TAG_KEYWORDS)
            .storeName(UPDATED_STORE_NAME)
            .storeOwner(UPDATED_STORE_OWNER)
            .storeAddress(UPDATED_STORE_ADDRESS)
            .storeEmail(UPDATED_STORE_EMAIL)
            .storeTelephone(UPDATED_STORE_TELEPHONE)
            .storeFax(UPDATED_STORE_FAX)
            .storeLogo(UPDATED_STORE_LOGO)
            .storeLogoPath(UPDATED_STORE_LOGO_PATH)
            .maintenanceMode(UPDATED_MAINTENANCE_MODE)
            .storeLanguageName(UPDATED_STORE_LANGUAGE_NAME)
            .storeImage(UPDATED_STORE_IMAGE)
            .storeImagePath(UPDATED_STORE_IMAGE_PATH)
            .google(UPDATED_GOOGLE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .instagram(UPDATED_INSTAGRAM)
            .orderStatus(UPDATED_ORDER_STATUS)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .itemsPerPage(UPDATED_ITEMS_PER_PAGE)
            .categoryProductCount(UPDATED_CATEGORY_PRODUCT_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return setting;
    }

    @BeforeEach
    public void initTest() {
        setting = createEntity(em);
    }

    @Test
    @Transactional
    public void createSetting() throws Exception {
        int databaseSizeBeforeCreate = settingRepository.findAll().size();

        // Create the Setting
        SettingDTO settingDTO = settingMapper.toDto(setting);
        restSettingMockMvc.perform(post("/api/settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settingDTO)))
            .andExpect(status().isCreated());

        // Validate the Setting in the database
        List<Setting> settingList = settingRepository.findAll();
        assertThat(settingList).hasSize(databaseSizeBeforeCreate + 1);
        Setting testSetting = settingList.get(settingList.size() - 1);
        assertThat(testSetting.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSetting.getMetaTagTitle()).isEqualTo(DEFAULT_META_TAG_TITLE);
        assertThat(testSetting.getMetaTagDescription()).isEqualTo(DEFAULT_META_TAG_DESCRIPTION);
        assertThat(testSetting.getMetaTagKeywords()).isEqualTo(DEFAULT_META_TAG_KEYWORDS);
        assertThat(testSetting.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testSetting.getStoreOwner()).isEqualTo(DEFAULT_STORE_OWNER);
        assertThat(testSetting.getStoreAddress()).isEqualTo(DEFAULT_STORE_ADDRESS);
        assertThat(testSetting.getStoreEmail()).isEqualTo(DEFAULT_STORE_EMAIL);
        assertThat(testSetting.getStoreTelephone()).isEqualTo(DEFAULT_STORE_TELEPHONE);
        assertThat(testSetting.getStoreFax()).isEqualTo(DEFAULT_STORE_FAX);
        assertThat(testSetting.getStoreLogo()).isEqualTo(DEFAULT_STORE_LOGO);
        assertThat(testSetting.getStoreLogoPath()).isEqualTo(DEFAULT_STORE_LOGO_PATH);
        assertThat(testSetting.getMaintenanceMode()).isEqualTo(DEFAULT_MAINTENANCE_MODE);
        assertThat(testSetting.getStoreLanguageName()).isEqualTo(DEFAULT_STORE_LANGUAGE_NAME);
        assertThat(testSetting.getStoreImage()).isEqualTo(DEFAULT_STORE_IMAGE);
        assertThat(testSetting.getStoreImagePath()).isEqualTo(DEFAULT_STORE_IMAGE_PATH);
        assertThat(testSetting.getGoogle()).isEqualTo(DEFAULT_GOOGLE);
        assertThat(testSetting.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testSetting.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testSetting.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testSetting.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testSetting.getInvoicePrefix()).isEqualTo(DEFAULT_INVOICE_PREFIX);
        assertThat(testSetting.getItemsPerPage()).isEqualTo(DEFAULT_ITEMS_PER_PAGE);
        assertThat(testSetting.getCategoryProductCount()).isEqualTo(DEFAULT_CATEGORY_PRODUCT_COUNT);
        assertThat(testSetting.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSetting.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSetting.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testSetting.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSetting.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createSettingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = settingRepository.findAll().size();

        // Create the Setting with an existing ID
        setting.setId(1L);
        SettingDTO settingDTO = settingMapper.toDto(setting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSettingMockMvc.perform(post("/api/settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Setting in the database
        List<Setting> settingList = settingRepository.findAll();
        assertThat(settingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSettings() throws Exception {
        // Initialize the database
        settingRepository.saveAndFlush(setting);

        // Get all the settingList
        restSettingMockMvc.perform(get("/api/settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(setting.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].metaTagTitle").value(hasItem(DEFAULT_META_TAG_TITLE)))
            .andExpect(jsonPath("$.[*].metaTagDescription").value(hasItem(DEFAULT_META_TAG_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].metaTagKeywords").value(hasItem(DEFAULT_META_TAG_KEYWORDS)))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storeOwner").value(hasItem(DEFAULT_STORE_OWNER)))
            .andExpect(jsonPath("$.[*].storeAddress").value(hasItem(DEFAULT_STORE_ADDRESS)))
            .andExpect(jsonPath("$.[*].storeEmail").value(hasItem(DEFAULT_STORE_EMAIL)))
            .andExpect(jsonPath("$.[*].storeTelephone").value(hasItem(DEFAULT_STORE_TELEPHONE)))
            .andExpect(jsonPath("$.[*].storeFax").value(hasItem(DEFAULT_STORE_FAX)))
            .andExpect(jsonPath("$.[*].storeLogo").value(hasItem(DEFAULT_STORE_LOGO)))
            .andExpect(jsonPath("$.[*].storeLogoPath").value(hasItem(DEFAULT_STORE_LOGO_PATH)))
            .andExpect(jsonPath("$.[*].maintenanceMode").value(hasItem(DEFAULT_MAINTENANCE_MODE)))
            .andExpect(jsonPath("$.[*].storeLanguageName").value(hasItem(DEFAULT_STORE_LANGUAGE_NAME)))
            .andExpect(jsonPath("$.[*].storeImage").value(hasItem(DEFAULT_STORE_IMAGE)))
            .andExpect(jsonPath("$.[*].storeImagePath").value(hasItem(DEFAULT_STORE_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].google").value(hasItem(DEFAULT_GOOGLE)))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM)))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS)))
            .andExpect(jsonPath("$.[*].invoicePrefix").value(hasItem(DEFAULT_INVOICE_PREFIX)))
            .andExpect(jsonPath("$.[*].itemsPerPage").value(hasItem(DEFAULT_ITEMS_PER_PAGE)))
            .andExpect(jsonPath("$.[*].categoryProductCount").value(hasItem(DEFAULT_CATEGORY_PRODUCT_COUNT)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getSetting() throws Exception {
        // Initialize the database
        settingRepository.saveAndFlush(setting);

        // Get the setting
        restSettingMockMvc.perform(get("/api/settings/{id}", setting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(setting.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.metaTagTitle").value(DEFAULT_META_TAG_TITLE))
            .andExpect(jsonPath("$.metaTagDescription").value(DEFAULT_META_TAG_DESCRIPTION))
            .andExpect(jsonPath("$.metaTagKeywords").value(DEFAULT_META_TAG_KEYWORDS))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME))
            .andExpect(jsonPath("$.storeOwner").value(DEFAULT_STORE_OWNER))
            .andExpect(jsonPath("$.storeAddress").value(DEFAULT_STORE_ADDRESS))
            .andExpect(jsonPath("$.storeEmail").value(DEFAULT_STORE_EMAIL))
            .andExpect(jsonPath("$.storeTelephone").value(DEFAULT_STORE_TELEPHONE))
            .andExpect(jsonPath("$.storeFax").value(DEFAULT_STORE_FAX))
            .andExpect(jsonPath("$.storeLogo").value(DEFAULT_STORE_LOGO))
            .andExpect(jsonPath("$.storeLogoPath").value(DEFAULT_STORE_LOGO_PATH))
            .andExpect(jsonPath("$.maintenanceMode").value(DEFAULT_MAINTENANCE_MODE))
            .andExpect(jsonPath("$.storeLanguageName").value(DEFAULT_STORE_LANGUAGE_NAME))
            .andExpect(jsonPath("$.storeImage").value(DEFAULT_STORE_IMAGE))
            .andExpect(jsonPath("$.storeImagePath").value(DEFAULT_STORE_IMAGE_PATH))
            .andExpect(jsonPath("$.google").value(DEFAULT_GOOGLE))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS))
            .andExpect(jsonPath("$.invoicePrefix").value(DEFAULT_INVOICE_PREFIX))
            .andExpect(jsonPath("$.itemsPerPage").value(DEFAULT_ITEMS_PER_PAGE))
            .andExpect(jsonPath("$.categoryProductCount").value(DEFAULT_CATEGORY_PRODUCT_COUNT))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingSetting() throws Exception {
        // Get the setting
        restSettingMockMvc.perform(get("/api/settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSetting() throws Exception {
        // Initialize the database
        settingRepository.saveAndFlush(setting);

        int databaseSizeBeforeUpdate = settingRepository.findAll().size();

        // Update the setting
        Setting updatedSetting = settingRepository.findById(setting.getId()).get();
        // Disconnect from session so that the updates on updatedSetting are not directly saved in db
        em.detach(updatedSetting);
        updatedSetting
            .url(UPDATED_URL)
            .metaTagTitle(UPDATED_META_TAG_TITLE)
            .metaTagDescription(UPDATED_META_TAG_DESCRIPTION)
            .metaTagKeywords(UPDATED_META_TAG_KEYWORDS)
            .storeName(UPDATED_STORE_NAME)
            .storeOwner(UPDATED_STORE_OWNER)
            .storeAddress(UPDATED_STORE_ADDRESS)
            .storeEmail(UPDATED_STORE_EMAIL)
            .storeTelephone(UPDATED_STORE_TELEPHONE)
            .storeFax(UPDATED_STORE_FAX)
            .storeLogo(UPDATED_STORE_LOGO)
            .storeLogoPath(UPDATED_STORE_LOGO_PATH)
            .maintenanceMode(UPDATED_MAINTENANCE_MODE)
            .storeLanguageName(UPDATED_STORE_LANGUAGE_NAME)
            .storeImage(UPDATED_STORE_IMAGE)
            .storeImagePath(UPDATED_STORE_IMAGE_PATH)
            .google(UPDATED_GOOGLE)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .instagram(UPDATED_INSTAGRAM)
            .orderStatus(UPDATED_ORDER_STATUS)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .itemsPerPage(UPDATED_ITEMS_PER_PAGE)
            .categoryProductCount(UPDATED_CATEGORY_PRODUCT_COUNT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        SettingDTO settingDTO = settingMapper.toDto(updatedSetting);

        restSettingMockMvc.perform(put("/api/settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settingDTO)))
            .andExpect(status().isOk());

        // Validate the Setting in the database
        List<Setting> settingList = settingRepository.findAll();
        assertThat(settingList).hasSize(databaseSizeBeforeUpdate);
        Setting testSetting = settingList.get(settingList.size() - 1);
        assertThat(testSetting.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSetting.getMetaTagTitle()).isEqualTo(UPDATED_META_TAG_TITLE);
        assertThat(testSetting.getMetaTagDescription()).isEqualTo(UPDATED_META_TAG_DESCRIPTION);
        assertThat(testSetting.getMetaTagKeywords()).isEqualTo(UPDATED_META_TAG_KEYWORDS);
        assertThat(testSetting.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testSetting.getStoreOwner()).isEqualTo(UPDATED_STORE_OWNER);
        assertThat(testSetting.getStoreAddress()).isEqualTo(UPDATED_STORE_ADDRESS);
        assertThat(testSetting.getStoreEmail()).isEqualTo(UPDATED_STORE_EMAIL);
        assertThat(testSetting.getStoreTelephone()).isEqualTo(UPDATED_STORE_TELEPHONE);
        assertThat(testSetting.getStoreFax()).isEqualTo(UPDATED_STORE_FAX);
        assertThat(testSetting.getStoreLogo()).isEqualTo(UPDATED_STORE_LOGO);
        assertThat(testSetting.getStoreLogoPath()).isEqualTo(UPDATED_STORE_LOGO_PATH);
        assertThat(testSetting.getMaintenanceMode()).isEqualTo(UPDATED_MAINTENANCE_MODE);
        assertThat(testSetting.getStoreLanguageName()).isEqualTo(UPDATED_STORE_LANGUAGE_NAME);
        assertThat(testSetting.getStoreImage()).isEqualTo(UPDATED_STORE_IMAGE);
        assertThat(testSetting.getStoreImagePath()).isEqualTo(UPDATED_STORE_IMAGE_PATH);
        assertThat(testSetting.getGoogle()).isEqualTo(UPDATED_GOOGLE);
        assertThat(testSetting.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testSetting.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testSetting.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testSetting.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testSetting.getInvoicePrefix()).isEqualTo(UPDATED_INVOICE_PREFIX);
        assertThat(testSetting.getItemsPerPage()).isEqualTo(UPDATED_ITEMS_PER_PAGE);
        assertThat(testSetting.getCategoryProductCount()).isEqualTo(UPDATED_CATEGORY_PRODUCT_COUNT);
        assertThat(testSetting.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSetting.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSetting.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testSetting.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSetting.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSetting() throws Exception {
        int databaseSizeBeforeUpdate = settingRepository.findAll().size();

        // Create the Setting
        SettingDTO settingDTO = settingMapper.toDto(setting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSettingMockMvc.perform(put("/api/settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(settingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Setting in the database
        List<Setting> settingList = settingRepository.findAll();
        assertThat(settingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSetting() throws Exception {
        // Initialize the database
        settingRepository.saveAndFlush(setting);

        int databaseSizeBeforeDelete = settingRepository.findAll().size();

        // Delete the setting
        restSettingMockMvc.perform(delete("/api/settings/{id}", setting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Setting> settingList = settingRepository.findAll();
        assertThat(settingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Setting.class);
        Setting setting1 = new Setting();
        setting1.setId(1L);
        Setting setting2 = new Setting();
        setting2.setId(setting1.getId());
        assertThat(setting1).isEqualTo(setting2);
        setting2.setId(2L);
        assertThat(setting1).isNotEqualTo(setting2);
        setting1.setId(null);
        assertThat(setting1).isNotEqualTo(setting2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SettingDTO.class);
        SettingDTO settingDTO1 = new SettingDTO();
        settingDTO1.setId(1L);
        SettingDTO settingDTO2 = new SettingDTO();
        assertThat(settingDTO1).isNotEqualTo(settingDTO2);
        settingDTO2.setId(settingDTO1.getId());
        assertThat(settingDTO1).isEqualTo(settingDTO2);
        settingDTO2.setId(2L);
        assertThat(settingDTO1).isNotEqualTo(settingDTO2);
        settingDTO1.setId(null);
        assertThat(settingDTO1).isNotEqualTo(settingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(settingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(settingMapper.fromId(null)).isNull();
    }
}
