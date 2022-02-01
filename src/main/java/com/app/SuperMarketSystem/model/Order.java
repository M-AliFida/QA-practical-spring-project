package com.app.SuperMarketSystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}