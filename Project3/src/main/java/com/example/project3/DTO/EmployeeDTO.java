package com.example.project3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private Integer userId;
    private String position;
    private Integer salary;

}
