package com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.input.dto.EmployeeDto;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.entities.EmployeeEntity;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail());
    }

    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        return employees.stream()
                .map(this::toDto)
                .toList();
    }

    public Employee toDomain(EmployeeEntity entity) {
        return new Employee(
                entity.getId() != null ? entity.getId() : 0,
                entity.getName(),
                entity.getEmail());
    }

    public Employee toDomain(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId() != null ? employeeDto.getId() : 0,
                employeeDto.getName(),
                employeeDto.getEmail());
    }

    public EmployeeEntity toEntity(Employee employee) {
        return new EmployeeEntity(
                employee.getId(),
                employee.getName(),
                employee.getEmail());
    }

}
