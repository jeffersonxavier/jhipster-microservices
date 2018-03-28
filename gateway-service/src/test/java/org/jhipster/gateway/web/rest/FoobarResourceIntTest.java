package org.jhipster.gateway.web.rest;

import org.jhipster.gateway.GatewayServiceApp;
import org.jhipster.gateway.client.BarClient;
import org.jhipster.gateway.client.FooClient;
import org.jhipster.gateway.client.RandomClient;
import org.jhipster.gateway.domain.Foobar;
import org.jhipster.gateway.repository.FoobarRepository;
import org.jhipster.gateway.service.FoobarService;
import org.jhipster.gateway.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jhipster.gateway.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FoobarResource REST controller.
 *
 * @see FoobarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayServiceApp.class)
public class FoobarResourceIntTest {

    private static final String DEFAULT_FOOBAR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FOOBAR_NAME = "BBBBBBBBBB";

    @Autowired
    private FoobarRepository foobarRepository;

    @Autowired
    private FoobarService foobarService;

    @Autowired
    private BarClient barClient;

    @Autowired
    private FooClient fooClient;

    @Autowired
    private RandomClient randomClient;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFoobarMockMvc;

    private Foobar foobar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FoobarResource foobarResource = new FoobarResource(foobarService, barClient, fooClient, randomClient);
        this.restFoobarMockMvc = MockMvcBuilders.standaloneSetup(foobarResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Foobar createEntity(EntityManager em) {
        Foobar foobar = new Foobar()
            .foobarName(DEFAULT_FOOBAR_NAME);
        return foobar;
    }

    @Before
    public void initTest() {
        foobar = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoobar() throws Exception {
        int databaseSizeBeforeCreate = foobarRepository.findAll().size();

        // Create the Foobar
        restFoobarMockMvc.perform(post("/api/foobars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foobar)))
            .andExpect(status().isCreated());

        // Validate the Foobar in the database
        List<Foobar> foobarList = foobarRepository.findAll();
        assertThat(foobarList).hasSize(databaseSizeBeforeCreate + 1);
        Foobar testFoobar = foobarList.get(foobarList.size() - 1);
        assertThat(testFoobar.getFoobarName()).isEqualTo(DEFAULT_FOOBAR_NAME);
    }

    @Test
    @Transactional
    public void createFoobarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foobarRepository.findAll().size();

        // Create the Foobar with an existing ID
        foobar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoobarMockMvc.perform(post("/api/foobars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foobar)))
            .andExpect(status().isBadRequest());

        // Validate the Foobar in the database
        List<Foobar> foobarList = foobarRepository.findAll();
        assertThat(foobarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFoobars() throws Exception {
        // Initialize the database
        foobarRepository.saveAndFlush(foobar);

        // Get all the foobarList
        restFoobarMockMvc.perform(get("/api/foobars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foobar.getId().intValue())))
            .andExpect(jsonPath("$.[*].foobarName").value(hasItem(DEFAULT_FOOBAR_NAME.toString())));
    }

    @Test
    @Transactional
    public void getFoobar() throws Exception {
        // Initialize the database
        foobarRepository.saveAndFlush(foobar);

        // Get the foobar
        restFoobarMockMvc.perform(get("/api/foobars/{id}", foobar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(foobar.getId().intValue()))
            .andExpect(jsonPath("$.foobarName").value(DEFAULT_FOOBAR_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFoobar() throws Exception {
        // Get the foobar
        restFoobarMockMvc.perform(get("/api/foobars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoobar() throws Exception {
        // Initialize the database
        foobarService.save(foobar);

        int databaseSizeBeforeUpdate = foobarRepository.findAll().size();

        // Update the foobar
        Foobar updatedFoobar = foobarRepository.findOne(foobar.getId());
        // Disconnect from session so that the updates on updatedFoobar are not directly saved in db
        em.detach(updatedFoobar);
        updatedFoobar
            .foobarName(UPDATED_FOOBAR_NAME);

        restFoobarMockMvc.perform(put("/api/foobars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFoobar)))
            .andExpect(status().isOk());

        // Validate the Foobar in the database
        List<Foobar> foobarList = foobarRepository.findAll();
        assertThat(foobarList).hasSize(databaseSizeBeforeUpdate);
        Foobar testFoobar = foobarList.get(foobarList.size() - 1);
        assertThat(testFoobar.getFoobarName()).isEqualTo(UPDATED_FOOBAR_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFoobar() throws Exception {
        int databaseSizeBeforeUpdate = foobarRepository.findAll().size();

        // Create the Foobar

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFoobarMockMvc.perform(put("/api/foobars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(foobar)))
            .andExpect(status().isCreated());

        // Validate the Foobar in the database
        List<Foobar> foobarList = foobarRepository.findAll();
        assertThat(foobarList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFoobar() throws Exception {
        // Initialize the database
        foobarService.save(foobar);

        int databaseSizeBeforeDelete = foobarRepository.findAll().size();

        // Get the foobar
        restFoobarMockMvc.perform(delete("/api/foobars/{id}", foobar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Foobar> foobarList = foobarRepository.findAll();
        assertThat(foobarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Foobar.class);
        Foobar foobar1 = new Foobar();
        foobar1.setId(1L);
        Foobar foobar2 = new Foobar();
        foobar2.setId(foobar1.getId());
        assertThat(foobar1).isEqualTo(foobar2);
        foobar2.setId(2L);
        assertThat(foobar1).isNotEqualTo(foobar2);
        foobar1.setId(null);
        assertThat(foobar1).isNotEqualTo(foobar2);
    }
}
