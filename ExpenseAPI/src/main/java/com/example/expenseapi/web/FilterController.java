package com.example.expenseapi.web;

import com.example.expenseapi.dto.ExpenseFilter;
import com.example.expenseapi.pojo.Expense;
import com.example.expenseapi.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filter")
@Tag(name="Filter Controller", description = "Controller to filter expenses")
public class FilterController {
    private final ExpenseService service;
    public FilterController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Expense>> search(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String groupName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String beginDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String email
    ) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setCategoryName(category);
        filter.setGroupName(groupName);
        filter.setPriceMin(minPrice);
        filter.setPriceMax(maxPrice);
        filter.setEmail(email);
        if (beginDate != null) filter.setBeginDate(LocalDate.parse(beginDate));
        if (endDate != null) filter.setEndDate(LocalDate.parse(endDate));

        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }
    @Deprecated
    @GetMapping("/category/{name}")
    @Operation(summary = "Retrieves expenses with given category")
    @ApiResponse(responseCode = "200", description = "List of expense objects with given category", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByCategory(@PathVariable String name) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setCategoryName(name);
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/date/{date}")
    @Operation(summary = "Retrieves expenses with given date")
    @ApiResponse(responseCode = "200", description = "List of expense objects with given date", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByDate(@PathVariable String date) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setBeginDate(LocalDate.parse(date));
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/date/{begin}/{end}")
    @Operation(summary = "Retrieves expenses between given dates")
    @ApiResponse(responseCode = "200", description = "List of expense objects between given dates", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByPeriod(@PathVariable String begin, @PathVariable String end) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setBeginDate(LocalDate.parse(begin));
        filter.setEndDate(LocalDate.parse(end));
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/price/{begin}/{end}")
    @Operation(summary = "Retrieves expenses between given prices")
    @ApiResponse(responseCode = "200", description = "List of expense object between given prices", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByPriceRange(@PathVariable double begin, @PathVariable double end) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setPriceMin(begin);
        filter.setPriceMax(end);
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/price/lower/{price}")
    @Operation(summary = "Retrieves expenses lower than given price")
    @ApiResponse(responseCode = "200", description = "List of expense object which price is lower than given", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByPriceLowerThan(@PathVariable double price) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setPriceMax(price);
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/price/greater/{price}")
    @Operation(summary = "Retrieves expenses greater than given price")
    @ApiResponse(responseCode = "200", description = "List of expense object which price is greater than given", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Expense.class))))
    public ResponseEntity<List<Expense>> getByPriceGreaterThan(@PathVariable double price) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setPriceMin(price);
        return new ResponseEntity<>(service.searchExpenses(filter), HttpStatus.OK);
    }
}
