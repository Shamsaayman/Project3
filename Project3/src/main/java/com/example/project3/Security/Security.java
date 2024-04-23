package com.example.project3.Security;


import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()

                .requestMatchers("/api/v1/auth/register","/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/update").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/v1/auth/get","/api/v1/auth/delete" ).hasAnyAuthority("ADMIN")

                .requestMatchers("/api/v1/customer/register","/api/v1/customer/update").permitAll()
                .requestMatchers("/api/v1/customer/get","/api/v1/customer/delete").hasAuthority("EMPLOYEE")

                .requestMatchers("/api/v1/employee/register", "/api/v1/employee/update").permitAll()
                .requestMatchers("/api/v1/employee/get","/api/v1/employee/delete").hasAuthority("EMPLOYEE")

                .requestMatchers("/api/v1/account/create","/api/v1/account/accounts","/api/v1/account/withdraw","/api/v1/account/deposit" , "/api/v1/account/transfer").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/activate","/api/v1/account/block").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/account/getusers").hasAuthority("EMPLOYEE")

                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}