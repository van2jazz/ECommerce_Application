package com.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Category;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Payment;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.OrderDTO;
import com.app.payloads.OrderResponse;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.OrderItemRepo;
import com.app.repositories.OrderRepo;
import com.app.repositories.PaymentRepo;
import com.app.repositories.UserRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplDiffblueTest {
    @MockBean
    private CartItemRepo cartItemRepo;

    @MockBean
    private CartRepo cartRepo;

    @MockBean
    private CartService cartService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderItemRepo orderItemRepo;

    @MockBean
    private OrderRepo orderRepo;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private PaymentRepo paymentRepo;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(String, Long, String)}
     */
    @Test
    void testPlaceOrder() {
        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(10.0d);
        cart.setUser(new User());

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCart(cart);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setMobileNumber("42");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setCartId(1L);
        cart2.setCartItems(new ArrayList<>());
        cart2.setTotalPrice(10.0d);
        cart2.setUser(user);

        User user2 = new User();
        user2.setAddresses(new ArrayList<>());
        user2.setCart(cart2);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setMobileNumber("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUserId(1L);

        Cart cart3 = new Cart();
        cart3.setCartId(1L);
        cart3.setCartItems(new ArrayList<>());
        cart3.setTotalPrice(10.0d);
        cart3.setUser(user2);
        when(cartRepo.findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(cart3);

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.save(Mockito.<Order>any())).thenReturn(order3);

        Payment payment3 = new Payment();
        payment3.setOrder(new Order());
        payment3.setPaymentId(1L);
        payment3.setPaymentMethod("Payment Method");

        Order order4 = new Order();
        order4.setEmail("jane.doe@example.org");
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderId(1L);
        order4.setOrderItems(new ArrayList<>());
        order4.setOrderStatus("Order Status");
        order4.setPayment(payment3);
        order4.setTotalAmount(10.0d);

        Payment payment4 = new Payment();
        payment4.setOrder(order4);
        payment4.setPaymentId(1L);
        payment4.setPaymentMethod("Payment Method");

        Order order5 = new Order();
        order5.setEmail("jane.doe@example.org");
        order5.setOrderDate(LocalDate.of(1970, 1, 1));
        order5.setOrderId(1L);
        order5.setOrderItems(new ArrayList<>());
        order5.setOrderStatus("Order Status");
        order5.setPayment(payment4);
        order5.setTotalAmount(10.0d);

        Payment payment5 = new Payment();
        payment5.setOrder(order5);
        payment5.setPaymentId(1L);
        payment5.setPaymentMethod("Payment Method");
        when(paymentRepo.save(Mockito.<Payment>any())).thenReturn(payment5);
        assertThrows(APIException.class, () -> orderServiceImpl.placeOrder("42", 1L, "Payment Method"));
        verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
        verify(orderRepo).save(Mockito.<Order>any());
        verify(paymentRepo).save(Mockito.<Payment>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(String, Long, String)}
     */
    @Test
    void testPlaceOrder2() {
        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(10.0d);
        cart.setUser(new User());

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCart(cart);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setMobileNumber("42");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setCartId(1L);
        cart2.setCartItems(new ArrayList<>());
        cart2.setTotalPrice(10.0d);
        cart2.setUser(user);

        User user2 = new User();
        user2.setAddresses(new ArrayList<>());
        user2.setCart(cart2);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setMobileNumber("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUserId(1L);

        Cart cart3 = new Cart();
        cart3.setCartId(1L);
        cart3.setCartItems(new ArrayList<>());
        cart3.setTotalPrice(10.0d);
        cart3.setUser(user2);
        when(cartRepo.findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(cart3);

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.save(Mockito.<Order>any())).thenReturn(order3);
        when(paymentRepo.save(Mockito.<Payment>any()))
                .thenThrow(new ResourceNotFoundException("Order Accepted !", "Order Accepted !", "Order Accepted !"));
        assertThrows(ResourceNotFoundException.class, () -> orderServiceImpl.placeOrder("42", 1L, "Payment Method"));
        verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
        verify(paymentRepo).save(Mockito.<Payment>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(String, Long, String)}
     */
    @Test
    void testPlaceOrder3() {
        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(10.0d);
        cart.setUser(new User());

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCart(cart);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setMobileNumber("42");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setCartId(1L);
        cart2.setCartItems(new ArrayList<>());
        cart2.setTotalPrice(10.0d);
        cart2.setUser(user);

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Order Accepted !");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(10.0d);
        product.setImage("Order Accepted !");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(10.0d);
        product.setProductId(1L);
        product.setProductName("Order Accepted !");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(10.0d);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart2);
        cartItem.setCartItemId(1L);
        cartItem.setDiscount(10.0d);
        cartItem.setProduct(product);
        cartItem.setProductPrice(10.0d);
        cartItem.setQuantity(1);

        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart cart3 = new Cart();
        cart3.setCartId(1L);
        cart3.setCartItems(new ArrayList<>());
        cart3.setTotalPrice(10.0d);
        cart3.setUser(new User());

        User user2 = new User();
        user2.setAddresses(new ArrayList<>());
        user2.setCart(cart3);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setMobileNumber("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUserId(1L);

        Cart cart4 = new Cart();
        cart4.setCartId(1L);
        cart4.setCartItems(new ArrayList<>());
        cart4.setTotalPrice(10.0d);
        cart4.setUser(user2);

        User user3 = new User();
        user3.setAddresses(new ArrayList<>());
        user3.setCart(cart4);
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setMobileNumber("42");
        user3.setPassword("iloveyou");
        user3.setRoles(new HashSet<>());
        user3.setUserId(1L);

        Cart cart5 = new Cart();
        cart5.setCartId(1L);
        cart5.setCartItems(cartItems);
        cart5.setTotalPrice(10.0d);
        cart5.setUser(user3);
        when(cartRepo.findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(cart5);
        when(cartService.deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn("jane.doe@example.org");
        OrderDTO orderDTO = new OrderDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(orderDTO);
        when(orderItemRepo.saveAll(Mockito.<Iterable<OrderItem>>any())).thenReturn(new ArrayList<>());

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.save(Mockito.<Order>any())).thenReturn(order3);

        Payment payment3 = new Payment();
        payment3.setOrder(new Order());
        payment3.setPaymentId(1L);
        payment3.setPaymentMethod("Payment Method");

        Order order4 = new Order();
        order4.setEmail("jane.doe@example.org");
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderId(1L);
        order4.setOrderItems(new ArrayList<>());
        order4.setOrderStatus("Order Status");
        order4.setPayment(payment3);
        order4.setTotalAmount(10.0d);

        Payment payment4 = new Payment();
        payment4.setOrder(order4);
        payment4.setPaymentId(1L);
        payment4.setPaymentMethod("Payment Method");

        Order order5 = new Order();
        order5.setEmail("jane.doe@example.org");
        order5.setOrderDate(LocalDate.of(1970, 1, 1));
        order5.setOrderId(1L);
        order5.setOrderItems(new ArrayList<>());
        order5.setOrderStatus("Order Status");
        order5.setPayment(payment4);
        order5.setTotalAmount(10.0d);

        Payment payment5 = new Payment();
        payment5.setOrder(order5);
        payment5.setPaymentId(1L);
        payment5.setPaymentMethod("Payment Method");
        when(paymentRepo.save(Mockito.<Payment>any())).thenReturn(payment5);
        OrderDTO actualPlaceOrderResult = orderServiceImpl.placeOrder("42", 1L, "Payment Method");
        verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
        verify(cartService).deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        verify(orderRepo).save(Mockito.<Order>any());
        verify(paymentRepo).save(Mockito.<Payment>any());
        verify(orderItemRepo).saveAll(Mockito.<Iterable<OrderItem>>any());
        assertSame(orderDTO, actualPlaceOrderResult);
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(String, Long, String)}
     */
    @Test
    void testPlaceOrder4() {
        Cart cart = new Cart();
        cart.setCartId(1L);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(10.0d);
        cart.setUser(new User());

        User user = new User();
        user.setAddresses(new ArrayList<>());
        user.setCart(cart);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setMobileNumber("42");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUserId(1L);

        Cart cart2 = new Cart();
        cart2.setCartId(1L);
        cart2.setCartItems(new ArrayList<>());
        cart2.setTotalPrice(10.0d);
        cart2.setUser(user);

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Order Accepted !");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(10.0d);
        product.setImage("Order Accepted !");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(10.0d);
        product.setProductId(1L);
        product.setProductName("Order Accepted !");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(10.0d);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart2);
        cartItem.setCartItemId(1L);
        cartItem.setDiscount(10.0d);
        cartItem.setProduct(product);
        cartItem.setProductPrice(10.0d);
        cartItem.setQuantity(1);

        ArrayList<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart cart3 = new Cart();
        cart3.setCartId(1L);
        cart3.setCartItems(new ArrayList<>());
        cart3.setTotalPrice(10.0d);
        cart3.setUser(new User());

        User user2 = new User();
        user2.setAddresses(new ArrayList<>());
        user2.setCart(cart3);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setMobileNumber("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUserId(1L);

        Cart cart4 = new Cart();
        cart4.setCartId(1L);
        cart4.setCartItems(new ArrayList<>());
        cart4.setTotalPrice(10.0d);
        cart4.setUser(user2);

        User user3 = new User();
        user3.setAddresses(new ArrayList<>());
        user3.setCart(cart4);
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setMobileNumber("42");
        user3.setPassword("iloveyou");
        user3.setRoles(new HashSet<>());
        user3.setUserId(1L);

        Cart cart5 = new Cart();
        cart5.setCartId(1L);
        cart5.setCartItems(cartItems);
        cart5.setTotalPrice(10.0d);
        cart5.setUser(user3);
        when(cartRepo.findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(cart5);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(new OrderDTO());
        when(orderItemRepo.saveAll(Mockito.<Iterable<OrderItem>>any()))
                .thenThrow(new ResourceNotFoundException("Order Accepted !", "Order Accepted !", "Order Accepted !"));

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.save(Mockito.<Order>any())).thenReturn(order3);

        Payment payment3 = new Payment();
        payment3.setOrder(new Order());
        payment3.setPaymentId(1L);
        payment3.setPaymentMethod("Payment Method");

        Order order4 = new Order();
        order4.setEmail("jane.doe@example.org");
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderId(1L);
        order4.setOrderItems(new ArrayList<>());
        order4.setOrderStatus("Order Status");
        order4.setPayment(payment3);
        order4.setTotalAmount(10.0d);

        Payment payment4 = new Payment();
        payment4.setOrder(order4);
        payment4.setPaymentId(1L);
        payment4.setPaymentMethod("Payment Method");

        Order order5 = new Order();
        order5.setEmail("jane.doe@example.org");
        order5.setOrderDate(LocalDate.of(1970, 1, 1));
        order5.setOrderId(1L);
        order5.setOrderItems(new ArrayList<>());
        order5.setOrderStatus("Order Status");
        order5.setPayment(payment4);
        order5.setTotalAmount(10.0d);

        Payment payment5 = new Payment();
        payment5.setOrder(order5);
        payment5.setPaymentId(1L);
        payment5.setPaymentMethod("Payment Method");
        when(paymentRepo.save(Mockito.<Payment>any())).thenReturn(payment5);
        assertThrows(ResourceNotFoundException.class, () -> orderServiceImpl.placeOrder("42", 1L, "Payment Method"));
        verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(orderRepo).save(Mockito.<Order>any());
        verify(paymentRepo).save(Mockito.<Payment>any());
        verify(orderItemRepo).saveAll(Mockito.<Iterable<OrderItem>>any());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getOrdersByUser(String)}
     */
    @Test
    void testGetOrdersByUser() {
        when(orderRepo.findAllByEmail(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertThrows(APIException.class, () -> orderServiceImpl.getOrdersByUser("42"));
        verify(orderRepo).findAllByEmail(Mockito.<String>any());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getOrdersByUser(String)}
     */
    @Test
    void testGetOrdersByUser2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(new OrderDTO());

        Payment payment = new Payment();
        payment.setOrder(new Order());
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(payment);
        order.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment2);
        order2.setTotalAmount(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        when(orderRepo.findAllByEmail(Mockito.<String>any())).thenReturn(orderList);
        List<OrderDTO> actualOrdersByUser = orderServiceImpl.getOrdersByUser("42");
        verify(orderRepo).findAllByEmail(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        assertEquals(1, actualOrdersByUser.size());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getOrdersByUser(String)}
     */
    @Test
    void testGetOrdersByUser3() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(new OrderDTO());

        Payment payment = new Payment();
        payment.setOrder(new Order());
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(payment);
        order.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment2);
        order2.setTotalAmount(10.0d);

        Payment payment3 = new Payment();
        payment3.setOrder(new Order());
        payment3.setPaymentId(2L);
        payment3.setPaymentMethod("com.app.entities.Payment");

        Order order3 = new Order();
        order3.setEmail("john.smith@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(2L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("com.app.entities.Order");
        order3.setPayment(payment3);
        order3.setTotalAmount(0.5d);

        Payment payment4 = new Payment();
        payment4.setOrder(order3);
        payment4.setPaymentId(2L);
        payment4.setPaymentMethod("com.app.entities.Payment");

        Order order4 = new Order();
        order4.setEmail("john.smith@example.org");
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderId(2L);
        order4.setOrderItems(new ArrayList<>());
        order4.setOrderStatus("com.app.entities.Order");
        order4.setPayment(payment4);
        order4.setTotalAmount(0.5d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order4);
        orderList.add(order2);
        when(orderRepo.findAllByEmail(Mockito.<String>any())).thenReturn(orderList);
        List<OrderDTO> actualOrdersByUser = orderServiceImpl.getOrdersByUser("42");
        verify(orderRepo).findAllByEmail(Mockito.<String>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        assertEquals(2, actualOrdersByUser.size());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getOrdersByUser(String)}
     */
    @Test
    void testGetOrdersByUser4() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field", "Field Name"));

        Payment payment = new Payment();
        payment.setOrder(new Order());
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(payment);
        order.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment2);
        order2.setTotalAmount(10.0d);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order2);
        when(orderRepo.findAllByEmail(Mockito.<String>any())).thenReturn(orderList);
        assertThrows(ResourceNotFoundException.class, () -> orderServiceImpl.getOrdersByUser("42"));
        verify(orderRepo).findAllByEmail(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#getOrder(String, Long)}
     */
    @Test
    void testGetOrder() {
        OrderDTO orderDTO = new OrderDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(orderDTO);

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(order3);
        OrderDTO actualOrder = orderServiceImpl.getOrder("42", 1L);
        verify(orderRepo).findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        assertSame(orderDTO, actualOrder);
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getOrder(String, Long)}
     */
    @Test
    void testGetOrder2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field", "Field Name"));

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(order3);
        assertThrows(ResourceNotFoundException.class, () -> orderServiceImpl.getOrder("42", 1L));
        verify(orderRepo).findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getAllOrders(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllOrders() {
        when(orderRepo.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertThrows(APIException.class, () -> orderServiceImpl.getAllOrders(10, 3, "Sort By", "asc"));
        verify(orderRepo).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getAllOrders(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllOrders2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(new OrderDTO());

        Payment payment = new Payment();
        payment.setOrder(new Order());
        payment.setPaymentId(1L);
        payment.setPaymentMethod("asc");

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("asc");
        order.setPayment(payment);
        order.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("asc");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("asc");
        order2.setPayment(payment2);
        order2.setTotalAmount(10.0d);

        ArrayList<Order> content = new ArrayList<>();
        content.add(order2);
        PageImpl<Order> pageImpl = new PageImpl<>(content);
        when(orderRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        OrderResponse actualAllOrders = orderServiceImpl.getAllOrders(10, 3, "Sort By", "asc");
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        verify(orderRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllOrders.getPageNumber().intValue());
        assertEquals(1, actualAllOrders.getPageSize().intValue());
        assertEquals(1, actualAllOrders.getTotalPages().intValue());
        assertEquals(1, actualAllOrders.getContent().size());
        assertEquals(1L, actualAllOrders.getTotalElements().longValue());
        assertTrue(actualAllOrders.isLastPage());
    }

    /**
     * Method under test:  {@link OrderServiceImpl#getAllOrders(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllOrders3() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(new OrderDTO());

        Payment payment = new Payment();
        payment.setOrder(new Order());
        payment.setPaymentId(1L);
        payment.setPaymentMethod("asc");

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("asc");
        order.setPayment(payment);
        order.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("asc");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("asc");
        order2.setPayment(payment2);
        order2.setTotalAmount(10.0d);

        Payment payment3 = new Payment();
        payment3.setOrder(new Order());
        payment3.setPaymentId(2L);
        payment3.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("john.smith@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(2L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment3);
        order3.setTotalAmount(0.5d);

        Payment payment4 = new Payment();
        payment4.setOrder(order3);
        payment4.setPaymentId(2L);
        payment4.setPaymentMethod("Payment Method");

        Order order4 = new Order();
        order4.setEmail("john.smith@example.org");
        order4.setOrderDate(LocalDate.of(1970, 1, 1));
        order4.setOrderId(2L);
        order4.setOrderItems(new ArrayList<>());
        order4.setOrderStatus("Order Status");
        order4.setPayment(payment4);
        order4.setTotalAmount(0.5d);

        ArrayList<Order> content = new ArrayList<>();
        content.add(order4);
        content.add(order2);
        PageImpl<Order> pageImpl = new PageImpl<>(content);
        when(orderRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        OrderResponse actualAllOrders = orderServiceImpl.getAllOrders(10, 3, "Sort By", "asc");
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        verify(orderRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllOrders.getPageNumber().intValue());
        assertEquals(1, actualAllOrders.getTotalPages().intValue());
        assertEquals(2, actualAllOrders.getPageSize().intValue());
        assertEquals(2, actualAllOrders.getContent().size());
        assertEquals(2L, actualAllOrders.getTotalElements().longValue());
        assertTrue(actualAllOrders.isLastPage());
    }

    /**
     * Method under test: {@link OrderServiceImpl#updateOrder(String, Long, String)}
     */
    @Test
    void testUpdateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any())).thenReturn(orderDTO);

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(order3);
        OrderDTO actualUpdateOrderResult = orderServiceImpl.updateOrder("42", 1L, "Order Status");
        verify(orderRepo).findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
        assertSame(orderDTO, actualUpdateOrderResult);
    }

    /**
     * Method under test:  {@link OrderServiceImpl#updateOrder(String, Long, String)}
     */
    @Test
    void testUpdateOrder2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field", "Field Name"));

        Order order = new Order();
        order.setEmail("jane.doe@example.org");
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus("Order Status");
        order.setPayment(new Payment());
        order.setTotalAmount(10.0d);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Payment Method");

        Order order2 = new Order();
        order2.setEmail("jane.doe@example.org");
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderId(1L);
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus("Order Status");
        order2.setPayment(payment);
        order2.setTotalAmount(10.0d);

        Payment payment2 = new Payment();
        payment2.setOrder(order2);
        payment2.setPaymentId(1L);
        payment2.setPaymentMethod("Payment Method");

        Order order3 = new Order();
        order3.setEmail("jane.doe@example.org");
        order3.setOrderDate(LocalDate.of(1970, 1, 1));
        order3.setOrderId(1L);
        order3.setOrderItems(new ArrayList<>());
        order3.setOrderStatus("Order Status");
        order3.setPayment(payment2);
        order3.setTotalAmount(10.0d);
        when(orderRepo.findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(order3);
        assertThrows(ResourceNotFoundException.class, () -> orderServiceImpl.updateOrder("42", 1L, "Order Status"));
        verify(orderRepo).findOrderByEmailAndOrderId(Mockito.<String>any(), Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<OrderDTO>>any());
    }
}
