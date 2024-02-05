package com.leandroadal.vortasks.entities.shop;

import com.leandroadal.vortasks.entities.shop.dto.ProductRequestDTO;
import com.leandroadal.vortasks.entities.shop.enumerators.ProductType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    public Product(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.type = data.type();
        this.icon = data.icon();
        this.coins = data.coins();
        this.gems = data.gems();
        this.totalSales = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType type;
    
    private String icon;
    private int coins;
    private int gems;
    private int totalSales;

    public void edit(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.type = data.type();
        this.icon = data.icon();
        this.coins = data.coins();
        this.gems = data.gems();
    }

    public void sell(){
        this.totalSales++;
    }
}
