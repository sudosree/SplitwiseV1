package com.splitwise.controllers;

import com.splitwise.models.Expense;
import com.splitwise.models.User;
import com.splitwise.repositories.IExpenseRepo;
import com.splitwise.repositories.IUserRepo;
import com.splitwise.strategies.SplittingStrategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpenseController {

    private IUserRepo userRepo;
    private IExpenseRepo expenseRepo;

    public ExpenseController(IUserRepo userRepo, IExpenseRepo expenseRepo) {
        this.userRepo = userRepo;
        this.expenseRepo = expenseRepo;
    }

    public void createExpense(String expenseName, String ownerUser, List<String> participants,
                                 double amountPaid, SplittingStrategy splittingStrategy) throws Exception {

        User currentUser = this.userRepo.findUserById(ownerUser);
        if (currentUser == null) {
            throw new Exception("User doesn't exists");
        }
        boolean found = this.userRepo.findUsers(participants);
        if (!found) {
            throw new Exception("One or more users not found");
        }
        Set<User> users = this.userRepo.getUsersById(participants);
        Expense expense = new Expense(expenseName, currentUser, users, amountPaid);
        expense = splittingStrategy.split(expense);
        this.expenseRepo.addExpense(ownerUser, expense);
    }

    public Set<Expense> getExpenseHistoryByUser(String userId) throws Exception {
        User currentUser = this.userRepo.findUserById(userId);
        if (currentUser == null) {
            throw new Exception("User doesn't exists");
        }
        return this.expenseRepo.getExpensesByUser(userId);
    }

    public Map<String, Double> getBalanceHistoryByUser(String userId) throws Exception {
        User currentUser = this.userRepo.findUserById(userId);
        if (currentUser == null) {
            throw new Exception("User doesn't exists");
        }
        return this.expenseRepo.getBalancesByUser(userId);
    }
}
