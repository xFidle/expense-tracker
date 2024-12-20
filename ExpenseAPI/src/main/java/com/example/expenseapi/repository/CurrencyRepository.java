package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {}
