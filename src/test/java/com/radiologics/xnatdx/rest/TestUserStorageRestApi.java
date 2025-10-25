package com.radiologics.morpheus.rest;



import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.nrg.xdat.security.services.RoleHolder;
import org.nrg.xdat.security.services.UserManagementServiceI;
import org.nrg.xft.security.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.radiologics.morpheus.config.RestConfig;
import com.radiologics.morpheus.service.UserStorageService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RestConfig.class)
public class TestUserStorageRestApi {
    private MockMvc mockMvc;
    private UserI mockAdmin;
    private Authentication authentication;
    private final String PATH = "/storage/users";

    @Autowired private UserStorageService userStorageService;
    
    @Autowired private RoleHolder roleHolder;
    @Autowired private WebApplicationContext wac;
    @Autowired private UserManagementServiceI mockUserManagementServiceI;

    private final MediaType JSON = MediaType.APPLICATION_JSON_UTF8;

    @Before
    public void setup() throws Exception {
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	
    	 // Mock the userI
        final String username = "admin";
        final String password = "admin";
        mockAdmin = Mockito.mock(UserI.class);
        when(mockAdmin.getLogin()).thenReturn(username);
        when(mockAdmin.getPassword()).thenReturn(password);

        authentication = new TestingAuthenticationToken(mockAdmin, password);

        // Mock the user management service
        when(mockUserManagementServiceI.getUser(username)).thenReturn(mockAdmin);
        
        
        
        
    }

    @Test
    public void testUpdateStorage() throws Exception {
    	String json = "{\"key1\":\"value1\", \"key1\":\"value\"}";
//todo
    	 //when(userStorageService.update(mockAdmin.getUsername(), json));
    	
    	
        final MockHttpServletRequestBuilder request =
                post(PATH).contentType(JSON)
                        .content(json)
                        .with(authentication(authentication))
                        .with(csrf())
                        .with(testSecurityContext());

        
        //String string=mockMvc.perform(request).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
       
     
      
    }

    @Test
    public void testSetServer() throws Exception {

       
       
    }

    
}
