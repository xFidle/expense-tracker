package com.example.expenseapi.service;

import com.example.expenseapi.dto.ExpenseFilter;
import com.example.expenseapi.pojo.*;
import com.example.expenseapi.pojo.Currency;
import com.example.expenseapi.repository.*;
import com.example.expenseapi.utils.ExpenseSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

@Service
public class ExpenseServiceImpl extends GenericServiceImpl<Expense, Long> implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public ExpenseServiceImpl(ExpenseRepository repository, CategoryRepository categoryRepository, CurrencyRepository currencyRepository, UserRepository userRepository, MembershipRepository membershipRepository) {
        super(repository);
        this.expenseRepository = repository;
        this.categoryRepository = categoryRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Expense> getExpensesByEmail(String mail) {
        return expenseRepository.findByUserEmail(mail);
    }

    @Override
    public Expense save(Expense entity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(entity::setUser);
        if (entity.getCategory() == null) {
            Category defaultCategory = categoryRepository.findById(1L)
                    .orElseGet(() -> categoryRepository.save(new Category()));
            entity.setCategory(defaultCategory);
        }
        if (entity.getCurrency() == null) {
            Currency defaultCurrency = currencyRepository.findById(1L)
                    .orElseGet(() -> currencyRepository.save(new Currency()));
            entity.setCurrency(defaultCurrency);
        }
        return super.save(entity);
    }

    @Override
    public List<Expense> getExpensesForGroup(String name) {
        ExpenseFilter filter = new ExpenseFilter();
        filter.setGroupName(name);
        return searchExpenses(filter);
    }

    @Override
    public ExpInfo getExpInfo(String group) {
        List<Expense> groupExpenses = getExpensesForGroup(group);
        List<Expense> userExpenses = expenseRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        double groupSum = groupExpenses.stream().mapToDouble(Expense::getPrice).sum();
        double userSum = userExpenses.stream().mapToDouble(Expense::getPrice).sum();
        return new ExpInfo(userSum, groupSum);
    }

    @Override
    public ExpInfo getExpInfo() {
        return getExpInfo(getGroupName());
    }

    @Override
    public Map<LocalDate, List<Expense>> getDateExpenseAsMap() {
        List<Expense> groupExpenses = getExpensesForGroup();
        return groupExpenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.groupingBy(
                        Expense::getDate,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    @Override
    public Map<Category, List<Expense>> getCategoryExpenseAsMap() {
        List<Expense> groupExpenses = getExpensesForGroup();
        return groupExpenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory
                ));
    }

    @Override
    public Optional<Expense> getRecentExpense() {
        return expenseRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Expense> getExpensesForGroup() {
        return getExpensesForGroup(getGroupName());
    }

    private String getGroupName() {
        return getAllGroups().getFirst().getName();
    }

    private List<BaseGroup> getAllGroups() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<User> user = userRepository.findByEmail(email);
        List<BaseGroup> groups = null;
        if (user.isPresent()) {
            groups = membershipRepository.findBaseGroupsByUser_Id(user.get().getId());
        }
        return groups;
    }

    @Override
    public Map<LocalDate, List<Expense>> getGroupExpenseAsDateMap(String name) {
        List<Expense> expenses = getExpensesForGroup(name);
        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::getDate));
    }

    @Override
    public Map<Category, List<Expense>> getGroupExpenseAsCategoryMap(String name) {
        List<Expense> expenses = getExpensesForGroup(name);
        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::getCategory));
    }

    @Override
    public List<Expense> searchExpenses(ExpenseFilter filter) {
        if (filter.getGroupName() == null || filter.getGroupName().isEmpty()) {
            filter.setGroupName(getGroupName());
        }
        if (!isGroupNameValid(filter.getGroupName())) {
            return Collections.emptyList();
        }
        Specification<Expense> spec = Specification.where(null);
        spec = spec.and(ExpenseSpecification.hasCategory(filter.getCategoryName()));
        spec = spec.and(ExpenseSpecification.dateIs(filter.getDate()));
        spec = spec.and(ExpenseSpecification.dateBetween(filter.getBeginDate(), filter.getEndDate()));
        spec = spec.and(ExpenseSpecification.priceBetween(filter.getPriceMin(), filter.getPriceMax()));
        spec = spec.and(ExpenseSpecification.hasGroup(filter.getGroupName()));
        spec = spec.and(ExpenseSpecification.isUser(filter.getEmail()));
        return expenseRepository.findAll(spec);
    }

    private boolean isGroupNameValid(String name){
        return getAllGroups().stream()
                .anyMatch(group -> group.getName().equals(name));
    }
}
