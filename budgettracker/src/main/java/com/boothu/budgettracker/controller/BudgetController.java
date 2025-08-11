package com.boothu.budgettracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boothu.budgettracker.dto.BudgetUsage;
import com.boothu.budgettracker.model.Budget;
import com.boothu.budgettracker.model.Expense;
import com.boothu.budgettracker.repository.BudgetRepository;
import com.boothu.budgettracker.repository.ExpenseRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

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

    // Endpoint: GET /budgets/usage?category=X&budgetMonth=Y
    @GetMapping("/usage")
    public BudgetUsage getBudgetUsage(@RequestParam String category, @RequestParam String budgetMonth) {

        // Use budgetRepository to find all the budgets for this category & month (although only 1 is expected)
        List<Budget> budgets = budgetRepository.findByCategoryAndBudgetMonth(category, budgetMonth);

        // If no matching budget found, throw exception
        if (budgets.isEmpty()) {
            throw new RuntimeException("No budget found for this category and month");
        }

        Budget budget = budgets.get(0); // assumes one budget per category & month

        // Use expenseRepository to find all the expenses for this category & month
        // 'StartsWith' because expense dates are stored like "2025-08-10", and starting with "2025-08" will catch all days in August 2025.
        List<Expense> expenses = expenseRepository.findByCategoryAndDateStartsWith(category, budgetMonth);

        // Loop through expenses and add to total
        double totalSpent = 0.0;
        for (Expense expense : expenses) {
            totalSpent += expense.getAmount();
        }

        // Calculate remaining budget
        double remaining = budget.getLimitAmount() - totalSpent;

        return new BudgetUsage(category, budgetMonth, budget.getLimitAmount(), totalSpent, remaining);
    }

    // Endpoint: DELETE /budgets/{id}
    @DeleteMapping("/{id}") // Maps this method to a DELETE request
    // @PathVariable extracts {id} from the URL
    public void deleteBudget(@PathVariable Long id) {
        budgetRepository.deleteById(id);
    }

    // Endpoint: PUT /budgets/{id}
    @PutMapping("/{id}") // Maps this method to a PUT request
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        // If given budget id exists, updates its fields and saves it back to the database
        return budgetRepository.findById(id).map(budget -> {
            budget.setCategory(updatedBudget.getCategory());
            budget.setLimitAmount(updatedBudget.getLimitAmount());
            budget.setBudgetMonth(updatedBudget.getBudgetMonth());
            return budgetRepository.save(budget);
        })
                .orElseThrow(() -> new RuntimeException("Budget not found with ID " + id));
    }

    // Endpoint: GET /budgets/total
    @GetMapping("/total")
    public Double getTotalBudget() {
        double total = 0.0;
        // Loop through every budget and add it to the total, then return it
        for (Budget budget : budgetRepository.findAll()) {
            total += budget.getLimitAmount();
        }
        return total;
    }

    // Endpoint: GET /budgets/overview/?budgetMonth=Y
    @GetMapping("/overview")
    public List<BudgetUsage> getBudgetOverview(@RequestParam String budgetMonth) {

        // Get all budgets for given month
        List<Budget> budgets = budgetRepository.findByBudgetMonth(budgetMonth);

        // Throw exception if no budgets found
        if (budgets.isEmpty()) {
            throw new RuntimeException("No budgets found for this month");
        }

        // Create list which will hold the BudgetUsage objects
        List<BudgetUsage> overviewList = new ArrayList<>();

        for (Budget budget : budgets) {
            // Create list of expenses in same category as current budget
            List<Expense> categoryExpenses = expenseRepository.findByCategoryAndDateStartsWith(budget.getCategory(), budgetMonth);

            // Loop through expenses and add to total
            double totalSpent = 0.0;
            for (Expense expense : categoryExpenses) {
                totalSpent += expense.getAmount();
            }

            // Calculate remaining budget
            double remaining = budget.getLimitAmount() - totalSpent;

            // Add data for current budget to the overview list
            overviewList.add(new BudgetUsage(budget.getCategory(), budgetMonth, budget.getLimitAmount(), totalSpent, remaining));
        }
        // Return every budget in given months usage
        return overviewList;
    }
}
