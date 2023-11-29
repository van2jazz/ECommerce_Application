package com.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.entities.Cart;
import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.ProductDTO;
import com.app.payloads.ProductResponse;
import com.app.repositories.CartRepo;
import com.app.repositories.CategoryRepo;
import com.app.repositories.ProductRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

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

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplDiffblueTest {
    @MockBean
    private CartRepo cartRepo;

    @MockBean
    private CartService cartService;

    @MockBean
    private CategoryRepo categoryRepo;

    @MockBean
    private FileService fileService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Long, Product)}
     */
    @Test
    void testAddProduct() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ProductDTO productDTO = new ProductDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(productDTO);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category2);
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
        when(productRepo.save(Mockito.<Product>any())).thenReturn(product);

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setCategory(category3);
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
        ProductDTO actualAddProductResult = productServiceImpl.addProduct(1L, product2);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(productRepo).save(Mockito.<Product>any());
        assertEquals("default.png", product2.getImage());
        assertEquals(9.0d, product2.getSpecialPrice());
        assertSame(category, product2.getCategory());
        assertSame(productDTO, actualAddProductResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Long, Product)}
     */
    @Test
    void testAddProduct2() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new APIException("An error occurred"));

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category2);
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
        assertThrows(APIException.class, () -> productServiceImpl.addProduct(1L, product));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(productRepo).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Long, Product)}
     */
    @Test
    void testAddProduct3() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("default.png");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(0.01d);
        product.setImage("default.png");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(0.01d);
        product.setProductId(1L);
        product.setProductName("default.png");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(0.01d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(products);
        Optional<Category> ofResult = Optional.of(category2);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new APIException("An error occurred"));

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setCategory(category3);
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
        assertThrows(APIException.class, () -> productServiceImpl.addProduct(1L, product2));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(productRepo).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Long, Product)}
     */
    @Test
    void testAddProduct4() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("default.png");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(0.01d);
        product.setImage("default.png");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(0.01d);
        product.setProductId(1L);
        product.setProductName("default.png");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(0.01d);

        Category category2 = new Category();
        category2.setCategoryId(2L);
        category2.setCategoryName("Product Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("default.png");
        product2.setDiscount(10.0d);
        product2.setImage("Product Name");
        product2.setOrderItems(new ArrayList<>());
        product2.setPrice(10.0d);
        product2.setProductId(2L);
        product2.setProductName("Product Name");
        product2.setProducts(new ArrayList<>());
        product2.setQuantity(0);
        product2.setSpecialPrice(10.0d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product);

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(products);
        Optional<Category> ofResult = Optional.of(category3);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new APIException("An error occurred"));

        Category category4 = new Category();
        category4.setCategoryId(1L);
        category4.setCategoryName("Category Name");
        category4.setProducts(new ArrayList<>());

        Product product3 = new Product();
        product3.setCategory(category4);
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
        assertThrows(APIException.class, () -> productServiceImpl.addProduct(1L, product3));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(productRepo).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Long, Product)}
     */
    @Test
    void testAddProduct5() {
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());

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
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.addProduct(1L, product));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getAllProducts(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllProducts() {
        ArrayList<Product> content = new ArrayList<>();
        when(productRepo.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        ProductResponse actualAllProducts = productServiceImpl.getAllProducts(10, 3, "Sort By", "asc");
        verify(productRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllProducts.getPageNumber().intValue());
        assertEquals(0, actualAllProducts.getPageSize().intValue());
        assertEquals(0L, actualAllProducts.getTotalElements().longValue());
        assertEquals(1, actualAllProducts.getTotalPages().intValue());
        assertTrue(actualAllProducts.isLastPage());
        assertEquals(content, actualAllProducts.getContent());
    }

    /**
     * Method under test:  {@link ProductServiceImpl#getAllProducts(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllProducts2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("asc");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(10.0d);
        product.setImage("asc");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(10.0d);
        product.setProductId(1L);
        product.setProductName("asc");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(10.0d);

        ArrayList<Product> content = new ArrayList<>();
        content.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(content);
        when(productRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        ProductResponse actualAllProducts = productServiceImpl.getAllProducts(10, 3, "Sort By", "asc");
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any());
        verify(productRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllProducts.getPageNumber().intValue());
        assertEquals(1, actualAllProducts.getPageSize().intValue());
        assertEquals(1, actualAllProducts.getTotalPages().intValue());
        assertEquals(1, actualAllProducts.getContent().size());
        assertEquals(1L, actualAllProducts.getTotalElements().longValue());
        assertTrue(actualAllProducts.isLastPage());
    }

    /**
     * Method under test:  {@link ProductServiceImpl#getAllProducts(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllProducts3() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any())).thenReturn(new ProductDTO());

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("asc");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setDiscount(10.0d);
        product.setImage("asc");
        product.setOrderItems(new ArrayList<>());
        product.setPrice(10.0d);
        product.setProductId(1L);
        product.setProductName("asc");
        product.setProducts(new ArrayList<>());
        product.setQuantity(1);
        product.setSpecialPrice(10.0d);

        Category category2 = new Category();
        category2.setCategoryId(2L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("asc");
        product2.setDiscount(0.5d);
        product2.setImage("Image");
        product2.setOrderItems(new ArrayList<>());
        product2.setPrice(0.5d);
        product2.setProductId(2L);
        product2.setProductName("Product Name");
        product2.setProducts(new ArrayList<>());
        product2.setQuantity(0);
        product2.setSpecialPrice(0.5d);

        ArrayList<Product> content = new ArrayList<>();
        content.add(product2);
        content.add(product);
        PageImpl<Product> pageImpl = new PageImpl<>(content);
        when(productRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        ProductResponse actualAllProducts = productServiceImpl.getAllProducts(10, 3, "Sort By", "asc");
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<ProductDTO>>any());
        verify(productRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllProducts.getPageNumber().intValue());
        assertEquals(1, actualAllProducts.getTotalPages().intValue());
        assertEquals(2, actualAllProducts.getPageSize().intValue());
        assertEquals(2, actualAllProducts.getContent().size());
        assertEquals(2L, actualAllProducts.getTotalElements().longValue());
        assertTrue(actualAllProducts.isLastPage());
    }

    /**
     * Method under test:  {@link ProductServiceImpl#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() {
        when(cartRepo.findCartsByProductId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepo).delete(Mockito.<Product>any());
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualDeleteProductResult = productServiceImpl.deleteProduct(1L);
        verify(cartRepo).findCartsByProductId(Mockito.<Long>any());
        verify(productRepo).delete(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
        assertEquals("Product with productId: 1 deleted successfully !!!", actualDeleteProductResult);
    }

    /**
     * Method under test:  {@link ProductServiceImpl#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct2() {
        when(cartRepo.findCartsByProductId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        Optional<Product> ofResult = Optional.of(product);
        doThrow(new APIException("An error occurred")).when(productRepo).delete(Mockito.<Product>any());
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(APIException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(cartRepo).findCartsByProductId(Mockito.<Long>any());
        verify(productRepo).delete(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct3() {
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
        when(cartRepo.findCartsByProductId(Mockito.<Long>any())).thenReturn(cartList);
        when(cartService.deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn("jane.doe@example.org");

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
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepo).delete(Mockito.<Product>any());
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualDeleteProductResult = productServiceImpl.deleteProduct(1L);
        verify(cartRepo).findCartsByProductId(Mockito.<Long>any());
        verify(cartService).deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any());
        verify(productRepo).delete(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
        assertEquals("Product with productId: 1 deleted successfully !!!", actualDeleteProductResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct4() {
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
        cart4.setCartItems(new ArrayList<>());
        cart4.setTotalPrice(1.0d);
        cart4.setUser(user4);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart4);
        cartList.add(cart2);
        when(cartRepo.findCartsByProductId(Mockito.<Long>any())).thenReturn(cartList);
        when(cartService.deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn("jane.doe@example.org");

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
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepo).delete(Mockito.<Product>any());
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualDeleteProductResult = productServiceImpl.deleteProduct(1L);
        verify(cartRepo).findCartsByProductId(Mockito.<Long>any());
        verify(cartService, atLeast(1)).deleteProductFromCart(Mockito.<Long>any(), Mockito.<Long>any());
        verify(productRepo).delete(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
        assertEquals("Product with productId: 1 deleted successfully !!!", actualDeleteProductResult);
    }
}
