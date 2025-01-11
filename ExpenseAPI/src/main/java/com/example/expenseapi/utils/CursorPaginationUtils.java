package com.example.expenseapi.utils;

import com.example.expenseapi.pojo.Expense;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CursorPaginationUtils {
    public static Sort buildSortForDate(boolean desc) {
        if (desc) {
            return Sort.by(Sort.Direction.DESC, "date")
                    .and(Sort.by(Sort.Direction.DESC, "id"));
        } else {
            return Sort.by(Sort.Direction.ASC, "date")
                    .and(Sort.by(Sort.Direction.ASC, "id"));
        }
    }

    public static Sort buildSortForCategory(boolean desc) {
        if (desc) {
            return Sort.by(Sort.Direction.DESC, "category.name")
                    .and(Sort.by(Sort.Direction.DESC, "id"));
        } else {
            return Sort.by(Sort.Direction.ASC, "category.name")
                    .and(Sort.by(Sort.Direction.ASC, "id"));
        }
    }

    public static Specification<Expense> dateCursorSpec(Long lastId, LocalDate lastDate, boolean desc) {
        if (desc) {
            return (root, query, cb) -> cb.or(
                    cb.lessThan(root.get("date"), lastDate),
                    cb.and(
                            cb.equal(root.get("date"), lastDate),
                            cb.lessThan(root.get("id"), lastId)
                    )
            );
        } else {
            return (root, query, cb) -> cb.or(
                    cb.greaterThan(root.get("date"), lastDate),
                    cb.and(
                            cb.equal(root.get("date"), lastDate),
                            cb.greaterThan(root.get("id"), lastId)
                    )
            );
        }
    }
    public static Specification<Expense> categoryCursorSpec(Long lastId, String lastCategory, boolean desc) {
        if (desc) {
            return (root, query, cb) -> cb.or(
                    cb.lessThan(root.get("category").get("name"), lastCategory),
                    cb.and(
                            cb.equal(root.get("category").get("name"), lastCategory),
                            cb.lessThan(root.get("id"), lastId)
                    )
            );
        } else {
            return (root, query, cb) -> cb.or(
                    cb.greaterThan(root.get("category").get("name"), lastCategory),
                    cb.and(
                            cb.equal(root.get("category").get("name"), lastCategory),
                            cb.greaterThan(root.get("id"), lastId)
                    )
            );
        }
    }
}
