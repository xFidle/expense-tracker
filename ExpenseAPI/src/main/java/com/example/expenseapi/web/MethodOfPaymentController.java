package com.example.expenseapi.web;

import com.example.expenseapi.pojo.MethodOfPayment;
import com.example.expenseapi.service.MethodOfPaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class MethodOfPaymentController extends GenericController<MethodOfPayment, Long> {
    public MethodOfPaymentController(MethodOfPaymentService service) {
        super(service);
    }
}
