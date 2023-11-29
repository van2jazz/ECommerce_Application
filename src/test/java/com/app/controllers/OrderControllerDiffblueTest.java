package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.payloads.OrderDTO;
import com.app.payloads.OrderResponse;
import com.app.services.OrderService;

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

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerDiffblueTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test:  {@link OrderController#getAllOrders(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new OrderResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/orders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":null,\"pageNumber\":null,\"pageSize\":null,\"totalElements\":null,\"totalPages\":null,\"lastPage"
                                        + "\":false}"));
    }

    /**
     * Method under test:  {@link OrderController#getOrdersByUser(String)}
     */
    @Test
    void testGetOrdersByUser() throws Exception {
        when(orderService.getOrdersByUser(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/public/users/{emailId}/orders",
                "42");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link OrderController#getOrderByUser(String, Long)}
     */
    @Test
    void testGetOrderByUser() throws Exception {
        when(orderService.getOrder(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(new OrderDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/public/users/{emailId}/orders/{orderId}", "42", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"orderId\":null,\"email\":null,\"orderItems\":[],\"orderDate\":null,\"payment\":null,\"totalAmount\":null,"
                                        + "\"orderStatus\":null}"));
    }

    /**
     * Method under test:  {@link OrderController#getOrderByUser(String, Long)}
     */
    @Test
    void testGetOrderByUser2() throws Exception {
        when(orderService.getOrdersByUser(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(orderService.getOrder(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(new OrderDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/public/users/{emailId}/orders/{orderId}", "Uri Variables", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link OrderController#orderProducts(String, Long, String)}
     */
    @Test
    void testOrderProducts() throws Exception {
        when(orderService.placeOrder(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<String>any()))
                .thenReturn(new OrderDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/public/users/{emailId}/carts/{cartId}/payments/{paymentMethod}/order", "42", 1L, "Payment Method");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"orderId\":null,\"email\":null,\"orderItems\":[],\"orderDate\":null,\"payment\":null,\"totalAmount\":null,"
                                        + "\"orderStatus\":null}"));
    }

    /**
     * Method under test:  {@link OrderController#updateOrderByUser(String, Long, String)}
     */
    @Test
    void testUpdateOrderByUser() throws Exception {
        when(orderService.updateOrder(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<String>any()))
                .thenReturn(new OrderDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/admin/users/{emailId}/orders/{orderId}/orderStatus/{orderStatus}", "42", 1L, "Order Status");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"orderId\":null,\"email\":null,\"orderItems\":[],\"orderDate\":null,\"payment\":null,\"totalAmount\":null,"
                                        + "\"orderStatus\":null}"));
    }
}
