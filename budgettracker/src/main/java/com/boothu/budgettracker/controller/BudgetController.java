package com.boothu.budgettracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boothu.budgettracker.model.Budget;
import com.boothu.budgettracker.repository.BudgetRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    // Endpoint: POST /budgets
    @PostMapping
    public Budget addBudget(@Valid @RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    // Endpoint: GET /budgets
    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
}
