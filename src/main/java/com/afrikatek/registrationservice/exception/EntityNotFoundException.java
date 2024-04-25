package com.afrikatek.registrationservice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, String entityName) {
        super("The " + entityName + " with id " + id + " could not be found.");
    }

    public EntityNotFoundException(String guid, String entityName) {
        super("The " + entityName + " with guid " + guid + " could not be found.");
    }
}
