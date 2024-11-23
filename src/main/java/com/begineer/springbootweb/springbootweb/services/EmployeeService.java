package com.begineer.springbootweb.springbootweb.services;

import com.begineer.springbootweb.springbootweb.dto.EmployeeDTO;
import com.begineer.springbootweb.springbootweb.entities.EmployeeEntity;
import com.begineer.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java.lang.reflect.Field;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity ->
                        modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        boolean exists = isExistById(id);
        if (!exists)
            return null;
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public Boolean isExistById(Long id) {
        return employeeRepository.existsById(id);
    }

    public boolean deleteEmployeeById(Long id) {
        boolean exist = isExistById(id);
        if (!exist) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updates) {
       boolean exists=isExistById(id);
       if(!exists)
           return null;
       EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
       updates.forEach((field,value)->{
           Field fieldsToBeUpdated=ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
           fieldsToBeUpdated.setAccessible(true);
           ReflectionUtils.setField(fieldsToBeUpdated,employeeEntity,value);
       });
       return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}