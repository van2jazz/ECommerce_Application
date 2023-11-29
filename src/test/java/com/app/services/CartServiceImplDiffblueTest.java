package com.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exceptions.APIException;
import com.app.payloads.CartDTO;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.ProductRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CartServiceImplDiffblueTest {
  @MockBean
  private CartItemRepo cartItemRepo;

  @MockBean
  private CartRepo cartRepo;

  @Autowired
  private CartServiceImpl cartServiceImpl;

  @MockBean
  private ModelMapper modelMapper;

  @MockBean
  private ProductRepo productRepo;

  /**
   * Method under test:
   * {@link CartServiceImpl#addProductToCart(Long, Long, Integer)}
   */
  @Test
  void testAddProductToCart() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);
    Optional<Cart> ofResult = Optional.of(cart4);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Category category2 = new Category();
    category2.setCategoryId(1L);
    category2.setCategoryName("Category Name");
    category2.setProducts(new ArrayList<>());

    Product product2 = new Product();
    product2.setCategory(category2);
    product2.setDescription("The characteristics of someone or something");
    product2.setDiscount(10.0d);
    product2.setImage("Image");
    product2.setOrderItems(new ArrayList<>());
    product2.setPrice(10.0d);
    product2.setProductId(1L);
    product2.setProductName("Product Name");
    product2.setProducts(new ArrayList<>());
    product2.setQuantity(1);
    product2.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product2);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    assertThrows(APIException.class, () -> cartServiceImpl.addProductToCart(1L, 1L, 2));
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:  {@link CartServiceImpl#addProductToCart(Long, Long, Integer)}
   */
  @Test
  void testAddProductToCart2() {
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any()))
            .thenThrow(new APIException("An error occurred"));

    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);
    Optional<Cart> ofResult = Optional.of(cart2);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
    product.setProducts(new ArrayList<>());
    product.setQuantity(1);
    product.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    assertThrows(APIException.class, () -> cartServiceImpl.addProductToCart(1L, 1L, 2));
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:  {@link CartServiceImpl#getAllCarts()}
   */
  @Test
  void testGetAllCarts() {
    when(cartRepo.findAll()).thenReturn(new ArrayList<>());
    assertThrows(APIException.class, () -> cartServiceImpl.getAllCarts());
    verify(cartRepo).findAll();
  }

  /**
   * Method under test: {@link CartServiceImpl#getAllCarts()}
   */
  @Test
  void testGetAllCarts2() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    ArrayList<CartItem> cartItems = new ArrayList<>();
    cart2.setCartItems(cartItems);
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    ArrayList<Cart> cartList = new ArrayList<>();
    cartList.add(cart2);
    when(cartRepo.findAll()).thenReturn(cartList);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any())).thenReturn(new CartDTO());
    List<CartDTO> actualAllCarts = cartServiceImpl.getAllCarts();
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    verify(cartRepo).findAll();
    assertEquals(1, actualAllCarts.size());
    assertEquals(cartItems, actualAllCarts.get(0).getProducts());
  }

  /**
   * Method under test: {@link CartServiceImpl#getAllCarts()}
   */
  @Test
  void testGetAllCarts3() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("john.smith@example.org");
    user3.setFirstName("John");
    user3.setLastName("Smith");
    user3.setMobileNumber("Mobile Number");
    user3.setPassword("Password");
    user3.setRoles(new HashSet<>());
    user3.setUserId(2L);

    Cart cart3 = new Cart();
    cart3.setCartId(2L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(1.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("john.smith@example.org");
    user4.setFirstName("John");
    user4.setLastName("Smith");
    user4.setMobileNumber("Mobile Number");
    user4.setPassword("Password");
    user4.setRoles(new HashSet<>());
    user4.setUserId(2L);

    Cart cart4 = new Cart();
    cart4.setCartId(2L);
    ArrayList<CartItem> cartItems = new ArrayList<>();
    cart4.setCartItems(cartItems);
    cart4.setTotalPrice(1.0d);
    cart4.setUser(user4);

    ArrayList<Cart> cartList = new ArrayList<>();
    cartList.add(cart4);
    cartList.add(cart2);
    when(cartRepo.findAll()).thenReturn(cartList);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any())).thenReturn(new CartDTO());
    List<CartDTO> actualAllCarts = cartServiceImpl.getAllCarts();
    verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    verify(cartRepo).findAll();
    assertEquals(2, actualAllCarts.size());
    assertEquals(cartItems, actualAllCarts.get(1).getProducts());
  }

  /**
   * Method under test: {@link CartServiceImpl#getAllCarts()}
   */
  @Test
  void testGetAllCarts4() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    ArrayList<Cart> cartList = new ArrayList<>();
    cartList.add(cart2);
    when(cartRepo.findAll()).thenReturn(cartList);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any()))
            .thenThrow(new APIException("An error occurred"));
    assertThrows(APIException.class, () -> cartServiceImpl.getAllCarts());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    verify(cartRepo).findAll();
  }

  /**
   * Method under test: {@link CartServiceImpl#getCart(String, Long)}
   */
  @Test
  void testGetCart() {
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
    ArrayList<CartItem> cartItems = new ArrayList<>();
    cart3.setCartItems(cartItems);
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user2);
    when(cartRepo.findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(cart3);
    CartDTO cartDTO = new CartDTO();
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any())).thenReturn(cartDTO);
    CartDTO actualCart = cartServiceImpl.getCart("42", 1L);
    verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    assertEquals(cartItems, actualCart.getProducts());
    assertSame(cartDTO, actualCart);
  }

  /**
   * Method under test: {@link CartServiceImpl#getCart(String, Long)}
   */
  @Test
  void testGetCart2() {
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
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any()))
            .thenThrow(new APIException("An error occurred"));
    assertThrows(APIException.class, () -> cartServiceImpl.getCart("42", 1L));
    verify(cartRepo).findCartByEmailAndCartId(Mockito.<String>any(), Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
  }

  /**
   * Method under test: {@link CartServiceImpl#updateProductInCarts(Long, Long)}
   */
  @Test
  void testUpdateProductInCarts() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);

    Category category2 = new Category();
    category2.setCategoryId(1L);
    category2.setCategoryName("Category Name");
    category2.setProducts(new ArrayList<>());

    Product product2 = new Product();
    product2.setCategory(category2);
    product2.setDescription("The characteristics of someone or something");
    product2.setDiscount(10.0d);
    product2.setImage("Image");
    product2.setOrderItems(new ArrayList<>());
    product2.setPrice(10.0d);
    product2.setProductId(1L);
    product2.setProductName("Product Name");
    product2.setProducts(new ArrayList<>());
    product2.setQuantity(1);
    product2.setSpecialPrice(10.0d);

    CartItem cartItem2 = new CartItem();
    cartItem2.setCart(cart4);
    cartItem2.setCartItemId(1L);
    cartItem2.setDiscount(10.0d);
    cartItem2.setProduct(product2);
    cartItem2.setProductPrice(10.0d);
    cartItem2.setQuantity(1);
    when(cartItemRepo.save(Mockito.<CartItem>any())).thenReturn(cartItem2);
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user5 = new User();
    user5.setAddresses(new ArrayList<>());
    user5.setCart(new Cart());
    user5.setEmail("jane.doe@example.org");
    user5.setFirstName("Jane");
    user5.setLastName("Doe");
    user5.setMobileNumber("42");
    user5.setPassword("iloveyou");
    user5.setRoles(new HashSet<>());
    user5.setUserId(1L);

    Cart cart5 = new Cart();
    cart5.setCartId(1L);
    cart5.setCartItems(new ArrayList<>());
    cart5.setTotalPrice(10.0d);
    cart5.setUser(user5);

    User user6 = new User();
    user6.setAddresses(new ArrayList<>());
    user6.setCart(cart5);
    user6.setEmail("jane.doe@example.org");
    user6.setFirstName("Jane");
    user6.setLastName("Doe");
    user6.setMobileNumber("42");
    user6.setPassword("iloveyou");
    user6.setRoles(new HashSet<>());
    user6.setUserId(1L);

    Cart cart6 = new Cart();
    cart6.setCartId(1L);
    cart6.setCartItems(new ArrayList<>());
    cart6.setTotalPrice(10.0d);
    cart6.setUser(user6);
    Optional<Cart> ofResult = Optional.of(cart6);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Category category3 = new Category();
    category3.setCategoryId(1L);
    category3.setCategoryName("Category Name");
    category3.setProducts(new ArrayList<>());

    Product product3 = new Product();
    product3.setCategory(category3);
    product3.setDescription("The characteristics of someone or something");
    product3.setDiscount(10.0d);
    product3.setImage("Image");
    product3.setOrderItems(new ArrayList<>());
    product3.setPrice(10.0d);
    product3.setProductId(1L);
    product3.setProductName("Product Name");
    product3.setProducts(new ArrayList<>());
    product3.setQuantity(1);
    product3.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product3);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    cartServiceImpl.updateProductInCarts(1L, 1L);
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
    verify(cartItemRepo).save(Mockito.<CartItem>any());
  }

  /**
   * Method under test: {@link CartServiceImpl#updateProductInCarts(Long, Long)}
   */
  @Test
  void testUpdateProductInCarts2() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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
    when(cartItemRepo.save(Mockito.<CartItem>any())).thenThrow(new APIException("An error occurred"));
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);
    Optional<Cart> ofResult = Optional.of(cart4);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    Category category2 = new Category();
    category2.setCategoryId(1L);
    category2.setCategoryName("Category Name");
    category2.setProducts(new ArrayList<>());

    Product product2 = new Product();
    product2.setCategory(category2);
    product2.setDescription("The characteristics of someone or something");
    product2.setDiscount(10.0d);
    product2.setImage("Image");
    product2.setOrderItems(new ArrayList<>());
    product2.setPrice(10.0d);
    product2.setProductId(1L);
    product2.setProductName("Product Name");
    product2.setProducts(new ArrayList<>());
    product2.setQuantity(1);
    product2.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product2);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    assertThrows(APIException.class, () -> cartServiceImpl.updateProductInCarts(1L, 1L));
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
    verify(cartItemRepo).save(Mockito.<CartItem>any());
  }

  /**
   * Method under test:
   * {@link CartServiceImpl#updateProductQuantityInCart(Long, Long, Integer)}
   */
  @Test
  void testUpdateProductQuantityInCart() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    ArrayList<CartItem> cartItems = new ArrayList<>();
    cart4.setCartItems(cartItems);
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);

    Category category2 = new Category();
    category2.setCategoryId(1L);
    category2.setCategoryName("Category Name");
    category2.setProducts(new ArrayList<>());

    Product product2 = new Product();
    product2.setCategory(category2);
    product2.setDescription("The characteristics of someone or something");
    product2.setDiscount(10.0d);
    product2.setImage("Image");
    product2.setOrderItems(new ArrayList<>());
    product2.setPrice(10.0d);
    product2.setProductId(1L);
    product2.setProductName("Product Name");
    product2.setProducts(new ArrayList<>());
    product2.setQuantity(1);
    product2.setSpecialPrice(10.0d);

    CartItem cartItem2 = new CartItem();
    cartItem2.setCart(cart4);
    cartItem2.setCartItemId(1L);
    cartItem2.setDiscount(10.0d);
    cartItem2.setProduct(product2);
    cartItem2.setProductPrice(10.0d);
    cartItem2.setQuantity(1);
    when(cartItemRepo.save(Mockito.<CartItem>any())).thenReturn(cartItem2);
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user5 = new User();
    user5.setAddresses(new ArrayList<>());
    user5.setCart(new Cart());
    user5.setEmail("jane.doe@example.org");
    user5.setFirstName("Jane");
    user5.setLastName("Doe");
    user5.setMobileNumber("42");
    user5.setPassword("iloveyou");
    user5.setRoles(new HashSet<>());
    user5.setUserId(1L);

    Cart cart5 = new Cart();
    cart5.setCartId(1L);
    cart5.setCartItems(new ArrayList<>());
    cart5.setTotalPrice(10.0d);
    cart5.setUser(user5);

    User user6 = new User();
    user6.setAddresses(new ArrayList<>());
    user6.setCart(cart5);
    user6.setEmail("jane.doe@example.org");
    user6.setFirstName("Jane");
    user6.setLastName("Doe");
    user6.setMobileNumber("42");
    user6.setPassword("iloveyou");
    user6.setRoles(new HashSet<>());
    user6.setUserId(1L);

    Cart cart6 = new Cart();
    cart6.setCartId(1L);
    cart6.setCartItems(new ArrayList<>());
    cart6.setTotalPrice(10.0d);
    cart6.setUser(user6);
    Optional<Cart> ofResult = Optional.of(cart6);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
    CartDTO cartDTO = new CartDTO();
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any())).thenReturn(cartDTO);

    Category category3 = new Category();
    category3.setCategoryId(1L);
    category3.setCategoryName("Category Name");
    category3.setProducts(new ArrayList<>());

    Product product3 = new Product();
    product3.setCategory(category3);
    product3.setDescription("The characteristics of someone or something");
    product3.setDiscount(10.0d);
    product3.setImage("Image");
    product3.setOrderItems(new ArrayList<>());
    product3.setPrice(10.0d);
    product3.setProductId(1L);
    product3.setProductName("Product Name");
    product3.setProducts(new ArrayList<>());
    product3.setQuantity(1);
    product3.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product3);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    CartDTO actualUpdateProductQuantityInCartResult = cartServiceImpl.updateProductQuantityInCart(1L, 1L, 1);
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
    verify(cartItemRepo).save(Mockito.<CartItem>any());
    assertEquals(cartItems, actualUpdateProductQuantityInCartResult.getProducts());
    assertSame(cartDTO, actualUpdateProductQuantityInCartResult);
  }

  /**
   * Method under test:
   * {@link CartServiceImpl#updateProductQuantityInCart(Long, Long, Integer)}
   */
  @Test
  void testUpdateProductQuantityInCart2() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);

    Category category2 = new Category();
    category2.setCategoryId(1L);
    category2.setCategoryName("Category Name");
    category2.setProducts(new ArrayList<>());

    Product product2 = new Product();
    product2.setCategory(category2);
    product2.setDescription("The characteristics of someone or something");
    product2.setDiscount(10.0d);
    product2.setImage("Image");
    product2.setOrderItems(new ArrayList<>());
    product2.setPrice(10.0d);
    product2.setProductId(1L);
    product2.setProductName("Product Name");
    product2.setProducts(new ArrayList<>());
    product2.setQuantity(1);
    product2.setSpecialPrice(10.0d);

    CartItem cartItem2 = new CartItem();
    cartItem2.setCart(cart4);
    cartItem2.setCartItemId(1L);
    cartItem2.setDiscount(10.0d);
    cartItem2.setProduct(product2);
    cartItem2.setProductPrice(10.0d);
    cartItem2.setQuantity(1);
    when(cartItemRepo.save(Mockito.<CartItem>any())).thenReturn(cartItem2);
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user5 = new User();
    user5.setAddresses(new ArrayList<>());
    user5.setCart(new Cart());
    user5.setEmail("jane.doe@example.org");
    user5.setFirstName("Jane");
    user5.setLastName("Doe");
    user5.setMobileNumber("42");
    user5.setPassword("iloveyou");
    user5.setRoles(new HashSet<>());
    user5.setUserId(1L);

    Cart cart5 = new Cart();
    cart5.setCartId(1L);
    cart5.setCartItems(new ArrayList<>());
    cart5.setTotalPrice(10.0d);
    cart5.setUser(user5);

    User user6 = new User();
    user6.setAddresses(new ArrayList<>());
    user6.setCart(cart5);
    user6.setEmail("jane.doe@example.org");
    user6.setFirstName("Jane");
    user6.setLastName("Doe");
    user6.setMobileNumber("42");
    user6.setPassword("iloveyou");
    user6.setRoles(new HashSet<>());
    user6.setUserId(1L);

    Cart cart6 = new Cart();
    cart6.setCartId(1L);
    cart6.setCartItems(new ArrayList<>());
    cart6.setTotalPrice(10.0d);
    cart6.setUser(user6);
    Optional<Cart> ofResult = Optional.of(cart6);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any()))
            .thenThrow(new APIException("An error occurred"));

    Category category3 = new Category();
    category3.setCategoryId(1L);
    category3.setCategoryName("Category Name");
    category3.setProducts(new ArrayList<>());

    Product product3 = new Product();
    product3.setCategory(category3);
    product3.setDescription("The characteristics of someone or something");
    product3.setDiscount(10.0d);
    product3.setImage("Image");
    product3.setOrderItems(new ArrayList<>());
    product3.setPrice(10.0d);
    product3.setProductId(1L);
    product3.setProductName("Product Name");
    product3.setProducts(new ArrayList<>());
    product3.setQuantity(1);
    product3.setSpecialPrice(10.0d);
    Optional<Product> ofResult2 = Optional.of(product3);
    when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult2);
    assertThrows(APIException.class, () -> cartServiceImpl.updateProductQuantityInCart(1L, 1L, 1));
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CartDTO>>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    verify(productRepo).findById(Mockito.<Long>any());
    verify(cartItemRepo).save(Mockito.<CartItem>any());
  }

  /**
   * Method under test: {@link CartServiceImpl#deleteProductFromCart(Long, Long)}
   */
  @Test
  void testDeleteProductFromCart() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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
    doNothing().when(cartItemRepo).deleteCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);
    Optional<Cart> ofResult = Optional.of(cart4);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
    String actualDeleteProductFromCartResult = cartServiceImpl.deleteProductFromCart(1L, 1L);
    verify(cartItemRepo).deleteCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
    assertEquals("Product Product Name removed from the cart !!!", actualDeleteProductFromCartResult);
  }

  /**
   * Method under test: {@link CartServiceImpl#deleteProductFromCart(Long, Long)}
   */
  @Test
  void testDeleteProductFromCart2() {
    User user = new User();
    user.setAddresses(new ArrayList<>());
    user.setCart(new Cart());
    user.setEmail("jane.doe@example.org");
    user.setFirstName("Jane");
    user.setLastName("Doe");
    user.setMobileNumber("42");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setUserId(1L);

    Cart cart = new Cart();
    cart.setCartId(1L);
    cart.setCartItems(new ArrayList<>());
    cart.setTotalPrice(10.0d);
    cart.setUser(user);

    User user2 = new User();
    user2.setAddresses(new ArrayList<>());
    user2.setCart(cart);
    user2.setEmail("jane.doe@example.org");
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setMobileNumber("42");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setUserId(1L);

    Cart cart2 = new Cart();
    cart2.setCartId(1L);
    cart2.setCartItems(new ArrayList<>());
    cart2.setTotalPrice(10.0d);
    cart2.setUser(user2);

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());

    Product product = new Product();
    product.setCategory(category);
    product.setDescription("The characteristics of someone or something");
    product.setDiscount(10.0d);
    product.setImage("Image");
    product.setOrderItems(new ArrayList<>());
    product.setPrice(10.0d);
    product.setProductId(1L);
    product.setProductName("Product Name");
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
    doThrow(new APIException("An error occurred")).when(cartItemRepo)
            .deleteCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    when(cartItemRepo.findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any())).thenReturn(cartItem);

    User user3 = new User();
    user3.setAddresses(new ArrayList<>());
    user3.setCart(new Cart());
    user3.setEmail("jane.doe@example.org");
    user3.setFirstName("Jane");
    user3.setLastName("Doe");
    user3.setMobileNumber("42");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setUserId(1L);

    Cart cart3 = new Cart();
    cart3.setCartId(1L);
    cart3.setCartItems(new ArrayList<>());
    cart3.setTotalPrice(10.0d);
    cart3.setUser(user3);

    User user4 = new User();
    user4.setAddresses(new ArrayList<>());
    user4.setCart(cart3);
    user4.setEmail("jane.doe@example.org");
    user4.setFirstName("Jane");
    user4.setLastName("Doe");
    user4.setMobileNumber("42");
    user4.setPassword("iloveyou");
    user4.setRoles(new HashSet<>());
    user4.setUserId(1L);

    Cart cart4 = new Cart();
    cart4.setCartId(1L);
    cart4.setCartItems(new ArrayList<>());
    cart4.setTotalPrice(10.0d);
    cart4.setUser(user4);
    Optional<Cart> ofResult = Optional.of(cart4);
    when(cartRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertThrows(APIException.class, () -> cartServiceImpl.deleteProductFromCart(1L, 1L));
    verify(cartItemRepo).deleteCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartItemRepo).findCartItemByProductIdAndCartId(Mockito.<Long>any(), Mockito.<Long>any());
    verify(cartRepo).findById(Mockito.<Long>any());
  }
}
