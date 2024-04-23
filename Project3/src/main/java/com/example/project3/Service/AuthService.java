package com.example.project3.Service;


import com.example.project3.API.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public List<User> getAllUsers(){
            return authRepository.findAll();
        }

    public void register(User user){
        user.setRole("ADMIN");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public void updateUser(String username, User user){
        User u = authRepository.findUserByUsername(username);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        authRepository.save(u);
    }

    public void deleteUser(Integer userId){
        User u = authRepository.findUserById(userId);
        authRepository.delete(u);
    }

    public User login(String username, String password){
        User user = null;
        return user;
    }

    public void logout(){
    }



}