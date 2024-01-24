package com.leandroadal.vortasks.entities.user;

import com.leandroadal.vortasks.dto.auth.UserCreateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public User(UserCreateDTO data) {
        this.name = data.name();
        this.username = data.username();
        this.email = data.email();
        this.password = data.password();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
    private Long id;

    private String name;
	private String username;
    private String email;
    private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProgressData progressData; // Referência ao usuário associado

}
