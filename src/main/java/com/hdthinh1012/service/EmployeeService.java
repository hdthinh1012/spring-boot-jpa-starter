package com.hdthinh1012.service;

import com.hdthinh1012.model.Employee;
import com.hdthinh1012.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        log.info("Employee with id: {} doesn't exist!", id);
        return null;
    }

    public Employee saveEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        Employee savedEmployee = employeeRepo.save(employee);

        log.info("Employee with id: {} saved successfully", employee.getId());
        return savedEmployee;
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
        if (existingEmployee.isEmpty()) {
            log.info("Employee with id: {} doesn't exist for updating!", employee.getId());
            return null;
        }
        employee.setCreatedAt(existingEmployee.get().getCreatedAt());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee updatedEmployee = employeeRepo.save(employee);
        log.info("Employee with id: {} updated successfully", employee.getId());
        return updatedEmployee;
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepo.deleteById(id);
    }
}
