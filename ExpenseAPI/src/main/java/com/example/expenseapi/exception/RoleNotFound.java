package com.example.expenseapi.exception;

public class RoleNotFound extends RuntimeException {
    public RoleNotFound(Long id) {
        super("Role with id " + id + " not found");
    }
}
