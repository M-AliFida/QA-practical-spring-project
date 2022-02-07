package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.dto.ProductDTO;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.ProductRepository;
import com.app.SuperMarketSystem.repository.UserRepository;
import com.app.SuperMarketSystem.service.ProductService;
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
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Stream.of(new Product("Product Id", "Product name", 1000.0)).collect(Collectors.toList()));
        assertNotNull(productService.findAllProducts().getData());
    }

    @Test
    public void addProductTest() {
        Product product = new Product("Product Id", "Product name", 1000.0);
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product, productService.addProduct(product).getData());
    }

    @Test
    public void deleteProductTest() {
        Product product = new Product("Product Id", "Product name", 1000.0);
        when(productRepository.getById(product.getId())).thenReturn(product);
        assertEquals(200, productService.deleteProduct(product.getId()).getStatus());
    }

    @Test
    public void updateProductTest() {
        Product oldProduct = new Product("ProductId", "Product name", 1000.0);
        Product updatedProduct = new Product("ProductId", "Updated Product name", 2000.0);

        when(productRepository.getById(oldProduct.getId())).thenReturn(oldProduct);
        assertEquals(200, productService.updateProduct(updatedProduct).getStatus());
        assertEquals(updatedProduct, productService.updateProduct(updatedProduct).getData());
    }

    @Test
    public void getProductByIdTest() {
        String id = "ProductId";
        Product product = new Product("ProductId", "Product name", 1000.0);
        when(productRepository.getById(id)).thenReturn(product);
        assertEquals(product, productService.getProductById(id).getData());
    }

    @Test
    public void purchaseProductsTest() {
        Integer userId = 1;
        User user = new User(1, "Test", "User", "test@gmail.com", 20, "password", "address", "phone number");
        when(userRepository.getById(userId)).thenReturn(user);

        ProductDTO productDTO = new ProductDTO("ProductId", 5.0);
        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(productDTO);

        String productId = "ProductId";
        Product product = new Product("ProductId", "Product name", 1000.0);
        when(productRepository.getById(productId)).thenReturn(product);

        assertEquals(200, productService.purchaseProducts(userId, productDTOS).getStatus());
    }
}