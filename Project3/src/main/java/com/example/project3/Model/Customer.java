package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    private Integer id;
//    @NotEmpty(message = "phoneNumber cannot be empty")
//    @Column(columnDefinition = "not null")
//    @Pattern(regexp = "(/^(\\+\\d{1,3}[- ]?)?\\d{10}$/)")
    private String phoneNumber;

    @OneToOne
    @MapsId
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Account> accounts;
}
