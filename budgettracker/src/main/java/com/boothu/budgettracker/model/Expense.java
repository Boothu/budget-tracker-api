package com.boothu.budgettracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity // Marks that this class maps to database table
@Table(name = "expenses") // Specifies table name in database will be 'expenses'
public class Expense {

    @Id // Mark this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Allows database to auto generate the id via auto increment
    private Long id;

    @NotBlank(message = "Category is required")
    private String category;

    private String description;

    @Positive(message = "Amount must be positive")
    private double amount;

    // E.g. '2025-08-10'
    @NotNull(message = "Date is required")
    private String date;

    public Expense() {
    }

    public Expense(String category, String description, double amount, String date) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    @NotBlank
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
