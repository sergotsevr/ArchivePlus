package com.example.demo.repositories;

import com.example.demo.entities.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {

}
