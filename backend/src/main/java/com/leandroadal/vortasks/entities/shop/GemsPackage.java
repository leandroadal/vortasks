package com.leandroadal.vortasks.entities.shop;

import java.math.BigDecimal;

import com.leandroadal.vortasks.entities.shop.dto.GemsPackageRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GemsPackage {

    public GemsPackage(GemsPackageRequestDTO data) {
        this.nameOfPackage = data.nameOfPackage();
        this.gems = data.gems();
        this.money = data.money();
        this.icon = data.icon();
        this.totalSales = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfPackage;
    private String icon;
    private int gems;
    private BigDecimal money;
    private int totalSales;
    
    public void edit(GemsPackageRequestDTO data) {
        this.nameOfPackage = data.nameOfPackage();
        this.gems = data.gems();
        this.money = data.money();
        this.icon = data.icon();
    }

    public void sell(){
        this.totalSales++;
    }
}
