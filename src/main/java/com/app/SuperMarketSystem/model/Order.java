package com.app.SuperMarketSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderNumber = UUID.randomUUID().toString();
    private Double totalPrice;
    private LocalDateTime orderTime;
    private String deliveryStatus;
    @ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Order(String orderNumber, Double totalPrice, LocalDateTime orderTime, String deliveryStatus) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.deliveryStatus = deliveryStatus;
    }   
}