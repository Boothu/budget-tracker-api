package com.boothu.budgettracker.dto;

public class BudgetUsage {

    private final String category;
    private final String budgetMonth;
    private final double limit;
    private final double spent;
    private final double remaining;

    public BudgetUsage(String category, String budgetMonth, double limit, double spent, double remaining) {
        this.category = category;
        this.budgetMonth = budgetMonth;
        this.limit = limit;
        this.spent = spent;
        this.remaining = remaining;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public String getBudgetMonth() {
        return budgetMonth;
    }

    public double getLimit() {
        return limit;
    }

    public double getSpent() {
        return spent;
    }

    public double getRemaining() {
        return remaining;
    }
}
