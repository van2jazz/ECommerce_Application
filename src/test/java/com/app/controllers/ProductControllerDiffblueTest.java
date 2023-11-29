package com.app.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.payloads.ProductDTO;
import com.app.payloads.ProductResponse;
import com.app.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test:  {@link ProductController#addProduct(Product, Long)}
     */
    @Test
    void testAddProduct() throws Exception {
        when(productService.addProduct(Mockito.<Long>any(), Mockito.<Product>any())).thenReturn(new ProductDTO());

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
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/admin/categories/{categoryId}/product", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":null,\"productName\":null,\"image\":null,\"description\":null,\"quantity\":null,\"price\":0.0,"
                                        + "\"discount\":0.0,\"specialPrice\":0.0}"));
    }

    /**
     * Method under test:  {@link ProductController#getAllProducts(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new ProductResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/public/products");
        MockMvcBuilders.standaloneSetup(productController)
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
     * Method under test:  {@link ProductController#updateProduct(Product, Long)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(Mockito.<Long>any(), Mockito.<Product>any())).thenReturn(new ProductDTO());

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
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/admin/products/{productId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"productId\":null,\"productName\":null,\"image\":null,\"description\":null,\"quantity\":null,\"price\":0.0,"
                                        + "\"discount\":0.0,\"specialPrice\":0.0}"));
    }

    /**
     * Method under test:  {@link ProductController#deleteProductByCategory(Long)}
     */
    @Test
    void testDeleteProductByCategory() throws Exception {
        when(productService.deleteProduct(Mockito.<Long>any())).thenReturn("Delete Product");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/admin/products/{productId}", 1L);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Product"));
    }

    /**
     * Method under test:  {@link ProductController#getProductsByCategory(Long, Integer, Integer, String, String)}
     */
    @Test
    void testGetProductsByCategory() throws Exception {
        when(productService.searchByCategory(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ProductResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/public/categories/{categoryId}/products", 1L);
        MockMvcBuilders.standaloneSetup(productController)
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
     * Method under test:  {@link ProductController#getProductsByKeyword(String, Integer, Integer, String, String)}
     */
    @Test
    void testGetProductsByKeyword() throws Exception {
        when(productService.searchProductByKeyword(Mockito.<String>any(), Mockito.<Integer>any(), Mockito.<Integer>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ProductResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/public/products/keyword/{keyword}",
                "Keyword");
        MockMvcBuilders.standaloneSetup(productController)
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
     * Method under test:
     * {@link ProductController#updateProductImage(Long, MultipartFile)}
     */
    @Test
    void testUpdateProductImage() throws Exception {
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/admin/products/{productId}/image", "Uri Variables", "Uri Variables")
                .param("image", String.valueOf(new MockMultipartFile("Name", contentStream)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
