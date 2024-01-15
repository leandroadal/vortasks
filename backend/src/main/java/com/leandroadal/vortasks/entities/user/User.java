package com.leandroadal.vortasks.entities.user;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.social.Friend;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    public User(String name, float coins, float gems) {
        this.name = name;
        this.coins = coins;
        this.gems = gems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private float coins;
    private float gems;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserBackup backup; // Referência ao backup associado

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> myShopping;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friend> friends;
}