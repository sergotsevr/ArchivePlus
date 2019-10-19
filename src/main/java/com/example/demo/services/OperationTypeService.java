package com.example.demo.services;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationTypeDto;
import com.example.demo.repositories.OperationTypeRepository;
import com.example.demo.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class OperationTypeService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OperationTypeRepository operationTypeRepository;

    public OperationTypeDto add(OperationTypeDto operationTypeDto) {
        try {
            OperationType operationType = Mapper.dtoToType(operationTypeDto);
            OperationType type = operationTypeRepository.save(operationType);
            OperationTypeDto operationTypeDtoBack = Mapper.typeToDto(type);
            return operationTypeDtoBack;
        } catch (Exception e) {
            logger.error("failed to create new OperationType with id={}", operationTypeDto.getOperationTypeId());
            return null;
        }
    }

    public void delete(long id) {
        try {
            operationTypeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete OperationType with id={}", id);
        }
    }

    public List<OperationTypeDto> findAll() {
        List<OperationType> list = operationTypeRepository.findAll();
        List<OperationTypeDto> listDto = new LinkedList<OperationTypeDto>();
        for (OperationType operationType : list
        ) {
            listDto.add(Mapper.typeToDto(operationType));
        }
        if (listDto.isEmpty()) {
            logger.warn("OperationType list is empty");
        }
        return listDto;
    }

    public OperationTypeDto getById(Long operationTypeId) {
        Optional<OperationType> byId = operationTypeRepository.findById(operationTypeId);
        if (byId.isPresent()) {
            OperationType operationType = byId.get();
            return Mapper.typeToDto(operationType);
        }
        logger.warn("No OperationStage with id={}", operationTypeId);
        return null;
    }

    public OperationTypeDto update(OperationTypeDto operationTypeDto) {
        try {
            OperationType operationType = Mapper.dtoToType(operationTypeDto);
            OperationType type = operationTypeRepository.save(operationType);
            return Mapper.typeToDto(type);
        } catch (Exception e) {
            logger.error("failed to update OperationType with id={} , operationTypeRepository={}", operationTypeDto.getOperationTypeId(), operationTypeRepository);
            return null;
        }
    }
}
