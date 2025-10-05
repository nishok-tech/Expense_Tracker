package com.assignment.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.expense_tracker.model.Expense_model;

@Repository
public interface Expense_Repo extends JpaRepository<Expense_model, Integer>{

}
