package com.begineer.springbootweb.springbootweb.controllers;

import com.begineer.springbootweb.springbootweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    @GetMapping(path="/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name ="employeeId") Long id){
        return new EmployeeDTO(id,"Saurav","saurav09@gmail.com",26, LocalDate.of(2021,8,5),true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(name="inputAge",required = false)Integer age,
                                  @RequestParam(required = false)String sortBy)
    {
        return " Hi Age: "+age+" sortBy: "+sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeDTO.setId(100L);
        return employeeDTO;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hi From Put";
    }

    @PatchMapping
    public String updatePartialDetailInEmployeeById(){
        return "Hi From Patch";
    }

    @DeleteMapping
    public String deleteEmployeeById(){
        return "Hi From Delete";
    }
}
