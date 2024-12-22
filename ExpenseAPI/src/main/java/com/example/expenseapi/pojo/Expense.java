package com.example.expenseapi.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "price", nullable = false)
    private double price;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "expense_date", nullable = false)
    private LocalDate date = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    public Expense(double price, @NonNull User user, Category category, Currency currency) {
        this.price = price;
        this.user = user;
        this.category = category;
        this.currency = currency;
    }

    public Expense(double price, @NonNull User user, Category category, LocalDate date, Currency currency) {
        this(price, user, category, currency);
        this.setDate(date);
    }
}
