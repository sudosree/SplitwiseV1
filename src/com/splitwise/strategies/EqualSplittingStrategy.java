package com.splitwise.strategies;

import com.splitwise.models.Expense;
import com.splitwise.models.User;
import java.util.Set;

public class EqualSplittingStrategy implements SplittingStrategy {

    @Override
    public Expense split(Expense expense) {
        Set<User> users = expense.getUsers();
        int splitBy = users.size() + 1; // including the owner user
        double amountPaid = expense.getAmountPaid();
        double amountSplit = Math.round((amountPaid / splitBy) * 100.0) / 100.0;
        for (User user : users) {
            expense.addOweAmount(user, amountSplit);
        }
        return expense;
    }
}
