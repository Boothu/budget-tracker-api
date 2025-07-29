package com.boothu.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boothu.budgettracker.model.Expense;

@Repository // Marks this interface as a Spring component for data access
// By extending JpaRepository, the repository for Expense inherits methods for common database operations
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
