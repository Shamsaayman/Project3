package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "accountNumber cannot be empty")
    @Column(columnDefinition = "not null")
    @Pattern(regexp = "/^\\d{4}-\\d{4}-\\d{4}-\\d{4}$/\n")
    private Integer accountNumber;
    @NotNull(message = "balance cannot be empty")
    @Positive
    @Column(columnDefinition = "not null")
    private double balance;
    @AssertFalse
    private boolean isActive;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
