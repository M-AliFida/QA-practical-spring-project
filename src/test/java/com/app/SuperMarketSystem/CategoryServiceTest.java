package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.Category;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.repository.CategoryRepository;
import com.app.SuperMarketSystem.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void findAllCategoriesTest() {
        when(categoryRepository.findAll()).thenReturn(Stream.of(new Category("Category Id", "Category name")).collect(Collectors.toList()));
        assertNotNull(categoryService.findAllCategories().getData());
    }

    @Test
    public void addCategoryTest() {
        Category category = new Category("Category Id", "Category name");
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoryService.addCategory(category).getData());
    }

    @Test
    public void updateCategoryTest() {
        Category oldCategory = new Category("CategoryId", "Category name");
        Category updatedCategory = new Category("CategoryId", "Updated Category name");
        when(categoryRepository.getById(oldCategory.getId())).thenReturn(oldCategory);
        assertEquals(200, categoryService.updateCategory(updatedCategory).getStatus());
        assertEquals(updatedCategory, categoryService.updateCategory(updatedCategory).getData());
    }

    @Test
    public void deleteCategoryTest() {
        Category category = new Category("Category Id", "Category name");
        when(categoryRepository.getById(category.getId())).thenReturn(category);
        assertEquals(200, categoryService.deleteCategory(category.getId()).getStatus());
    }

    @Test
    public void getCategoryByIdTest() {
        String id = "CategoryId";
        Category category = new Category("CategoryId", "Category name");
        when(categoryRepository.getById(id)).thenReturn(category);
        assertEquals(category, categoryService.getCategoryById(id).getData());
    }

    @Test
    public void addProductsInCategoryTest() {
        String id = "CategoryId";
        Category category = new Category("CategoryId", "Category name");
        when(categoryRepository.getById(id)).thenReturn(category);

        Product product = new Product("ProductId", "Product name", 1000.0);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        assertEquals(200, categoryService.addProductsInCategory(id, productList).getStatus());
        assertEquals("Successfully added the products in the category", categoryService.addProductsInCategory(id, productList).getMessage());
    }
}