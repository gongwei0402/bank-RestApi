package com.cogent.bank_rest_api.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"username"}
                )
        }
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long id;
    @NotEmpty(message = "username should not be empty")
    @Column(name = "username", nullable = false)
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Column(name ="password", nullable = false)
    private String password;
    @NotEmpty(message = "role should not be empty")
    @Column(name ="role", nullable = false)
    private String role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    private Set<Account> accounts = new HashSet<>();
}
