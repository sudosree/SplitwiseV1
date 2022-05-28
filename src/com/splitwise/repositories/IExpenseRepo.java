package com.splitwise.repositories;

import com.splitwise.models.Expense;

import java.util.Map;
import java.util.Set;

public interface IExpenseRepo {

    Set<Expense> getExpensesByUser(String userId);

    void addExpense(String userId, Expense expense);

    Map<String, Double> getBalancesByUser(String userId);
}
