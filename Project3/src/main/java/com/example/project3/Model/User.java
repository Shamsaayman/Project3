package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "username cannot be empty")
//    @Size(min = 4 , max = 10)
//    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
//    @NotEmpty(message = "password cannot be empty")
//    @Size(min = 6)
    private String password;
//    @NotEmpty(message = "name cannot be empty")
//    @Size(min = 2 , max = 20)
//    @Column(columnDefinition = "varchar(20) not null ")
    private String name;
//    @Email
//    @Column(columnDefinition = "varchar(60) unique")
    private String email;
//    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)")
    private String role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
