package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.LoginLog;
import com.mycompany.myapp.repository.LoginLogRepository;
import com.mycompany.myapp.service.LoginLogService;
import com.mycompany.myapp.service.dto.LoginLogDTO;
import com.mycompany.myapp.service.mapper.LoginLogMapper;
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
 * Integration tests for the {@link LoginLogResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class LoginLogResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final Long DEFAULT_MODIFIED_BY = 1L;
    private static final Long UPDATED_MODIFIED_BY = 2L;

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_DATE = "BBBBBBBBBB";

    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private LoginLogService loginLogService;

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

    private MockMvc restLoginLogMockMvc;

    private LoginLog loginLog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoginLogResource loginLogResource = new LoginLogResource(loginLogService);
        this.restLoginLogMockMvc = MockMvcBuilders.standaloneSetup(loginLogResource)
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
    public static LoginLog createEntity(EntityManager em) {
        LoginLog loginLog = new LoginLog()
            .firstName(DEFAULT_FIRST_NAME)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return loginLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoginLog createUpdatedEntity(EntityManager em) {
        LoginLog loginLog = new LoginLog()
            .firstName(UPDATED_FIRST_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return loginLog;
    }

    @BeforeEach
    public void initTest() {
        loginLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoginLog() throws Exception {
        int databaseSizeBeforeCreate = loginLogRepository.findAll().size();

        // Create the LoginLog
        LoginLogDTO loginLogDTO = loginLogMapper.toDto(loginLog);
        restLoginLogMockMvc.perform(post("/api/login-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginLogDTO)))
            .andExpect(status().isCreated());

        // Validate the LoginLog in the database
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        assertThat(loginLogList).hasSize(databaseSizeBeforeCreate + 1);
        LoginLog testLoginLog = loginLogList.get(loginLogList.size() - 1);
        assertThat(testLoginLog.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testLoginLog.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testLoginLog.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testLoginLog.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testLoginLog.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testLoginLog.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createLoginLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loginLogRepository.findAll().size();

        // Create the LoginLog with an existing ID
        loginLog.setId(1L);
        LoginLogDTO loginLogDTO = loginLogMapper.toDto(loginLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoginLogMockMvc.perform(post("/api/login-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoginLog in the database
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        assertThat(loginLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLoginLogs() throws Exception {
        // Initialize the database
        loginLogRepository.saveAndFlush(loginLog);

        // Get all the loginLogList
        restLoginLogMockMvc.perform(get("/api/login-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loginLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getLoginLog() throws Exception {
        // Initialize the database
        loginLogRepository.saveAndFlush(loginLog);

        // Get the loginLog
        restLoginLogMockMvc.perform(get("/api/login-logs/{id}", loginLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loginLog.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingLoginLog() throws Exception {
        // Get the loginLog
        restLoginLogMockMvc.perform(get("/api/login-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoginLog() throws Exception {
        // Initialize the database
        loginLogRepository.saveAndFlush(loginLog);

        int databaseSizeBeforeUpdate = loginLogRepository.findAll().size();

        // Update the loginLog
        LoginLog updatedLoginLog = loginLogRepository.findById(loginLog.getId()).get();
        // Disconnect from session so that the updates on updatedLoginLog are not directly saved in db
        em.detach(updatedLoginLog);
        updatedLoginLog
            .firstName(UPDATED_FIRST_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        LoginLogDTO loginLogDTO = loginLogMapper.toDto(updatedLoginLog);

        restLoginLogMockMvc.perform(put("/api/login-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginLogDTO)))
            .andExpect(status().isOk());

        // Validate the LoginLog in the database
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        assertThat(loginLogList).hasSize(databaseSizeBeforeUpdate);
        LoginLog testLoginLog = loginLogList.get(loginLogList.size() - 1);
        assertThat(testLoginLog.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testLoginLog.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testLoginLog.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testLoginLog.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testLoginLog.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testLoginLog.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLoginLog() throws Exception {
        int databaseSizeBeforeUpdate = loginLogRepository.findAll().size();

        // Create the LoginLog
        LoginLogDTO loginLogDTO = loginLogMapper.toDto(loginLog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoginLogMockMvc.perform(put("/api/login-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoginLog in the database
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        assertThat(loginLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoginLog() throws Exception {
        // Initialize the database
        loginLogRepository.saveAndFlush(loginLog);

        int databaseSizeBeforeDelete = loginLogRepository.findAll().size();

        // Delete the loginLog
        restLoginLogMockMvc.perform(delete("/api/login-logs/{id}", loginLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        assertThat(loginLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoginLog.class);
        LoginLog loginLog1 = new LoginLog();
        loginLog1.setId(1L);
        LoginLog loginLog2 = new LoginLog();
        loginLog2.setId(loginLog1.getId());
        assertThat(loginLog1).isEqualTo(loginLog2);
        loginLog2.setId(2L);
        assertThat(loginLog1).isNotEqualTo(loginLog2);
        loginLog1.setId(null);
        assertThat(loginLog1).isNotEqualTo(loginLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoginLogDTO.class);
        LoginLogDTO loginLogDTO1 = new LoginLogDTO();
        loginLogDTO1.setId(1L);
        LoginLogDTO loginLogDTO2 = new LoginLogDTO();
        assertThat(loginLogDTO1).isNotEqualTo(loginLogDTO2);
        loginLogDTO2.setId(loginLogDTO1.getId());
        assertThat(loginLogDTO1).isEqualTo(loginLogDTO2);
        loginLogDTO2.setId(2L);
        assertThat(loginLogDTO1).isNotEqualTo(loginLogDTO2);
        loginLogDTO1.setId(null);
        assertThat(loginLogDTO1).isNotEqualTo(loginLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(loginLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(loginLogMapper.fromId(null)).isNull();
    }
}
