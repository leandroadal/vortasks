package com.leandroadal.vortasks.entities.user;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.UserBackup;
import com.leandroadal.vortasks.entities.shop.Product;
import com.leandroadal.vortasks.entities.social.Friend;
import com.leandroadal.vortasks.entities.social.GroupTask;
import com.leandroadal.vortasks.entities.social.OnlineMission;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "progress_data")
@NoArgsConstructor
public class UserProgressData {

    public UserProgressData(int coins, int gems, int level, float xp, User user) {
        this.coins = coins;
        this.gems = gems;
        this.level = level;
        this.xp = xp;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private int coins;
    private int gems;
    private int level;
    private float xp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserBackup backup; // Referência ao backup associado

    @JoinTable(
      name = "user_purchased_products", 
      joinColumns = @JoinColumn(name = "user_progress_id"), 
      inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
    private List<Product> purchasedProducts;

    @OneToMany(mappedBy = "progressData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friend> friends;

    @JoinTable(
      name = "progress_data_group_task", 
      joinColumns = @JoinColumn(name = "progress_data_id"), 
      inverseJoinColumns = @JoinColumn(name = "group_task_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupTask> groupTasks;

    @OneToMany(mappedBy = "progressData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OnlineMission> onlineMissions;
}
