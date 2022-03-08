package com.springbootframework.datapostgres.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException{
    private String resource;
    private String fieldName;
    private int fieldValue;

    public ResourceNotFoundException(String resource, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s : %d ", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResource() {
        return resource;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldValue() {
        return fieldValue;
    }
}
