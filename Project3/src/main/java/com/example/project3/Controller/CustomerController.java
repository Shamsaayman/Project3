package com.example.project3.Controller;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @PostMapping("/register")
    public ResponseEntity register(@AuthenticationPrincipal User user , @RequestBody @Valid CustomerDTO customerDTO ){
        customerService.registerCustomer(user , customerDTO);
        return ResponseEntity.status(200).body("Register success");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer( @RequestBody @Valid CustomerDTO customerDTO , @PathVariable Integer id){
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.status(200).body("Update successful");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer( @PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Delete successful");
    }
}
