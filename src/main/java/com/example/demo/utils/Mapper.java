package com.example.demo.utils;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    static public OperationTypeDto typeToDto(OperationType operationType) {
        return new OperationTypeDto(operationType.getOperationTypeId(),
                operationType.getOperationTypeCode(),
                operationType.getOperationTypeName(),
                operationType.getOrderIndex(),
                operationType.getDateModified());
    }

    static public OperationType dtoToType(OperationTypeDto operationTypeDto) {
        return new OperationType(operationTypeDto.getOperationTypeId(),
                operationTypeDto.getOperationTypeCode(),
                operationTypeDto.getOperationTypeName(),
                operationTypeDto.getOrderIndex(),
                operationTypeDto.getDateModified());
    }
}
