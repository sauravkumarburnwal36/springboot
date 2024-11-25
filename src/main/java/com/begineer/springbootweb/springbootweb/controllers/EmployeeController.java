package com.begineer.springbootweb.springbootweb.controllers;

import com.begineer.springbootweb.springbootweb.dto.EmployeeDTO;
import com.begineer.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.begineer.springbootweb.springbootweb.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO=employeeService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1->ResponseEntity.ok(employeeDTO1)).
                orElseThrow(()->new ResourceNotFoundException("Employee not found with id: "+id));
    }

        @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
        {

            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
        @PostMapping()
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1=employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                                          @PathVariable(name="employeeId") Long id)
    {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id,employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable (name="employeeId") Long id)
    {
        boolean deleted=employeeService.deleteEmployeeById(id);
        if(!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody  Map<String,Object> updates,
                                                                 @PathVariable(name="employeeId") Long id){
        EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeById(id,updates);
        if(employeeDTO==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
