package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.payloads.CartDTO;
import com.app.services.CartService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerDiffblueTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartService cartService;

    /**
     * Method under test:  {@link CartController#addProductToCart(Long, Long, Integer)}
     */
    @Test
    void testAddProductToCart() throws Exception {
        when(cartService.addProductToCart(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<Integer>any()))
                .thenReturn(new CartDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/public/carts/{cartId}/products/{productId}/quantity/{quantity}", 1L, 1L, 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"cartId\":null,\"totalPrice\":0.0,\"products\":[]}"));
    }

    /**
     * Method under test:  {@link CartController#deleteProductFromCart(Long, Long)}
     */
    @Test
    void testDeleteProductFromCart() throws Exception {
        when(cartService.deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn("jane.doe@example.org");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/public/carts/{cartId}/product/{productId}", 1L, 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("jane.doe@example.org"));
    }

    /**
     * Method under test:  {@link CartController#getCartById(String, Long)}
     */
    @Test
    void testGetCartById() throws Exception {
        when(cartService.getCart(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(new CartDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/public/users/{emailId}/carts/{cartId}", "42", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"cartId\":null,\"totalPrice\":0.0,\"products\":[]}"));
    }

    /**
     * Method under test:  {@link CartController#getCarts()}
     */
    @Test
    void testGetCarts() throws Exception {
        when(cartService.getAllCarts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/carts");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link CartController#updateCartProduct(Long, Long, Integer)}
     */
    @Test
    void testUpdateCartProduct() throws Exception {
        when(cartService.updateProductQuantityInCart(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<Integer>any()))
                .thenReturn(new CartDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/public/carts/{cartId}/products/{productId}/quantity/{quantity}", 1L, 1L, 1);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"cartId\":null,\"totalPrice\":0.0,\"products\":[]}"));
    }
}
