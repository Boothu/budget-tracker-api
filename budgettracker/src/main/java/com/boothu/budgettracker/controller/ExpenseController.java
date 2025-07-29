package com.boothu.budgettracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boothu.budgettracker.model.Expense;
import com.boothu.budgettracker.repository.ExpenseRepository;

@RestController // Marks class as a REST controller
@RequestMapping("/expenses") // Classes methods are accessible via this URL path
public class ExpenseController {

    @Autowired // Injects instance of ExpenseRepository so its methods can be used
    private ExpenseRepository expenseRepository;

    // Endpoint: POST /expenses
    // Accepts a JSON object in the request body, converts it to an Expense object, and saves it to the database
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    // Endpoint: GET /expenses
    // Returns a list of all expenses stored in the database
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
