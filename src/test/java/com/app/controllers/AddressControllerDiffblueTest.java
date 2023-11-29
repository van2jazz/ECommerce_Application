package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.entities.Address;
import com.app.payloads.AddressDTO;
import com.app.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
class AddressControllerDiffblueTest {
    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    /**
     * Method under test:  {@link AddressController#createAddress(AddressDTO)}
     */
    @Test
    void testCreateAddress() throws Exception {
        when(addressService.createAddress(Mockito.<AddressDTO>any())).thenReturn(new AddressDTO());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setBuildingName("Building Name");
        addressDTO.setCity("Oxford");
        addressDTO.setCountry("GB");
        addressDTO.setPincode("Pincode");
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        String content = (new ObjectMapper()).writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"addressId\":null,\"street\":null,\"buildingName\":null,\"city\":null,\"state\":null,\"country\":null,\"pincode"
                                        + "\":null}"));
    }

    /**
     * Method under test:  {@link AddressController#getAddresses()}
     */
    @Test
    void testGetAddresses() throws Exception {
        when(addressService.getAddresses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/addresses");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link AddressController#getAddress(Long)}
     */
    @Test
    void testGetAddress() throws Exception {
        when(addressService.getAddress(Mockito.<Long>any())).thenReturn(new AddressDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/addresses/{addressId}", 1L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"addressId\":null,\"street\":null,\"buildingName\":null,\"city\":null,\"state\":null,\"country\":null,\"pincode"
                                        + "\":null}"));
    }

    /**
     * Method under test:  {@link AddressController#getAddress(Long)}
     */
    @Test
    void testGetAddress2() throws Exception {
        when(addressService.getAddresses()).thenReturn(new ArrayList<>());
        when(addressService.getAddress(Mockito.<Long>any())).thenReturn(new AddressDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/addresses/{addressId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link AddressController#updateAddress(Long, Address)}
     */
    @Test
    void testUpdateAddress() throws Exception {
        when(addressService.updateAddress(Mockito.<Long>any(), Mockito.<Address>any())).thenReturn(new AddressDTO());

        Address address = new Address();
        address.setAddressId(1L);
        address.setBuildingName("Building Name");
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setPincode("Pincode");
        address.setState("MD");
        address.setStreet("Street");
        address.setUsers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/admin/addresses/{addressId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"addressId\":null,\"street\":null,\"buildingName\":null,\"city\":null,\"state\":null,\"country\":null,\"pincode"
                                        + "\":null}"));
    }

    /**
     * Method under test:  {@link AddressController#deleteAddress(Long)}
     */
    @Test
    void testDeleteAddress() throws Exception {
        when(addressService.deleteAddress(Mockito.<Long>any())).thenReturn("42 Main St");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/admin/addresses/{addressId}",
                1L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42 Main St"));
    }
}
