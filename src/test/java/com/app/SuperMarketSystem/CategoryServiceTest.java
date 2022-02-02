package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.Category;
import com.app.SuperMarketSystem.repository.CategoryRepository;
import com.app.SuperMarketSystem.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}