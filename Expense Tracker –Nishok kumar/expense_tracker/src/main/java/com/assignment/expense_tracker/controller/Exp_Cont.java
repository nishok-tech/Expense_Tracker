package com.assignment.expense_tracker.controller;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.expense_tracker.model.Expense_dto;
import com.assignment.expense_tracker.model.Expense_model;
import com.assignment.expense_tracker.repository.Category_repo;
import com.assignment.expense_tracker.repository.Expense_Repo;
import com.assignment.expense_tracker.service.Expense_service;

import jakarta.validation.Valid;

@Controller
public class Exp_Cont {

	@Autowired
	Category_repo cr;
	
	@Autowired
	Expense_Repo expense_Repo;
	
	@Autowired
	Expense_service exp_service;
	
	@GetMapping("/")
	public String ExpensePage(Model m)
	{
		
		m.addAttribute("expenseDto", new Expense_dto());
		return "expense";
	}
	
	@GetMapping("/alter_expense")
	public String ExpenseAlterPage(Model m)
	{
		
		return "expense_alter";
	}
	
	
	@GetMapping("/all_Expenses")
	public String ExpensesList(Model m)
	{
		List<Expense_model> expenses = expense_Repo.findAll();
		double sum = expenses.stream().mapToDouble(Expense_model::getAmount).sum();
		m.addAttribute("expenses", expenses);
		m.addAttribute("sum", sum);
		return "all_Expenses";
	}
	
	
	@PostMapping("add")
	public String addExpense(@Valid @ModelAttribute("expenseDto") Expense_dto expenseDto,
			BindingResult result,
			Model m
			) 
	{
		if(result.hasErrors()) {
			return "expense";
		}
		
		Expense_model em = new Expense_model(expenseDto.getAmount(),expenseDto.getDate(),expenseDto.getNote(),
				expenseDto.getCategory());
		expense_Repo.save(em);
		m.addAttribute("msg", "Expense Added sucessfully");
		return "expense";
	}
	
	@GetMapping("/filter_expenses")
	public String filter_expenses(@RequestParam(name="fdate", required=false) LocalDate fdate,
			@RequestParam(name="tdate", required=false) LocalDate tdate,
			@RequestParam(name="MinAmt", required=false) Double MinAmt,
			@RequestParam(name="MaxAmt", required=false) Double MaxAmt,
			@RequestParam(name="category", required=false) String category,
			Model m)
	{
		List<Expense_model> expenses = expense_Repo.findAll();
		
		if((!(fdate == null)) && (!(tdate == null)) && fdate.isAfter(tdate) ) {
			m.addAttribute("err", "\"From-date\" is greater than \"To-date\" !! ");
			
			return "redirect:/all_Expenses";
		}
		
		if((!(MinAmt == null)) && (!(MaxAmt == null)) && MinAmt > MaxAmt) {
			m.addAttribute("err", "\"Min-Amount\" is greater than \"Max-Amount\" !! ");
			m.addAttribute("expenses", expenses);
			return "all_Expenses";
		}
		
		if(!(fdate == null)) {
			expenses = expenses.stream().filter(e -> !e.getDate().isAfter(fdate))
					.toList();
			m.addAttribute("fdate",fdate);
		}
		
		if(!(tdate == null)) {
			expenses = expenses.stream().filter(e -> !e.getDate().isAfter(tdate)).toList();
			m.addAttribute("tdate",tdate);
		}
		
		if(!(MinAmt == null)) {
			expenses = expenses.stream().filter(e -> (e.getAmount() >= MinAmt)).toList();
			m.addAttribute("MinAmt",MinAmt);
		}
		
		if(!(MaxAmt == null)) {
			expenses = expenses.stream().filter(e -> (e.getAmount() <= MaxAmt)).toList();
			
		}
		
		if(!category.isEmpty()) {
			expenses = expenses.stream().filter(e -> e.getCategory().equalsIgnoreCase(category)).toList();
			m.addAttribute("category", category);
		}
		
		double sum = expenses.stream().mapToDouble(Expense_model::getAmount).sum();
		m.addAttribute("sum",sum);
		
		
		
		m.addAttribute("expenses", expenses);
		return "all_Expenses";
	}
	
	@GetMapping("/search")
	public String viewExpense(@RequestParam String id, Model m) 
	{
//		check id is not blank 
		if(id.trim().isEmpty() || id == null) {
			m.addAttribute("err","Id required");
			return "expense_alter";
		}
		
		
//		parse expenseId from input
		int expense_ID;
		try {
			expense_ID = Integer.parseInt(id);
		}
		catch(Exception e) {
			m.addAttribute("err","Invalid ID format, enter a number");
			return "expense_alter";
		}
		
		
//		find optional expense by id
		Optional<Expense_model> expenseOpt =  expense_Repo.findById(expense_ID);
		if(expenseOpt.isEmpty()) {
			m.addAttribute("err","Expense Id doesn't exist");
			return "expense_alter";
		}
		
//		get expense from optional expense
		Expense_model exp = expenseOpt.get();
		
//		convert into expense_Dto
		Expense_dto expenseDto = new Expense_dto(
				exp.getAmount(),exp.getDate(),exp.getNote(),exp.getCategory());
		
		m.addAttribute("expenseDto",expenseDto);
		m.addAttribute("id",Integer.valueOf(id));
		return "expense_alter";
		
	}
	
	@PostMapping("/update/{id}")
	public String updExpense(@PathVariable int id,
			@Valid @ModelAttribute("expenseDto") Expense_dto expenseDto,
			BindingResult result,
			Model m
			) 
	{
		if(result.hasErrors()) {
			return "expense_alter";
		}
		
		Expense_model em = new Expense_model(expenseDto.getAmount(),expenseDto.getDate(),expenseDto.getNote(),
				expenseDto.getCategory());
		em.setId(id);
		
		expense_Repo.save(em);
		m.addAttribute("msg", "Expense Updated sucessfully");
		return "expense_alter";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable int id,Model m)
	{
		expense_Repo.deleteById(id);
		m.addAttribute("msg", "Expense deleted sucessfully");
		return "expense_alter";
	}
}
