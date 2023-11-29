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

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;
import com.app.repositories.CategoryRepo;

import java.util.ArrayList;
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

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplDiffblueTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(Category)}
     */
    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        when(categoryRepo.findByCategoryName(Mockito.<String>any())).thenReturn(category);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());
        assertThrows(APIException.class, () -> categoryServiceImpl.createCategory(category2));
        verify(categoryRepo).findByCategoryName(Mockito.<String>any());
    }

    /**
     * Method under test:  {@link CategoryServiceImpl#createCategory(Category)}
     */
    @Test
    void testCreateCategory2() {
        when(categoryRepo.findByCategoryName(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field", "Field Name"));

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.createCategory(category));
        verify(categoryRepo).findByCategoryName(Mockito.<String>any());
    }

    /**
     * Method under test:  {@link CategoryServiceImpl#getCategories(Integer, Integer, String, String)}
     */
    @Test
    void testGetCategories() {
        when(categoryRepo.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertThrows(APIException.class, () -> categoryServiceImpl.getCategories(10, 3, "Sort By", "asc"));
        verify(categoryRepo).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(Integer, Integer, String, String)}
     */
    @Test
    void testGetCategories2() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("asc");
        category.setProducts(new ArrayList<>());

        ArrayList<Category> content = new ArrayList<>();
        content.add(category);
        PageImpl<Category> pageImpl = new PageImpl<>(content);
        when(categoryRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any())).thenReturn(new CategoryDTO());
        CategoryResponse actualCategories = categoryServiceImpl.getCategories(10, 3, "Sort By", "asc");
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any());
        verify(categoryRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualCategories.getPageNumber().intValue());
        assertEquals(1, actualCategories.getPageSize().intValue());
        assertEquals(1, actualCategories.getTotalPages().intValue());
        assertEquals(1, actualCategories.getContent().size());
        assertEquals(1L, actualCategories.getTotalElements().longValue());
        assertTrue(actualCategories.isLastPage());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(Integer, Integer, String, String)}
     */
    @Test
    void testGetCategories3() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("asc");
        category.setProducts(new ArrayList<>());

        Category category2 = new Category();
        category2.setCategoryId(2L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());

        ArrayList<Category> content = new ArrayList<>();
        content.add(category2);
        content.add(category);
        PageImpl<Category> pageImpl = new PageImpl<>(content);
        when(categoryRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any())).thenReturn(new CategoryDTO());
        CategoryResponse actualCategories = categoryServiceImpl.getCategories(10, 3, "Sort By", "asc");
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any());
        verify(categoryRepo).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualCategories.getPageNumber().intValue());
        assertEquals(1, actualCategories.getTotalPages().intValue());
        assertEquals(2, actualCategories.getPageSize().intValue());
        assertEquals(2, actualCategories.getContent().size());
        assertEquals(2L, actualCategories.getTotalElements().longValue());
        assertTrue(actualCategories.isLastPage());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Category, Long)}
     */
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());
        when(categoryRepo.save(Mockito.<Category>any())).thenReturn(category2);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CategoryDTO categoryDTO = new CategoryDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any())).thenReturn(categoryDTO);

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(new ArrayList<>());
        CategoryDTO actualUpdateCategoryResult = categoryServiceImpl.updateCategory(category3, 1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(categoryRepo).save(Mockito.<Category>any());
        assertEquals(1L, category3.getCategoryId().longValue());
        assertSame(categoryDTO, actualUpdateCategoryResult);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Category, Long)}
     */
    @Test
    void testUpdateCategory2() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(new ArrayList<>());
        when(categoryRepo.save(Mockito.<Category>any())).thenReturn(category2);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any()))
                .thenThrow(new APIException("An error occurred"));

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(new ArrayList<>());
        assertThrows(APIException.class, () -> categoryServiceImpl.updateCategory(category3, 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        verify(categoryRepo).save(Mockito.<Category>any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Category, Long)}
     */
    @Test
    void testUpdateCategory3() {
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CategoryDTO>>any())).thenReturn(new CategoryDTO());

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.updateCategory(category, 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepo).delete(Mockito.<Category>any());
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualDeleteCategoryResult = categoryServiceImpl.deleteCategory(1L);
        verify(categoryRepo).delete(Mockito.<Category>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        assertEquals("Category with categoryId: 1 deleted successfully !!!", actualDeleteCategoryResult);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Category Name");
        category.setProducts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new APIException("An error occurred")).when(categoryRepo).delete(Mockito.<Category>any());
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(APIException.class, () -> categoryServiceImpl.deleteCategory(1L));
        verify(categoryRepo).delete(Mockito.<Category>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory3() {
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

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(products);
        Optional<Category> ofResult = Optional.of(category2);
        doNothing().when(categoryRepo).delete(Mockito.<Category>any());
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(productService.deleteProduct(Mockito.<Long>any())).thenReturn("Delete Product");
        String actualDeleteCategoryResult = categoryServiceImpl.deleteCategory(1L);
        verify(productService).deleteProduct(Mockito.<Long>any());
        verify(categoryRepo).delete(Mockito.<Category>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        assertEquals("Category with categoryId: 1 deleted successfully !!!", actualDeleteCategoryResult);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory4() {
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

        Category category2 = new Category();
        category2.setCategoryId(2L);
        category2.setCategoryName("com.app.entities.Category");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("Description");
        product2.setDiscount(0.5d);
        product2.setImage("com.app.entities.Product");
        product2.setOrderItems(new ArrayList<>());
        product2.setPrice(0.5d);
        product2.setProductId(2L);
        product2.setProductName("com.app.entities.Product");
        product2.setProducts(new ArrayList<>());
        product2.setQuantity(0);
        product2.setSpecialPrice(0.5d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product);

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryName("Category Name");
        category3.setProducts(products);
        Optional<Category> ofResult = Optional.of(category3);
        doNothing().when(categoryRepo).delete(Mockito.<Category>any());
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(productService.deleteProduct(Mockito.<Long>any())).thenReturn("Delete Product");
        String actualDeleteCategoryResult = categoryServiceImpl.deleteCategory(1L);
        verify(productService, atLeast(1)).deleteProduct(Mockito.<Long>any());
        verify(categoryRepo).delete(Mockito.<Category>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
        assertEquals("Category with categoryId: 1 deleted successfully !!!", actualDeleteCategoryResult);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory5() {
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.deleteCategory(1L));
        verify(categoryRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory6() {
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

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryName("Category Name");
        category2.setProducts(products);
        Optional<Category> ofResult = Optional.of(category2);
        when(categoryRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(productService.deleteProduct(Mockito.<Long>any())).thenThrow(new APIException("An error occurred"));
        assertThrows(APIException.class, () -> categoryServiceImpl.deleteCategory(1L));
        verify(productService).deleteProduct(Mockito.<Long>any());
        verify(categoryRepo).findById(Mockito.<Long>any());
    }
}
