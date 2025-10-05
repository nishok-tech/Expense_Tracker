package com.assignment.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.expense_tracker.model.Category;

@Repository
public interface Category_repo extends JpaRepository<Category, Integer>{

}
