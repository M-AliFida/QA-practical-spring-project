package com.app.SuperMarketSystem.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private Double price;
}