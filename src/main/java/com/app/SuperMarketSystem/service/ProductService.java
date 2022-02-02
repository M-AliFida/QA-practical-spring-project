package com.app.SuperMarketSystem.service;

import com.app.SuperMarketSystem.dto.ApiResponse;
import com.app.SuperMarketSystem.dto.ProductDTO;
import com.app.SuperMarketSystem.model.Order;
import com.app.SuperMarketSystem.model.Product;
import com.app.SuperMarketSystem.model.User;
import com.app.SuperMarketSystem.repository.OrderRepository;
import com.app.SuperMarketSystem.repository.ProductRepository;
import com.app.SuperMarketSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public ApiResponse findAllProducts() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Product> productList = productRepository.findAll();
            if (productList.isEmpty()) {
                apiResponse.setMessage("There is no product in the database");
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setData(null);
            } else {
                apiResponse.setMessage("Successfully fetched the products from the database");
                apiResponse.setData(productList);
                apiResponse.setStatus(HttpStatus.OK.value());
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse addProduct(Product product) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            productRepository.save(product);
            apiResponse.setMessage("Successfully added product in the database");
            apiResponse.setData(product);
            apiResponse.setStatus(HttpStatus.OK.value());
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse deleteProduct(String productId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                productRepository.delete(product.get());
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successfully deleted the product from the database");
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("There is no product against this ID");
            }
            apiResponse.setData(null);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse updateProduct(Product product) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (productOptional.isPresent()) {
                productRepository.save(product);
                apiResponse.setMessage("Product Successfully updated in the database");
                apiResponse.setData(productOptional);
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("There is no product against this ID");
                apiResponse.setData(null);
            }
            return apiResponse;

        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse getProductById(String productId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successful");
                apiResponse.setData(product);
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("There is no product in the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

/*    public ApiResponse purchaseProducts(Integer quantity, Integer userId, List<Product> productsList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Order order = new Order();
                for (Product product : productsList
                ) {
                    order.setTotalPrice(product.getPrice() + order.getTotalPrice());
                    order.getProducts().add(product);
                }
                order.setDeliveryStatus("Pending");
                order.setOrderTime(LocalDateTime.now());
                orderRepository.save(order);
                user.get().getOrders().add(order);
                apiResponse.setData(order);
                userRepository.save(user.get());
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("There is no user against this Id in the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }*/

    public ApiResponse purchaseProducts(Integer userId, List<ProductDTO> productsList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Order order = new Order();
                order.setTotalPrice(0.0);
                for (ProductDTO product : productsList
                ) {
                    Optional<Product> productToBuy = productRepository.findById(product.getProductId());
                    if (productToBuy.isPresent()) {
                        order.setTotalPrice(productToBuy.get().getPrice() * product.getQuantityToPurchase() + order.getTotalPrice());
                        order.getProducts().add(productToBuy.get());
                    } else {
                        apiResponse.setData(null);
                        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                        apiResponse.setMessage("There is no product against " + product.getProductId() + " in the database");
                    }
                }
                order.setDeliveryStatus("Pending");
                order.setOrderTime(LocalDateTime.now());
                orderRepository.save(order);
                user.get().getOrders().add(order);

                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successfully placed the orders");
                apiResponse.setData(order);

                userRepository.save(user.get());
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("There is no user against this Id in the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(null);
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }
}