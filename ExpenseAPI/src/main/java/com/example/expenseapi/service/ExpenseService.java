package com.example.expenseapi.service;

import java.util.List;
import com.example.expenseapi.pojo.Expense;

public interface ExpenseService extends GenericService<Expense, Long> {
    List<Expense> getExpensesByEmail(String email);
    List<Expense> getExpensesByCategory(String category);
    List<Expense> getExpensesByDate(String date);
    List<Expense> getExpensesByPeriod(String begin, String end);
}