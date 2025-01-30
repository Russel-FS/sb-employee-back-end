package com.hexagonal_service.hexagonal_spring_service.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;

public interface ManageEmployeePort {

    Employee addEmployee(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
