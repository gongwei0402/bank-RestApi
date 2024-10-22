package com.cogent.bank_rest_api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotEmpty(message = "Original Account Id should not be empty")
    private  Long fromAccountId;
    @NotEmpty(message = "Target Account Id should not be empty")
    private  Long toAccountId;
    @NotEmpty(message = "Amount should not be empty")
    private  double amount;
    private Date date;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "account_id",
            nullable = false
    )
    @JsonIgnore
    private Account account;
}
