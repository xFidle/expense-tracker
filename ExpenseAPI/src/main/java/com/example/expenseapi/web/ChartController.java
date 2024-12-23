package com.example.expenseapi.web;

import com.example.expenseapi.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chart")
public class ChartController {
    private final ExpenseService service;

    public ChartController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/my/group/{year}")
    @Operation(summary = "Retrieves map <Month, ExpensesSum> for given year (group-scoped)")
    public ResponseEntity<Map<String, Double>> getUserMap(@PathVariable String year) {
        return new ResponseEntity<>(service.getMonthlyExpensesForGroup(year), HttpStatus.OK);
    }

    @GetMapping("/my/expenses/{year}")
    @Operation(summary = "Retrieves map <Month, ExpensesSum> for given year (user-scoped)")
    public ResponseEntity<Map<String, Double>> getGroupMap(@PathVariable String year) {
        return new ResponseEntity<>(service.getMonthlyExpensesForUser(year), HttpStatus.OK);
    }

}