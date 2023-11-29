package com.app.controllers;

import static org.mockito.Mockito.when;

import com.app.entities.Category;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;
import com.app.services.CategoryService;
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

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerDiffblueTest {
  @Autowired
  private CategoryController categoryController;

  @MockBean
  private CategoryService categoryService;

  /**
   * Method under test:  {@link CategoryController#createCategory(Category)}
   */
  @Test
  void testCreateCategory() throws Exception {
    when(categoryService.createCategory(Mockito.<Category>any())).thenReturn(new CategoryDTO());

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());
    String content = (new ObjectMapper()).writeValueAsString(category);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/category")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("{\"categoryId\":null,\"categoryName\":null}"));
  }

  /**
   * Method under test:  {@link CategoryController#getCategories(Integer, Integer, String, String)}
   */
  @Test
  void testGetCategories() throws Exception {
    when(categoryService.getCategories(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
            Mockito.<String>any())).thenReturn(new CategoryResponse());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/public/categories");
    MockMvcBuilders.standaloneSetup(categoryController)
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
   * Method under test:  {@link CategoryController#updateCategory(Category, Long)}
   */
  @Test
  void testUpdateCategory() throws Exception {
    when(categoryService.updateCategory(Mockito.<Category>any(), Mockito.<Long>any())).thenReturn(new CategoryDTO());

    Category category = new Category();
    category.setCategoryId(1L);
    category.setCategoryName("Category Name");
    category.setProducts(new ArrayList<>());
    String content = (new ObjectMapper()).writeValueAsString(category);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/admin/categories/{categoryId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    MockMvcBuilders.standaloneSetup(categoryController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("{\"categoryId\":null,\"categoryName\":null}"));
  }

  /**
   * Method under test:  {@link CategoryController#deleteCategory(Long)}
   */
  @Test
  void testDeleteCategory() throws Exception {
    when(categoryService.deleteCategory(Mockito.<Long>any())).thenReturn("Delete Category");
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/admin/categories/{categoryId}",
            1L);
    MockMvcBuilders.standaloneSetup(categoryController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
            .andExpect(MockMvcResultMatchers.content().string("Delete Category"));
  }
}
