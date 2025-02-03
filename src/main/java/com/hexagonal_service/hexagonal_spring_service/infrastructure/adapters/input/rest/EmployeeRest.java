package com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.input.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal_service.hexagonal_spring_service.application.ports.input.ManageEmployeeUseCase;
import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.input.dto.EmployeeDto;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.mappers.EmployeeMapper;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

    private final ManageEmployeeUseCase manageEmployeeUseCase;
    private final EmployeeMapper employeeMapper;

    public EmployeeRest(ManageEmployeeUseCase manageEmployeeUseCase, EmployeeMapper employeeMapper) {
        this.manageEmployeeUseCase = manageEmployeeUseCase;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        return manageEmployeeUseCase.getAllEmployees()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            throw new IllegalArgumentException("ID should not be provided when creating a new employee");
        }
        Employee employee = employeeMapper.toDomain(employeeDto);
        Employee response = manageEmployeeUseCase.createEmployee(employee);
        return ResponseEntity.ok(employeeMapper.toDto(response));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable Long id) {
        Optional<Employee> response = manageEmployeeUseCase.getEmployee(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(employeeMapper.toDto((Employee) response.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        manageEmployeeUseCase.deleteEmployee(id);
         return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getId() == null) {
            throw new IllegalArgumentException("id debe ser proporcionado para actualizar un empleado");
        }
        Employee employee = employeeMapper.toDomain(employeeDto);
        Employee response = manageEmployeeUseCase.updateEmployee(employee);
        return ResponseEntity.ok(employeeMapper.toDto(response));
    }
 

}
