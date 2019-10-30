package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ShopApp;
import com.mycompany.myapp.domain.ProductDiscount;
import com.mycompany.myapp.repository.ProductDiscountRepository;
import com.mycompany.myapp.service.ProductDiscountService;
import com.mycompany.myapp.service.dto.ProductDiscountDTO;
import com.mycompany.myapp.service.mapper.ProductDiscountMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductDiscountResource} REST controller.
 */
@SpringBootTest(classes = ShopApp.class)
public class ProductDiscountResourceIT {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final BigDecimal DEFAULT_PIRCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PIRCE = new BigDecimal(2);

    private static final String DEFAULT_DATE_START = "AAAAAAAAAA";
    private static final String UPDATED_DATE_START = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_END = "AAAAAAAAAA";
    private static final String UPDATED_DATE_END = "BBBBBBBBBB";

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
    private ProductDiscountRepository productDiscountRepository;

    @Autowired
    private ProductDiscountMapper productDiscountMapper;

    @Autowired
    private ProductDiscountService productDiscountService;

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

    private MockMvc restProductDiscountMockMvc;

    private ProductDiscount productDiscount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductDiscountResource productDiscountResource = new ProductDiscountResource(productDiscountService);
        this.restProductDiscountMockMvc = MockMvcBuilders.standaloneSetup(productDiscountResource)
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
    public static ProductDiscount createEntity(EntityManager em) {
        ProductDiscount productDiscount = new ProductDiscount()
            .quantity(DEFAULT_QUANTITY)
            .priority(DEFAULT_PRIORITY)
            .pirce(DEFAULT_PIRCE)
            .dateStart(DEFAULT_DATE_START)
            .dateEnd(DEFAULT_DATE_END)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return productDiscount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductDiscount createUpdatedEntity(EntityManager em) {
        ProductDiscount productDiscount = new ProductDiscount()
            .quantity(UPDATED_QUANTITY)
            .priority(UPDATED_PRIORITY)
            .pirce(UPDATED_PIRCE)
            .dateStart(UPDATED_DATE_START)
            .dateEnd(UPDATED_DATE_END)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return productDiscount;
    }

    @BeforeEach
    public void initTest() {
        productDiscount = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductDiscount() throws Exception {
        int databaseSizeBeforeCreate = productDiscountRepository.findAll().size();

        // Create the ProductDiscount
        ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDto(productDiscount);
        restProductDiscountMockMvc.perform(post("/api/product-discounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDiscountDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductDiscount in the database
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        assertThat(productDiscountList).hasSize(databaseSizeBeforeCreate + 1);
        ProductDiscount testProductDiscount = productDiscountList.get(productDiscountList.size() - 1);
        assertThat(testProductDiscount.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProductDiscount.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testProductDiscount.getPirce()).isEqualTo(DEFAULT_PIRCE);
        assertThat(testProductDiscount.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testProductDiscount.getDateEnd()).isEqualTo(DEFAULT_DATE_END);
        assertThat(testProductDiscount.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProductDiscount.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductDiscount.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProductDiscount.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProductDiscount.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProductDiscountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productDiscountRepository.findAll().size();

        // Create the ProductDiscount with an existing ID
        productDiscount.setId(1L);
        ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDto(productDiscount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductDiscountMockMvc.perform(post("/api/product-discounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDiscountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductDiscount in the database
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        assertThat(productDiscountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductDiscounts() throws Exception {
        // Initialize the database
        productDiscountRepository.saveAndFlush(productDiscount);

        // Get all the productDiscountList
        restProductDiscountMockMvc.perform(get("/api/product-discounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productDiscount.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].pirce").value(hasItem(DEFAULT_PIRCE.intValue())))
            .andExpect(jsonPath("$.[*].dateStart").value(hasItem(DEFAULT_DATE_START)))
            .andExpect(jsonPath("$.[*].dateEnd").value(hasItem(DEFAULT_DATE_END)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE)));
    }
    
    @Test
    @Transactional
    public void getProductDiscount() throws Exception {
        // Initialize the database
        productDiscountRepository.saveAndFlush(productDiscount);

        // Get the productDiscount
        restProductDiscountMockMvc.perform(get("/api/product-discounts/{id}", productDiscount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productDiscount.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.pirce").value(DEFAULT_PIRCE.intValue()))
            .andExpect(jsonPath("$.dateStart").value(DEFAULT_DATE_START))
            .andExpect(jsonPath("$.dateEnd").value(DEFAULT_DATE_END))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE));
    }

    @Test
    @Transactional
    public void getNonExistingProductDiscount() throws Exception {
        // Get the productDiscount
        restProductDiscountMockMvc.perform(get("/api/product-discounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductDiscount() throws Exception {
        // Initialize the database
        productDiscountRepository.saveAndFlush(productDiscount);

        int databaseSizeBeforeUpdate = productDiscountRepository.findAll().size();

        // Update the productDiscount
        ProductDiscount updatedProductDiscount = productDiscountRepository.findById(productDiscount.getId()).get();
        // Disconnect from session so that the updates on updatedProductDiscount are not directly saved in db
        em.detach(updatedProductDiscount);
        updatedProductDiscount
            .quantity(UPDATED_QUANTITY)
            .priority(UPDATED_PRIORITY)
            .pirce(UPDATED_PIRCE)
            .dateStart(UPDATED_DATE_START)
            .dateEnd(UPDATED_DATE_END)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDto(updatedProductDiscount);

        restProductDiscountMockMvc.perform(put("/api/product-discounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDiscountDTO)))
            .andExpect(status().isOk());

        // Validate the ProductDiscount in the database
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        assertThat(productDiscountList).hasSize(databaseSizeBeforeUpdate);
        ProductDiscount testProductDiscount = productDiscountList.get(productDiscountList.size() - 1);
        assertThat(testProductDiscount.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProductDiscount.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testProductDiscount.getPirce()).isEqualTo(UPDATED_PIRCE);
        assertThat(testProductDiscount.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testProductDiscount.getDateEnd()).isEqualTo(UPDATED_DATE_END);
        assertThat(testProductDiscount.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProductDiscount.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductDiscount.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProductDiscount.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProductDiscount.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductDiscount() throws Exception {
        int databaseSizeBeforeUpdate = productDiscountRepository.findAll().size();

        // Create the ProductDiscount
        ProductDiscountDTO productDiscountDTO = productDiscountMapper.toDto(productDiscount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductDiscountMockMvc.perform(put("/api/product-discounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDiscountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductDiscount in the database
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        assertThat(productDiscountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductDiscount() throws Exception {
        // Initialize the database
        productDiscountRepository.saveAndFlush(productDiscount);

        int databaseSizeBeforeDelete = productDiscountRepository.findAll().size();

        // Delete the productDiscount
        restProductDiscountMockMvc.perform(delete("/api/product-discounts/{id}", productDiscount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        assertThat(productDiscountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDiscount.class);
        ProductDiscount productDiscount1 = new ProductDiscount();
        productDiscount1.setId(1L);
        ProductDiscount productDiscount2 = new ProductDiscount();
        productDiscount2.setId(productDiscount1.getId());
        assertThat(productDiscount1).isEqualTo(productDiscount2);
        productDiscount2.setId(2L);
        assertThat(productDiscount1).isNotEqualTo(productDiscount2);
        productDiscount1.setId(null);
        assertThat(productDiscount1).isNotEqualTo(productDiscount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDiscountDTO.class);
        ProductDiscountDTO productDiscountDTO1 = new ProductDiscountDTO();
        productDiscountDTO1.setId(1L);
        ProductDiscountDTO productDiscountDTO2 = new ProductDiscountDTO();
        assertThat(productDiscountDTO1).isNotEqualTo(productDiscountDTO2);
        productDiscountDTO2.setId(productDiscountDTO1.getId());
        assertThat(productDiscountDTO1).isEqualTo(productDiscountDTO2);
        productDiscountDTO2.setId(2L);
        assertThat(productDiscountDTO1).isNotEqualTo(productDiscountDTO2);
        productDiscountDTO1.setId(null);
        assertThat(productDiscountDTO1).isNotEqualTo(productDiscountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(productDiscountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(productDiscountMapper.fromId(null)).isNull();
    }
}
