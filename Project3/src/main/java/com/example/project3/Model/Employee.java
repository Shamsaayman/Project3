package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    private Integer id;
//    @NotEmpty(message = "position cannot be empty")
//    @Column(columnDefinition = "not null" )
    private String position;
//    @NotNull(message = "salary cannot be empty")
//    @Positive
//    @Column(columnDefinition = "not null" )
    private Integer salary;

    @OneToOne
    @MapsId
    private User user;

}
