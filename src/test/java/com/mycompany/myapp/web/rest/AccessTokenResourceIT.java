package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.AccessToken;
import com.mycompany.myapp.repository.AccessTokenRepository;
import com.mycompany.myapp.service.AccessTokenService;
import com.mycompany.myapp.service.dto.AccessTokenDTO;
import com.mycompany.myapp.service.mapper.AccessTokenMapper;
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
 * Integration tests for the {@link AccessTokenResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class AccessTokenResourceIT {

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Autowired
    private AccessTokenService accessTokenService;

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

    private MockMvc restAccessTokenMockMvc;

    private AccessToken accessToken;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccessTokenResource accessTokenResource = new AccessTokenResource(accessTokenService);
        this.restAccessTokenMockMvc = MockMvcBuilders.standaloneSetup(accessTokenResource)
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
    public static AccessToken createEntity(EntityManager em) {
        AccessToken accessToken = new AccessToken()
            .token(DEFAULT_TOKEN);
        return accessToken;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccessToken createUpdatedEntity(EntityManager em) {
        AccessToken accessToken = new AccessToken()
            .token(UPDATED_TOKEN);
        return accessToken;
    }

    @BeforeEach
    public void initTest() {
        accessToken = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccessToken() throws Exception {
        int databaseSizeBeforeCreate = accessTokenRepository.findAll().size();

        // Create the AccessToken
        AccessTokenDTO accessTokenDTO = accessTokenMapper.toDto(accessToken);
        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessTokenDTO)))
            .andExpect(status().isCreated());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeCreate + 1);
        AccessToken testAccessToken = accessTokenList.get(accessTokenList.size() - 1);
        assertThat(testAccessToken.getToken()).isEqualTo(DEFAULT_TOKEN);
    }

    @Test
    @Transactional
    public void createAccessTokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accessTokenRepository.findAll().size();

        // Create the AccessToken with an existing ID
        accessToken.setId(1L);
        AccessTokenDTO accessTokenDTO = accessTokenMapper.toDto(accessToken);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccessTokenMockMvc.perform(post("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccessTokens() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        // Get all the accessTokenList
        restAccessTokenMockMvc.perform(get("/api/access-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accessToken.getId().intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)));
    }
    
    @Test
    @Transactional
    public void getAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        // Get the accessToken
        restAccessTokenMockMvc.perform(get("/api/access-tokens/{id}", accessToken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accessToken.getId().intValue()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN));
    }

    @Test
    @Transactional
    public void getNonExistingAccessToken() throws Exception {
        // Get the accessToken
        restAccessTokenMockMvc.perform(get("/api/access-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        int databaseSizeBeforeUpdate = accessTokenRepository.findAll().size();

        // Update the accessToken
        AccessToken updatedAccessToken = accessTokenRepository.findById(accessToken.getId()).get();
        // Disconnect from session so that the updates on updatedAccessToken are not directly saved in db
        em.detach(updatedAccessToken);
        updatedAccessToken
            .token(UPDATED_TOKEN);
        AccessTokenDTO accessTokenDTO = accessTokenMapper.toDto(updatedAccessToken);

        restAccessTokenMockMvc.perform(put("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessTokenDTO)))
            .andExpect(status().isOk());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeUpdate);
        AccessToken testAccessToken = accessTokenList.get(accessTokenList.size() - 1);
        assertThat(testAccessToken.getToken()).isEqualTo(UPDATED_TOKEN);
    }

    @Test
    @Transactional
    public void updateNonExistingAccessToken() throws Exception {
        int databaseSizeBeforeUpdate = accessTokenRepository.findAll().size();

        // Create the AccessToken
        AccessTokenDTO accessTokenDTO = accessTokenMapper.toDto(accessToken);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccessTokenMockMvc.perform(put("/api/access-tokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accessTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccessToken in the database
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccessToken() throws Exception {
        // Initialize the database
        accessTokenRepository.saveAndFlush(accessToken);

        int databaseSizeBeforeDelete = accessTokenRepository.findAll().size();

        // Delete the accessToken
        restAccessTokenMockMvc.perform(delete("/api/access-tokens/{id}", accessToken.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccessToken> accessTokenList = accessTokenRepository.findAll();
        assertThat(accessTokenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccessToken.class);
        AccessToken accessToken1 = new AccessToken();
        accessToken1.setId(1L);
        AccessToken accessToken2 = new AccessToken();
        accessToken2.setId(accessToken1.getId());
        assertThat(accessToken1).isEqualTo(accessToken2);
        accessToken2.setId(2L);
        assertThat(accessToken1).isNotEqualTo(accessToken2);
        accessToken1.setId(null);
        assertThat(accessToken1).isNotEqualTo(accessToken2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccessTokenDTO.class);
        AccessTokenDTO accessTokenDTO1 = new AccessTokenDTO();
        accessTokenDTO1.setId(1L);
        AccessTokenDTO accessTokenDTO2 = new AccessTokenDTO();
        assertThat(accessTokenDTO1).isNotEqualTo(accessTokenDTO2);
        accessTokenDTO2.setId(accessTokenDTO1.getId());
        assertThat(accessTokenDTO1).isEqualTo(accessTokenDTO2);
        accessTokenDTO2.setId(2L);
        assertThat(accessTokenDTO1).isNotEqualTo(accessTokenDTO2);
        accessTokenDTO1.setId(null);
        assertThat(accessTokenDTO1).isNotEqualTo(accessTokenDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accessTokenMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accessTokenMapper.fromId(null)).isNull();
    }
}
