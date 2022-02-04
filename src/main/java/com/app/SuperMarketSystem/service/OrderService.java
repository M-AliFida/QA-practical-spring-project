package com.app.SuperMarketSystem.service;

import com.app.SuperMarketSystem.dto.ApiResponse;
import com.app.SuperMarketSystem.model.Order;
import com.app.SuperMarketSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ApiResponse findAllOrders() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Order> categoriesList = orderRepository.findAll();
            if (categoriesList.isEmpty()) {
                apiResponse.setMessage("No orders found within the database");
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setData(null);
            } else {
                apiResponse.setMessage("Successfully fetched orders from the database");
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

    public ApiResponse addOrder(Order order) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            orderRepository.save(order);
            apiResponse.setMessage("Successfully added order within the database");
            apiResponse.setData(order);
            apiResponse.setStatus(HttpStatus.OK.value());
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse deleteOrder(String orderId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Order order = orderRepository.getById(orderId);
            if (null != order) {
                orderRepository.delete(order);
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successfully deleted order from the database");
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such order found against this ID");
            }
            apiResponse.setData(null);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse updateOrder(Order order) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Order orderOptional = orderRepository.getById(order.getOrderNumber());
            if (null != orderOptional) {
                orderRepository.save(order);
                apiResponse.setMessage("Successfully updated order within the database");
                apiResponse.setData(order);
                apiResponse.setStatus(HttpStatus.OK.value());
            } else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such order found against this ID");
                apiResponse.setData(null);
            }
            return apiResponse;

        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

    public ApiResponse getOrderByOrderNumber(String orderNumber) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Order order = orderRepository.getById(orderNumber);
            if (null != order) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Successful");
                apiResponse.setData(order);
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("No such order found within the database");
            }
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return apiResponse;
        }
    }

}