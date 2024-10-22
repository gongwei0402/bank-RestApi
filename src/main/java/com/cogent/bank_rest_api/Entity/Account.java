package com.cogent.bank_rest_api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long id;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @NotEmpty
    @Column(name ="isApproved", nullable = false)
    private boolean isApproved;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    private Set<Transaction> transactions = new HashSet<>();
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_Id",
            nullable = false
    )
    @JsonIgnore
    private User user;

}

