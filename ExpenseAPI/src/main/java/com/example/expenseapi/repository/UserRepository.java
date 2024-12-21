package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
