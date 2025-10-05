package com.assignment.expense_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.expense_tracker.model.Expense_dto;
import com.assignment.expense_tracker.model.Expense_model;
import com.assignment.expense_tracker.service.Expense_service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("expense")
public class Expense_cont {
	
	@Autowired
	Expense_service expense_service;
	
	
	
//	@PostMapping("add")
//	public ResponseEntity<String> addExpense(@Valid @ModelAttribute("expense_dto") Expense_dto expense_dto,
//			BindingResult result,
//			Model m
//			) 
//	{
//		if(result.hasErrors()) {
//			
//		}
//		return expense_service.addExpense(expense);
//	}
	
	@GetMapping("view/{id}")
	public ResponseEntity<Expense_model> viewExpense(@PathVariable int id) 
	{
		return expense_service.searchExpense(id);
	}
	
	@PostMapping("update")
	public ResponseEntity<String> updateExpense(@RequestBody Expense_model expense) 
	{
		return expense_service.updateExpense(expense);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<String>  deleteExpense(@PathVariable int id) 
	{
		return expense_service.deleteExpense(id);
	}
	
	@GetMapping("view/all_Expenses")
	public ResponseEntity<List<Expense_model>> viewAllExpense() 
	{
		return expense_service.viewAllExpense();
	}

}
