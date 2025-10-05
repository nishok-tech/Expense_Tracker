package com.assignment.expense_tracker.service;


import java.util.List;
import java.util.Optional;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.expense_tracker.model.Expense_dto;
import com.assignment.expense_tracker.model.Expense_model;
import com.assignment.expense_tracker.repository.Expense_Repo;

@Service
public class Expense_service {

	@Autowired
	Expense_Repo expense_Repo;
	
	public ResponseEntity<String> addExpense(Expense_model expense)
	{
		if(expense.equals(null)) {
			return new ResponseEntity<>("Cannot insert null", HttpStatus.BAD_REQUEST);
		}
		else
		{
			try {
				expense_Repo.save(expense);
			}
			catch(Exception ex) {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Expense added sucessfully", HttpStatus.OK);
		}
	}
	
	public ResponseEntity<Expense_model> searchExpense(int id)
	{
		Expense_model exp =  expense_Repo.findById(id).get();
		if(exp.equals(null)) {
			return new ResponseEntity<>(new Expense_model(), HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(exp, HttpStatus.OK);
		}
		
	}
	
	
	public ResponseEntity<String> updateExpense(Expense_model expense)
	{
		if(expense.equals(null)) {
			return new ResponseEntity<>("Cannot update expense", HttpStatus.BAD_REQUEST);
		}
		else
		{
			Expense_model exp = expense_Repo.findById(expense.getId()).get();
			exp.setAmount(expense.getAmount());
			exp.setDate(expense.getDate());
			exp.setNote(expense.getNote());
			exp.setCategory(expense.getCategory());
			expense_Repo.save(exp);
			return new ResponseEntity<>("Expense Updated sucessfully", HttpStatus.OK);
		}
	}
	
	
	public ResponseEntity<String> deleteExpense(int id)
	{
		Expense_model exp =  expense_Repo.findById(id).get();
		if(exp.equals(null)) {
			return new ResponseEntity<>("Expense id doesn't exist", HttpStatus.BAD_REQUEST);
		}
		else {
			expense_Repo.delete(exp);
			return new ResponseEntity<>("Expense deleted sucessfully", HttpStatus.OK);
		}
		
	}

	public ResponseEntity<List<Expense_model>>  viewAllExpense() {
		List<Expense_model> expenses = expense_Repo.findAll();
		return new ResponseEntity<>(expenses, HttpStatus.OK);
	}

	
	
	
	
	
}
