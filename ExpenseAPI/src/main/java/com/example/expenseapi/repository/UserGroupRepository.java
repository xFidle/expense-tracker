package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Group;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<Group, Long> {}
