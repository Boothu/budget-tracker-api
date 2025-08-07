package com.boothu.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boothu.budgettracker.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
