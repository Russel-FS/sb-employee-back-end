package com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hexagonal_service.hexagonal_spring_service.application.ports.output.ManageEmployeePort;
import com.hexagonal_service.hexagonal_spring_service.domain.models.Employee;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.entities.EmployeeEntity;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.mappers.EmployeeMapper;
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.repositories.JpaEmployeeRepository;

@Component
public class EmployeeRepositoryAdapter implements ManageEmployeePort {

    private final JpaEmployeeRepository jpaEmployeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeRepositoryAdapter(JpaEmployeeRepository jpaEmployeeRepository, EmployeeMapper employeeMapper) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee addEmployee(Employee employee) {  
        EmployeeEntity entityToSave = employeeMapper.toEntity(employee);
        entityToSave.setId(null);  
        return employeeMapper.toDomain(jpaEmployeeRepository.save(entityToSave));
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
       return jpaEmployeeRepository.findById(id).map(employeeMapper::toDomain);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jpaEmployeeRepository.findAll().stream().map(employeeMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeEntity employeeEntity = jpaEmployeeRepository.save(employeeMapper.toEntity(employee));
        return employeeMapper.toDomain(employeeEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        jpaEmployeeRepository.deleteById(id);
    }

}
