package com.example.expenseapi.utils;

import com.example.expenseapi.pojo.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> nameContains(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }
            String pattern = "%" + name.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);
        });
    }

    public static Specification<User> surnameContains(String surname) {
        return (((root, query, criteriaBuilder) -> {
            if (surname == null) {
                return criteriaBuilder.conjunction();
            }
            String pattern = "%" + surname.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), pattern);
        }));
    }
}
