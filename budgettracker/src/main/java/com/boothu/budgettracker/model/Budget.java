package com.boothu.budgettracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Amount must be positive")
    private double limitAmount;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])$", message = "Budget month must be in YYYY-MM format")
    @NotBlank(message = "Budget Month is required")
    private String budgetMonth;

    public Budget() {
    }

    public Budget(String category, double limitAmount, String budgetMonth) {
        this.category = category;
        this.limitAmount = limitAmount;
        this.budgetMonth = budgetMonth;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getBudgetMonth() {
        return budgetMonth;
    }

    public void setBudgetMonth(String budgetMonth) {
        this.budgetMonth = budgetMonth;
    }
}
