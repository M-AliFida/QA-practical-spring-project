package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.repository.ProductRepository;
import com.app.SuperMarketSystem.service.ProductService;
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
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Stream.of(new Product("Product Id", "Product name", 1000.0)).collect(Collectors.toList()));
        assertNotNull(productService.findAllProducts().getData());
    }
}