package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.payloads.AddressDTO;
import com.app.payloads.CartDTO;
import com.app.payloads.UserDTO;
import com.app.payloads.UserResponse;
import com.app.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test:  {@link UserController#updateUser(UserDTO, Long)}
     */
    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(Mockito.<Long>any(), Mockito.<UserDTO>any())).thenReturn(new UserDTO());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/public/users/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"mobileNumber\":null,\"email\":null,\"password\":null,\"roles"
                                        + "\":[],\"address\":null,\"cart\":null}"));
    }

    /**
     * Method under test:  {@link UserController#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() throws Exception {
        when(userService.deleteUser(Mockito.<Long>any())).thenReturn("Delete User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/admin/users/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete User"));
    }

    /**
     * Method under test:  {@link UserController#getUser(Long)}
     */
    @Test
    void testGetUser() throws Exception {
        when(userService.getUserById(Mockito.<Long>any())).thenReturn(new UserDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/public/users/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":null,\"firstName\":null,\"lastName\":null,\"mobileNumber\":null,\"email\":null,\"password\":null,\"roles"
                                        + "\":[],\"address\":null,\"cart\":null}"));
    }

    /**
     * Method under test:  {@link UserController#getUsers(Integer, Integer, String, String)}
     */
    @Test
    void testGetUsers() throws Exception {
        when(userService.getAllUsers(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new UserResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/users");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":null,\"pageNumber\":null,\"pageSize\":null,\"totalElements\":null,\"totalPages\":null,\"lastPage"
                                        + "\":false}"));
    }
}
