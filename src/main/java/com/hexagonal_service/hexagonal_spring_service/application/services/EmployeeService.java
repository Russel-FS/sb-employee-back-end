package com.hexagonal_service.hexagonal_spring_service.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hexagonal_service.hexagonal_spring_service.application.ports.input.ManageEmployeeUseCase;
import com.hexagonal_service.hexagonal_spring_service.application.ports.output.ManageEmployeePort;
import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;

@Service
public class EmployeeService implements ManageEmployeeUseCase {

    private final ManageEmployeePort manageEmployeePort;

    public EmployeeService(ManageEmployeePort manageEmployeePort) {
        this.manageEmployeePort = manageEmployeePort;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return manageEmployeePort.addEmployee(employee);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return manageEmployeePort.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return manageEmployeePort.getAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        return manageEmployeePort.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        manageEmployeePort.deleteEmployee(id);
    }

}
