package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.TemporaryMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryMembershipRepository extends JpaRepository<TemporaryMembership, Long> {
}
