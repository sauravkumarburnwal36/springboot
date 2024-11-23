package com.begineer.springbootweb.springbootweb.controllers;

import com.begineer.springbootweb.springbootweb.dto.EmployeeDTO;
import com.begineer.springbootweb.springbootweb.entities.EmployeeEntity;
import com.begineer.springbootweb.springbootweb.repositories.EmployeeRepository;
import com.begineer.springbootweb.springbootweb.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

        @GetMapping()
    public List<EmployeeDTO> getAllEmployees()
        {
            return employeeService.getAllEmployees();
        }
        @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createNewEmployee(employeeDTO);
    }
    @PutMapping()
    public String updateEmployeeById(){
        return "Hi From PUT";
    }
}
