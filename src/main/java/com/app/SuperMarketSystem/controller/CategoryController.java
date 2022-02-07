package com.app.SuperMarketSystem.controller;

import com.app.SuperMarketSystem.dto.ApiResponse;
import com.app.SuperMarketSystem.model.Category;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ApiResponse list() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/save")
    public ApiResponse save(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable(name = "id") String categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/getBy/{id}")
    public ApiResponse getById(@PathVariable(name = "id") String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/addProducts")
    public ApiResponse addProducts(@RequestParam(name = "categoryId") String categoryId, @RequestBody List<Product> productList) {
        return categoryService.addProductsInCategory(categoryId, productList);
    }
}