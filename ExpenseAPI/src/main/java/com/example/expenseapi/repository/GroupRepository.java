package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {}
