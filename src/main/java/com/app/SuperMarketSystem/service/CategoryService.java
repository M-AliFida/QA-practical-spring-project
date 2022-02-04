package com.app.SuperMarketSystem.service;

import com.app.SuperMarketSystem.dto.ApiResponse;
import com.app.SuperMarketSystem.model.Category;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse findAllCategories() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Category> categoriesList = categoryRepository.findAll();
            if (categoriesList.isEmpty()) {
                apiResponse.setMessage("No categories found within the database");
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setData(null);
            } else {
                apiResponse.setMessage("Successfully fetched categories from the database");
                apiResponse.setData(categoriesList);
                apiResponse.setStatus(HttpStatus.OK.value());
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse addCategory(Category category) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            categoryRepository.save(category);
            apiResponse.setMessage("Successfully added category in the database");
            apiResponse.setData(category);
            apiResponse.setStatus(HttpStatus.OK.value());
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse deleteCategory(String categoryId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Category category = categoryRepository.getById(categoryId);
            if (null != category) {
                category.setProducts(null);
                categoryRepository.delete(category);
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successfully deleted category from the database");
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such category found against this ID");
            }
            apiResponse.setData(null);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse updateCategory(Category category) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Category existingCategory = categoryRepository.getById(category.getId());
            if (null != existingCategory) {
                categoryRepository.save(category);
                apiResponse.setMessage("Successfully updated category within the database");
                apiResponse.setData(category);
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such category found against this ID");
                apiResponse.setData(null);
            }
            return apiResponse;

        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse getCategoryById(String categoryId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Category category = categoryRepository.getById(categoryId);
            if (null != category) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successful");
                apiResponse.setData(category);
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such category found within the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse addProductsInCategory(String categoryId, List<Product> productList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Category category = categoryRepository.getById(categoryId);
            if (null != category) {
                if (productList.isEmpty()) {
                    apiResponse.setMessage("The product list is empty");
                    apiResponse.setData(productList);
                } else {
                    category.getProducts().addAll(productList);
                    categoryRepository.save(category);
                    apiResponse.setMessage("Successfully added products within the category");
                    apiResponse.setData(category);
                }
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setMessage("No such category found within the database");
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }
}