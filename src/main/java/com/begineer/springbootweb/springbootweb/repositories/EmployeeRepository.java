package com.begineer.springbootweb.springbootweb.repositories;

import com.begineer.springbootweb.springbootweb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
