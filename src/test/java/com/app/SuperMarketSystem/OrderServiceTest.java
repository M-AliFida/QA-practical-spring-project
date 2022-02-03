package com.app.SuperMarketSystem;

import com.app.SuperMarketSystem.model.Order;
import com.app.SuperMarketSystem.repository.OrderRepository;
import com.app.SuperMarketSystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @Test
    public void findAllOrders() {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order("OrderNumber", 10000.0, LocalDateTime.now(), "pending");
        orderList.add(order);
        when(orderRepository.findAll()).thenReturn(orderList);
        assertEquals(orderList, orderService.findAllOrders().getData());
    }

    @Test
    public void addOrderTest() {
        Order order = new Order("OrderNumber", 10000.0, LocalDateTime.now(), "pending");
        when(orderRepository.save(order)).thenReturn(order);
        assertEquals(order, orderService.addOrder(order).getData());
    }

    @Test
    public void deleteOrderTest() {
        Order order = new Order("OrderNumber", 10000.0, LocalDateTime.now(), "pending");
        when(orderRepository.getById(order.getOrderNumber())).thenReturn(order);
        assertEquals(200, orderService.deleteOrder(order.getOrderNumber()).getStatus());
    }

    @Test
    public void updateOrderTest() {
        Order oldOrder = new Order("OrderNumber", 10000.0, LocalDateTime.now(), "pending");
        Order updatedOrder = new Order("OrderNumber", 50000.0, LocalDateTime.now(), "accepted");

        when(orderRepository.getById(oldOrder.getOrderNumber())).thenReturn(oldOrder);
        assertEquals(200, orderService.updateOrder(updatedOrder).getStatus());
        assertEquals(updatedOrder, orderService.updateOrder(updatedOrder).getData());
    }

    @Test
    public void getOrderByIdTest() {
        String orderNumber = "OrderNumber";
        Order order = new Order("OrderNumber", 10000.0, LocalDateTime.now(), "pending");
        when(orderRepository.getById(orderNumber)).thenReturn(order);
        assertEquals(order, orderService.getOrderByOrderNumber(orderNumber).getData());
    }

}