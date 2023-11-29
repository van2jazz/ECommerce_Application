package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.payloads.AddressDTO;
import com.app.payloads.CartDTO;
import com.app.payloads.LoginCredentials;
import com.app.payloads.UserDTO;
import com.app.security.JWTUtil;
import com.app.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTUtil jWTUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    /**
     * Method under test:  {@link AuthController#loginHandler(LoginCredentials)}
     */
    @Test
    void testLoginHandler() throws Exception {
        when(jWTUtil.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setEmail("jane.doe@example.org");
        loginCredentials.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginCredentials);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"jwt-token\":\"ABC123\"}"));
    }

    /**
     * Method under test:  {@link AuthController#registerHandler(UserDTO)}
     */
    @Test
    void testRegisterHandler() throws Exception {
        when(jWTUtil.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(userService.registerUser(Mockito.<UserDTO>any())).thenReturn(new UserDTO());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(new AddressDTO());
        userDTO.setCart(new CartDTO());
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFirstName("Jane");
        userDTO.setLastName("Doe");
        userDTO.setMobileNumber("42");
        userDTO.setPassword("iloveyou");
        userDTO.setRoles(new HashSet<>());
        userDTO.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(userDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"jwt-token\":\"ABC123\"}"));
    }
}
