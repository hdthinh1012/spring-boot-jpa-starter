package com.hdthinh1012.repository;

import com.hdthinh1012.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    
}
