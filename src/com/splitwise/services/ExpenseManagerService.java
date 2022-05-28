package com.splitwise.services;

import com.splitwise.controllers.ExpenseController;
import com.splitwise.controllers.UserController;
import com.splitwise.models.Expense;
import com.splitwise.models.User;
import com.splitwise.strategies.SplittingStrategy;
import com.splitwise.strategies.SplittingStrategyFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpenseManagerService {

    private UserController userController;
    private ExpenseController expenseController;

    public ExpenseManagerService(UserController userController, ExpenseController expenseController) {
        this.userController = userController;
        this.expenseController = expenseController;
    }

    public void registerUser(String userId, String name, String phoneNo, String password) throws Exception {
        this.userController.registerUser(userId, name, phoneNo, password);
    }

    public void createExpense(String name, String owner, List<String> users,
                              double amountPaid, String splittingType) throws Exception {
        SplittingStrategy splittingStrategy = SplittingStrategyFactory.getStrategy(splittingType);
        this.expenseController.createExpense(name, owner, users, amountPaid, splittingStrategy);
    }

    public void showExpenseHistoryByUser(String userId) throws Exception {
        Set<Expense> expenses = this.expenseController.getExpenseHistoryByUser(userId);
        for (Expense expense : expenses) {
            Map<User, Double> paidToUsers = expense.getUsersOweAmount();
            for (User user : paidToUsers.keySet()) {
                printExpense(expense.getOwner().getUserId(), expense.getAmountPaid(),
                        expense.getName(), user.getUserId(), paidToUsers.get(user));
            }
        }
    }

    public void showBalanceHistoryByUser(String userId) throws Exception {
        Map<String, Double> userOwesAmount = this.expenseController.getBalanceHistoryByUser(userId);
        for (String paidTo : userOwesAmount.keySet()) {
            printBalance(userId, paidTo, userOwesAmount.get(paidTo));
        }
    }

    private void printExpense(String paidBy, double amountPaid, String expenseName, String paidTo, double amountOwed) {
        System.out.println("User " + paidBy + " paid " + amountPaid + " for " + expenseName +
                " and get back " + amountOwed + " from user " + paidTo);
    }

    private void printBalance(String paidBy, String paidTo, double amountOwe) {
        System.out.println("User " + paidTo + " owes User " + paidBy + " " + amountOwe);
    }
}
