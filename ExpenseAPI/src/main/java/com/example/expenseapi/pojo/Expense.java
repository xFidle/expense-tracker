package com.example.expenseapi.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "date", nullable = false)
    private Date date = new Date(System.currentTimeMillis());

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Expense(double price, @NonNull User user, Category category) {
        this.price = price;
        this.user = user;
        this.category = category;
    }

    public Expense(double price, @NonNull User user, Category category, Date date) {
        this.price = price;
        this.user = user;
        this.category = category;
        this.date = date;
    }

    public Expense(double price, @NonNull User user) {
        this(price, user, new Category());
    }
}
