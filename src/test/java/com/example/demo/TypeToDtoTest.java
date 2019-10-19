package com.example.demo;

import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationTypeCode;
import com.example.demo.entities.OperationTypeDto;
import com.example.demo.utils.Mapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeToDtoTest {

    @Test
    public void testToDto() {
        OperationType type = new OperationType(OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationTypeDto dto = Mapper.typeToDto(type);
        assertEquals("NAME", dto.getOperationTypeName());
        Long id = dto.getOperationTypeId();
        Long expectedId = 123L;
        assertEquals(OperationTypeCode.CODE_ONE, dto.getOperationTypeCode());
        assertEquals(123L, dto.getOrderIndex());
        assertEquals(null, dto.getDateModified());
    }

    @Test
    public void dtoToType() {
        OperationTypeDto dto = new OperationTypeDto(123L, OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationType type = Mapper.dtoToType(dto);
        Long id = type.getOperationTypeId();
        Long expectedId = 123L;
        assertEquals(expectedId, id);
        assertEquals(OperationTypeCode.CODE_ONE, type.getOperationTypeCode());
        assertEquals(123L, type.getOrderIndex());
        assertEquals(null, type.getDateModified());
    }
}
