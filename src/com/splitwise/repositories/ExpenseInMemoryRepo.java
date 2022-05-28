package com.splitwise.repositories;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.*;

public class ExpenseInMemoryRepo implements IExpenseRepo {

    private Map<String, Set<Expense>> userExpensesList;
    private Map<String, Map<String, Double>> userGetBackList;

    public ExpenseInMemoryRepo() {
        this.userExpensesList = new HashMap<>();
        this.userGetBackList = new HashMap<>();
    }

    @Override
    public Set<Expense> getExpensesByUser(String userId) {
        return this.userExpensesList.get(userId);
    }

    @Override
    public void addExpense(String userId, Expense expense) {
        if (!this.userExpensesList.containsKey(userId)) {
            this.userExpensesList.put(userId, new HashSet<>());
        }
        Set<Expense> expenses = this.userExpensesList.get(userId);
        expenses.add(expense);
        this.userExpensesList.put(userId, expenses);

        addUserGetBackAmount(userId, expense);

    }

    @Override
    public Map<String, Double> getBalancesByUser(String userId) {
        return this.userGetBackList.get(userId);
    }

    private void addUserGetBackAmount(String paidBy, Expense expense) {
        if (!this.userGetBackList.containsKey(paidBy)) {
            this.userGetBackList.put(paidBy, new HashMap<>());
        }
        // map of users owes how much amount to the owner user for this expense
        Map<User, Double> users = expense.getUsersOweAmount();
        for (User user : users.keySet()) {
            // map of users owes how much amount to the owner user in total
            Map<String, Double> userOwes = this.userGetBackList.get(paidBy);
            String paidTo = user.getUserId();
            double oweAmount = users.get(user);
            userOwes.put(paidTo, userOwes.getOrDefault(paidTo, 0.0) + oweAmount);
            if (!this.userGetBackList.containsKey(paidTo)) {
                this.userGetBackList.put(paidTo, new HashMap<>());
            }
            userOwes = this.userGetBackList.get(paidTo);
            userOwes.put(paidBy, userOwes.getOrDefault(paidBy, 0.0) - oweAmount);
        }
    }
}
