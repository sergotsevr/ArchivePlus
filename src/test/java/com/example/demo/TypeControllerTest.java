package com.example.demo;

import com.example.demo.controller.TypeController;
import com.example.demo.entities.OperationType;
import com.example.demo.entities.OperationTypeCode;
import com.example.demo.entities.OperationTypeDto;
import com.example.demo.utils.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@TestPropertySource(locations = "/application.properties", properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypeControllerTest {

    @Autowired
    private RestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private TypeController typeController;

    @Test
    public void update() throws Exception {
        Long id; //добавляем элемент, который будем обновлять
        OperationType operationType = new OperationType(OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationTypeDto operationTypeDto = Mapper.typeToDto(operationType);
        restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        ResponseEntity<OperationType> objectResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        operationType = objectResponseEntity.getBody();
        id = operationType.getOperationTypeId();

        operationType.setOperationTypeName("NAME_CHANGED");
        operationType.setOrderIndex(1313);
        operationTypeDto = Mapper.typeToDto(operationType);
        restTemplate.put("http://localhost:" + port + "/type/update", operationTypeDto, OperationType.class);
        ResponseEntity<OperationType> operationTypeUpdated = restTemplate.getForEntity("http://localhost:" + port + "/type/" + id, OperationType.class);
        assertThat(operationTypeUpdated.toString().contains("\"operationTypeCode\": \"CODE_ONE\""));
        assertThat(operationTypeUpdated.toString().contains("\"operationTypeName\": \"NAME_CHANGED\""));
        assertThat(operationTypeUpdated.toString().contains("\"orderIndex\": 1312"));
        assertThat(operationTypeUpdated.toString().contains("\"dateModified\": null"));
        assertThat(operationTypeUpdated.toString().contains("\"operationTypeId\":"));
    }

    @Test
    public void add() throws Exception {
        OperationType operationType = new OperationType(OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationTypeDto operationTypeDto = Mapper.typeToDto(operationType);
        ResponseEntity<OperationType> objectResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        operationType = objectResponseEntity.getBody();
        assertThat(operationType.toString().contains("\"operationTypeCode\": \"CODE_ONE\""));
        assertThat(operationType.toString().contains("\"operationTypeName\": \"NAME\""));
        assertThat(operationType.toString().contains("\"orderIndex\": 123"));
        assertThat(operationType.toString().contains("\"dateModified\": null"));
        assertThat(operationType.toString().contains("\"operationTypeId\":"));
    }

    @Test
    public void findAll() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OperationType>> response = restTemplate.exchange(
                "http://localhost:" + port + "/type/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OperationType>>() {
                });
        List<OperationType> operationTypeAdd = response.getBody();
        assertThat(!operationTypeAdd.toString().isEmpty());
    }

    @Test
    public void findById() throws Exception {
        Long id; //добавляем элемент, который будем искать
        OperationType operationTypeAdd = new OperationType(OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationTypeDto operationTypeDto = Mapper.typeToDto(operationTypeAdd);
        restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        ResponseEntity<OperationType> objectResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        operationTypeAdd = objectResponseEntity.getBody();
        id = operationTypeAdd.getOperationTypeId();


        ResponseEntity<OperationType> operationType = restTemplate.getForEntity("http://localhost:" + port + "/type/" + id, OperationType.class);
        assertThat(operationType.toString().contains("\"operationTypeCode\":"));
        assertThat(operationType.toString().contains("\"operationTypeName\":"));
        assertThat(operationType.toString().contains("\"orderIndex\":"));
        assertThat(operationType.toString().contains("\"dateModified\":"));
        assertThat(operationType.toString().contains("\"operationTypeId\":"));
    }

    @Test
    public void delete() throws Exception {

        //создаем элемент для удаления
        OperationType operationTypeCreate = new OperationType(OperationTypeCode.CODE_ONE, "NAME", 123, null);
        OperationTypeDto operationTypeDto = Mapper.typeToDto(operationTypeCreate);
        ResponseEntity<OperationType> objectResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/type/", operationTypeDto, OperationType.class);
        OperationType operationTypeDelete = objectResponseEntity.getBody();
        Long id = operationTypeDelete.getOperationTypeId();

        //удаляем добавленный элемент
        System.out.println(operationTypeDelete.toString());
        restTemplate.delete("http://localhost:" + port + "/type/" + id);

        //Пытаемся получить элемент и проверяемм, что элемент удален
        ResponseEntity<OperationType> operationType = restTemplate.getForEntity("http://localhost:" + port + "/type/" + id, OperationType.class);
        assertThat(operationType.toString().isEmpty());
    }
}

