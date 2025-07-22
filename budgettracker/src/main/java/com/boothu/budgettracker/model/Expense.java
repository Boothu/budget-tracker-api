package com.boothu.budgettracker.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Marks that this class maps to database table
@Table(name = "expenses") // Specifies table name in database will be 'expenses'
public class Expense {

    @Id // Mark this field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Allows database to auto generate the id via auto increment
    private Long id;

    private String description;

    private double amount;

    private LocalDate date;

    public Expense() {
    }

    public Expense(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public Long getId() {
        return id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
