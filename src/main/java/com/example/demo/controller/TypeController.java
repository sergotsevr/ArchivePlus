package com.example.demo.controller;

import com.example.demo.entities.OperationTypeDto;
import com.example.demo.services.OperationTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/type")
public class TypeController {

    private static Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private OperationTypeService operationTypeService;

    @PostMapping("/")
    public OperationTypeDto add(@RequestBody OperationTypeDto operationTypeDto) {
        logger.info("Creating OperationType id={}", operationTypeDto.getOperationTypeId());
        return operationTypeService.add(operationTypeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("deleting OperationType with id={}", id);
        operationTypeService.delete(id);
    }

    @GetMapping("/")
    public List<OperationTypeDto> getList() {
        logger.info("Searching for all OperationType logger class={}", logger.getClass() );
        List<OperationTypeDto> list = operationTypeService.findAll();
        return list;
    }

    
    @GetMapping("/{id}")
    public OperationTypeDto getById(@PathVariable Long id) {
        logger.info("Searching for OperationType with id={}", id);
        OperationTypeDto operationTypeDto = operationTypeService.getById(id);
        return operationTypeDto;
    }

    @PutMapping("/update")
    public OperationTypeDto update(@RequestBody OperationTypeDto operationTypeDto) {
        logger.info("Updating OperationType with id={}", operationTypeDto.getOperationTypeId());
        return operationTypeService.update(operationTypeDto);
    }
}
