package com.begineer.springbootweb.springbootweb.controllers;

import com.begineer.springbootweb.springbootweb.dto.EmployeeDTO;
import com.begineer.springbootweb.springbootweb.entities.EmployeeEntity;
import com.begineer.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }

        @GetMapping()
    public List<EmployeeEntity> getEmployee(@RequestParam(required = false)Integer age, @RequestParam(required = false) String sortBy)
        {
            return employeeRepository.findAll();
        }
        @PostMapping()
    public EmployeeEntity updateEmployeeById(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
    @PutMapping()
    public String createNewEmployee(){
        return "Hi From PUT";
    }
}
