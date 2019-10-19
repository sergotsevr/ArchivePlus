package com.example.demo.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation_type")
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long operationTypeId;
    private OperationTypeCode operationTypeCode;
    private String operationTypeName;
    private int orderIndex;
    private LocalDateTime dateModified;

    public OperationType() {
    }

    public OperationType(Long operationTypeId, OperationTypeCode operationTypeCode, String operationTypeName, int orderIndex, LocalDateTime dateModified) {
        this.operationTypeId = operationTypeId;
        this.operationTypeCode = operationTypeCode;
        this.operationTypeName = operationTypeName;
        this.orderIndex = orderIndex;
        this.dateModified = dateModified;
    }

    public OperationType(OperationTypeCode operationTypeCode, String operationTypeName, int orderIndex, LocalDateTime dateModified) {
        this.operationTypeCode = operationTypeCode;
        this.operationTypeName = operationTypeName;
        this.orderIndex = orderIndex;
        this.dateModified = dateModified;
    }

    public OperationType(String operationTypeCode, String operationTypeName) {
        this.operationTypeCode = OperationTypeCode.valueOf(operationTypeCode);
        this.operationTypeName = operationTypeName;
    }

    public Long getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Long operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public OperationTypeCode getOperationTypeCode() {
        return operationTypeCode;
    }

    public void setOperationTypeCode(OperationTypeCode operationTypeCode) {
        this.operationTypeCode = operationTypeCode;
    }

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "Id=" + operationTypeId
                + " Code=" + operationTypeCode
                + " Name=" + operationTypeName;
    }
}
