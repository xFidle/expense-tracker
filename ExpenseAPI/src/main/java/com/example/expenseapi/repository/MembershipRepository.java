package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Membership;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
