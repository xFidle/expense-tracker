package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Currency;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findBySymbol(@NonNull String symbol);
}
