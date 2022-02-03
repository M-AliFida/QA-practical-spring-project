package com.app.SuperMarketSystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<Product> products = new ArrayList<>();

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
}