package com.example.expenseapi.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role with id " + id + " not found");
    }
    public RoleNotFoundException(String role) {super("Role " + role + " not found");}
    public RoleNotFoundException() {super("No role for this user in this group");}
}
