package com.boothu.budgettracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Endpoint: DELETE /expenses/{id}
    @DeleteMapping("/{id}") // Maps this method to a DELETE request
    // @PathVariable extracts {id} from the URL
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }

    // Endpoint: PUT /expenses/{id}
    @PutMapping("/{id}") // Maps this method to a PUT request
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        // If given expense id exists, updates its fields and saves it back to the database
        return expenseRepository.findById(id).map(expense -> {
            expense.setDescription(updatedExpense.getDescription());
            expense.setAmount(updatedExpense.getAmount());
            expense.setDate(updatedExpense.getDate());
            return expenseRepository.save(expense);
        })
                .orElseThrow(() -> new RuntimeException("Expense not found with ID " + id));
    }
}
