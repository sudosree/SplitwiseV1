package com.splitwise.strategies;

import com.splitwise.models.Expense;

public interface SplittingStrategy {

    Expense split(Expense expense);
}
