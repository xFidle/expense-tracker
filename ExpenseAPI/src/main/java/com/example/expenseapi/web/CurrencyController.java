package com.example.expenseapi.web;

import com.example.expenseapi.service.CurrencyService;
import com.example.expenseapi.pojo.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController extends GenericController<Currency, Long> {
    public CurrencyController(CurrencyService service) {
        super(service);
    }
}