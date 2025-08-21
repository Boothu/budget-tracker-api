package com.boothu.budgettracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boothu.budgettracker.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    // Spring Data JPA reads the method name and automatically generates a query based on it
    List<Budget> findByCategoryAndBudgetMonth(String category, String budgetMonth);

    public List<Budget> findByBudgetMonth(String budgetMonth);

}
