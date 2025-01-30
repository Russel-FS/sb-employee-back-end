package com.hexagonal_service.hexagonal_spring_service.application.ports.input;

import java.util.List;
import java.util.Optional;

import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;

public interface ManageEmployeeUseCase {

    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployee(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
} 