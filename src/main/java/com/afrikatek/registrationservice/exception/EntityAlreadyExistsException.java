package com.afrikatek.registrationservice.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Long id, String entityName) {
        super("The " + entityName + " with id " + id + " already exists.");
    }
}
