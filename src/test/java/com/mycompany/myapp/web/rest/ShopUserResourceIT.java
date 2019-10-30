package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ShopUser;
import com.mycompany.myapp.repository.ShopUserRepository;
import com.mycompany.myapp.service.ShopUserService;
import com.mycompany.myapp.service.dto.ShopUserDTO;
import com.mycompany.myapp.service.mapper.ShopUserMapper;
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
 * Integration tests for the {@link ShopUserResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ShopUserResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR_PATH = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHONE_NUMBER = 1;
    private static final Integer UPDATED_PHONE_NUMBER = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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
    private ShopUserRepository shopUserRepository;

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private ShopUserService shopUserService;

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

    private MockMvc restShopUserMockMvc;

    private ShopUser shopUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShopUserResource shopUserResource = new ShopUserResource(shopUserService);
        this.restShopUserMockMvc = MockMvcBuilders.standaloneSetup(shopUserResource)
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
    public static ShopUser createEntity(EntityManager em) {
        ShopUser shopUser = new ShopUser()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .avatar(DEFAULT_AVATAR)
            .avatarPath(DEFAULT_AVATAR_PATH)
            .code(DEFAULT_CODE)
            .ip(DEFAULT_IP)
            .address(DEFAULT_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .name(DEFAULT_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return shopUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopUser createUpdatedEntity(EntityManager em) {
        ShopUser shopUser = new ShopUser()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .avatar(UPDATED_AVATAR)
            .avatarPath(UPDATED_AVATAR_PATH)
            .code(UPDATED_CODE)
            .ip(UPDATED_IP)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return shopUser;
    }

    @BeforeEach
    public void initTest() {
        shopUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createShopUser() throws Exception {
        int databaseSizeBeforeCreate = shopUserRepository.findAll().size();

        // Create the ShopUser
        ShopUserDTO shopUserDTO = shopUserMapper.toDto(shopUser);
        restShopUserMockMvc.perform(post("/api/shop-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopUserDTO)))
            .andExpect(status().isCreated());

        // Validate the ShopUser in the database
        List<ShopUser> shopUserList = shopUserRepository.findAll();
        assertThat(shopUserList).hasSize(databaseSizeBeforeCreate + 1);
        ShopUser testShopUser = shopUserList.get(shopUserList.size() - 1);
        assertThat(testShopUser.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testShopUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testShopUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testShopUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testShopUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testShopUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testShopUser.getAvatarPath()).isEqualTo(DEFAULT_AVATAR_PATH);
        assertThat(testShopUser.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testShopUser.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testShopUser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testShopUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testShopUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShopUser.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testShopUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testShopUser.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testShopUser.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testShopUser.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createShopUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shopUserRepository.findAll().size();

        // Create the ShopUser with an existing ID
        shopUser.setId(1L);
        ShopUserDTO shopUserDTO = shopUserMapper.toDto(shopUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopUserMockMvc.perform(post("/api/shop-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopUser in the database
        List<ShopUser> shopUserList = shopUserRepository.findAll();
        assertThat(shopUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShopUsers() throws Exception {
        // Initialize the database
        shopUserRepository.saveAndFlush(shopUser);

        // Get all the shopUserList
        restShopUserMockMvc.perform(get("/api/shop-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].avatarPath").value(hasItem(DEFAULT_AVATAR_PATH)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getShopUser() throws Exception {
        // Initialize the database
        shopUserRepository.saveAndFlush(shopUser);

        // Get the shopUser
        restShopUserMockMvc.perform(get("/api/shop-users/{id}", shopUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shopUser.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.avatarPath").value(DEFAULT_AVATAR_PATH))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingShopUser() throws Exception {
        // Get the shopUser
        restShopUserMockMvc.perform(get("/api/shop-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShopUser() throws Exception {
        // Initialize the database
        shopUserRepository.saveAndFlush(shopUser);

        int databaseSizeBeforeUpdate = shopUserRepository.findAll().size();

        // Update the shopUser
        ShopUser updatedShopUser = shopUserRepository.findById(shopUser.getId()).get();
        // Disconnect from session so that the updates on updatedShopUser are not directly saved in db
        em.detach(updatedShopUser);
        updatedShopUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .avatar(UPDATED_AVATAR)
            .avatarPath(UPDATED_AVATAR_PATH)
            .code(UPDATED_CODE)
            .ip(UPDATED_IP)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .name(UPDATED_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ShopUserDTO shopUserDTO = shopUserMapper.toDto(updatedShopUser);

        restShopUserMockMvc.perform(put("/api/shop-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopUserDTO)))
            .andExpect(status().isOk());

        // Validate the ShopUser in the database
        List<ShopUser> shopUserList = shopUserRepository.findAll();
        assertThat(shopUserList).hasSize(databaseSizeBeforeUpdate);
        ShopUser testShopUser = shopUserList.get(shopUserList.size() - 1);
        assertThat(testShopUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testShopUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testShopUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testShopUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testShopUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testShopUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testShopUser.getAvatarPath()).isEqualTo(UPDATED_AVATAR_PATH);
        assertThat(testShopUser.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testShopUser.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testShopUser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testShopUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testShopUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShopUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testShopUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testShopUser.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testShopUser.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testShopUser.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingShopUser() throws Exception {
        int databaseSizeBeforeUpdate = shopUserRepository.findAll().size();

        // Create the ShopUser
        ShopUserDTO shopUserDTO = shopUserMapper.toDto(shopUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopUserMockMvc.perform(put("/api/shop-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shopUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopUser in the database
        List<ShopUser> shopUserList = shopUserRepository.findAll();
        assertThat(shopUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShopUser() throws Exception {
        // Initialize the database
        shopUserRepository.saveAndFlush(shopUser);

        int databaseSizeBeforeDelete = shopUserRepository.findAll().size();

        // Delete the shopUser
        restShopUserMockMvc.perform(delete("/api/shop-users/{id}", shopUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShopUser> shopUserList = shopUserRepository.findAll();
        assertThat(shopUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopUser.class);
        ShopUser shopUser1 = new ShopUser();
        shopUser1.setId(1L);
        ShopUser shopUser2 = new ShopUser();
        shopUser2.setId(shopUser1.getId());
        assertThat(shopUser1).isEqualTo(shopUser2);
        shopUser2.setId(2L);
        assertThat(shopUser1).isNotEqualTo(shopUser2);
        shopUser1.setId(null);
        assertThat(shopUser1).isNotEqualTo(shopUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopUserDTO.class);
        ShopUserDTO shopUserDTO1 = new ShopUserDTO();
        shopUserDTO1.setId(1L);
        ShopUserDTO shopUserDTO2 = new ShopUserDTO();
        assertThat(shopUserDTO1).isNotEqualTo(shopUserDTO2);
        shopUserDTO2.setId(shopUserDTO1.getId());
        assertThat(shopUserDTO1).isEqualTo(shopUserDTO2);
        shopUserDTO2.setId(2L);
        assertThat(shopUserDTO1).isNotEqualTo(shopUserDTO2);
        shopUserDTO1.setId(null);
        assertThat(shopUserDTO1).isNotEqualTo(shopUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shopUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shopUserMapper.fromId(null)).isNull();
    }
}
