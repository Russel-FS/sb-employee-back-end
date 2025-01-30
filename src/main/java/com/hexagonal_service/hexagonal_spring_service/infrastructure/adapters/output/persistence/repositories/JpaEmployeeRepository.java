package com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.hexagonal_service.hexagonal_spring_service.infrastructure.adapters.output.persistence.entities.EmployeeEntity;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
