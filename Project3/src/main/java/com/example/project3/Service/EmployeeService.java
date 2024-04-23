package com.example.project3.Service;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllemployees(){

        return employeeRepository.findAll();
    }

    public void registerEmployee(User user , EmployeeDTO employeeDTO){
        User u = new User();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        user.setRole("EMPLOYEE");

        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);

        Employee e = new Employee();
        e.setId(employeeDTO.getUserId());
        e.setPosition(employeeDTO.getPosition());
        e.setSalary(employeeDTO.getSalary());

        u.setEmployee(e);

        authRepository.save(u);
        employeeRepository.save(e);
    }

    public void updateEmployee(Integer id, EmployeeDTO employeeDTO){
       Employee e = employeeRepository.findEmployeeById(id);
        e.setPosition(employeeDTO.getPosition());
        e.setSalary(employeeDTO.getSalary());
        employeeRepository.save(e);
    }

    public void deleteEmployee(Integer id){
        Employee e = employeeRepository.findEmployeeById(id);
        employeeRepository.delete(e);
    }

}
