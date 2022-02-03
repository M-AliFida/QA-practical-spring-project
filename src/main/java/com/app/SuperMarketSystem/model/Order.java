package com.app.SuperMarketSystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderNumber = UUID.randomUUID().toString();
    private Double totalPrice;
    private LocalDateTime orderTime;
    private String deliveryStatus;
    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Order(String orderNumber, Double totalPrice, LocalDateTime orderTime, String deliveryStatus) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.deliveryStatus = deliveryStatus;
    }
}