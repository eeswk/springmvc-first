package ee.swan.controller;

import ee.swan.config.AppConfig;
import ee.swan.config.TestConfig;
import ee.swan.config.TransactionManagerConfig;
import ee.swan.config.WebMvcConfig;
import ee.swan.service.MessageService;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {AppConfig.class, TestConfig.class}),
        @ContextConfiguration(classes = WebMvcConfig.class)
})
@WebAppConfiguration
public class WelcomeControllerTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;


    @Before
    public void setupMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                //서블릿 필터 추가
            .addFilter(new CharacterEncodingFilter("UTF-8")).build();
        MockitoAnnotations.initMocks(this);
        //this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/index.jsp"));
    }

    @Test
    public void testBooks() throws Exception {
        mockMvc.perform(get("/books")
                .param("name", "Spring")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Track-Id", UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testBooksContent() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(
                                //containsString("[{\"bookId\":\"9791158390747\",\"name\":\"슬랙으로 협업하기\"}]"))
                                containsString("9791158390747")));
    }






}