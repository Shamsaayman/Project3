package com.example.project3.Service;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();
    }

    public void registerCustomer(User user , CustomerDTO customerDTO){
        User u = new User();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        user.setRole("CUSTOMER");

        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);


        Customer c = new Customer();
        c.setId(customerDTO.getUserId());
        c.setPhoneNumber(customerDTO.getPhoneNumber());

        u.setCustomer(c);

        authRepository.save(u);
        customerRepository.save(c);
    }

    public void updateCustomer(Integer id, CustomerDTO customerDTO){
        Customer c = customerRepository.findCustomerById(id);
        c.setId(customerDTO.getUserId());
        c.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(c);
    }

    public void deleteCustomer(Integer id){
        Customer c = customerRepository.findCustomerById(id);
        customerRepository.delete(c);
    }
}
