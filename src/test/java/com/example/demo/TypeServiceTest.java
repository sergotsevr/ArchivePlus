/*
package com.example.demo;


import com.example.demo.controller.TypeController;
import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationTypeCode;
import com.example.demo.entities.OperationTypeDto;
import com.example.demo.repositories.OperationTypeRepository;
import com.example.demo.services.OperationTypeService;
import com.example.demo.utils.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TypeServiceTest {

    OperationTypeService operationTypeService=new OperationTypeService();
    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Test
    public void addTest() throws Exception {
        OperationType operationType = new OperationType( OperationTypeCode.CODE_ONE, "NAME", 123, null);
        Long id = operationType.getOperationTypeId();
        OperationTypeDto operationTypeSaved = operationTypeService.add(operationType);
        Optional<OperationType> byId = operationTypeRepository.findById(id);
        OperationType operationTypeFromDB;
        if (byId.isPresent()) {
            operationTypeFromDB = byId.get();
            assertEquals(operationTypeSaved, Mapper.typeToDto(operationTypeFromDB));
        } else {
            assertEquals(1, 0);
        }
        operationTypeService.delete(operationType);
    }

    @Test
    public void getByIdTest() {
        OperationType operationType = new OperationType( OperationTypeCode.CODE_ONE, "NAME", 123, null);
        /*operationTypeService.add(operationType);

import org.junit.Test;Long id = operationType.getOperationTypeId();
        OperationTypeDto byId = operationTypeService.getById(id);
        assertEquals( byId, Mapper.typeToDto(operationType));
    }

    @Test
    public void updateTest(){
        OperationType operationType = new OperationType( OperationTypeCode.CODE_ONE, "NAME", 1673, null);
        OperationTypeDto operationTypeDto = operationTypeService.update(operationType);
        assertEquals( Mapper.typeToDto(operationType), operationTypeDto);
    }
}
*/