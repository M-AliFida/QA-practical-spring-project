package com.app.SuperMarketSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
//    private Integer quantityInStock;
    private double price;
}
