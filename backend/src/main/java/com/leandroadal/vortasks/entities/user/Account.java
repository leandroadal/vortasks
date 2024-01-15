package com.leandroadal.vortasks.entities.user;

import com.leandroadal.vortasks.dto.AccountCreateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account {

    public Account(AccountCreateDTO data) {
        this.username = data.username();
        this.email = data.email();
        this.password = data.password();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
    private Long id;

	private String username;
    private String email;
    private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user; // Referência ao usuário associado

}
