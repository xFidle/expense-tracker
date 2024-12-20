package com.example.expenseapi.repository;

import com.example.expenseapi.pojo.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserEmail(String email);
    List<Expense> findByCategoryName(String category);
    List<Expense> findByDate(LocalDate date);
    List<Expense> findByDateBetween(LocalDate begin, LocalDate end);
    List<Expense> findByPriceBetween(double leftEnd, double rightEnd);
    List<Expense> findByPriceLessThan(double price);
    List<Expense> findByPriceGreaterThan(double price);
    List<Expense> findAllByOrderByDateDesc();
    Optional<Expense> findTopByOrderByIdDesc();

    @Query("SELECT e FROM Expense e WHERE e.user IN (SELECT m.user FROM Membership m WHERE m.name = :name)")
    List<Expense> findByUserGroupName(String name);
}