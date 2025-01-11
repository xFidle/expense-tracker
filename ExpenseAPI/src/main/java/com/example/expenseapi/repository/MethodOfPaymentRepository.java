package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.MethodOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MethodOfPaymentRepository extends JpaRepository<MethodOfPayment, Long> {
    Optional<MethodOfPayment> findByName(String methodOfPayment);
}
