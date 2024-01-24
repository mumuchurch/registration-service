package com.afrikatek.registrationservice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, String entityName) {
        super("The " + entityName + " with id " + id + " could not be found.");
    }
}
