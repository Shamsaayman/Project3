package com.example.project3.Controller;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getAllemployees());
    }

    @PostMapping("/register")
    public ResponseEntity register(@AuthenticationPrincipal User user , @RequestBody @Valid EmployeeDTO employeeDTO ){
        employeeService.registerEmployee(user , employeeDTO);
        return ResponseEntity.status(200).body("Register success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee( @RequestBody @Valid EmployeeDTO employeeDTO , @PathVariable Integer id){
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.status(200).body("Update successful");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee( @PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body("Delete successful");
    }
}
