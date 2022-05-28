package com.splitwise.strategies;

public class SplittingStrategyFactory {

    public static SplittingStrategy getStrategy(String splittingType) {
        if (splittingType.equals("EQUAL")) {
            return new EqualSplittingStrategy();
        }
        return null;
    }
}
