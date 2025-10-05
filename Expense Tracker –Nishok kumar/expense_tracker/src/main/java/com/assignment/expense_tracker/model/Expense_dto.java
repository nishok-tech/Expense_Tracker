package com.assignment.expense_tracker.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Expense_dto {

	@NotNull(message = "Amount cannot be empty")
	@Positive(message = "Amount must be positive")
	private Double amount;
	
	@NotNull(message = "Date cannot be empty")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate date;
	
	
	@NotBlank(message = "Note cannot be empty")
	private String note;
	
	@NotBlank(message = "Category cannot be empty")
	private String category;
	
	

	public Expense_dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense_dto(
			@NotNull(message = "Amount cannot be empty") @Positive(message = "Amount must be positive") Double amount,
			@NotNull(message = "Date cannot be empty") LocalDate date,
			@NotBlank(message = "Note cannot be empty") String note,
			@NotBlank(message = "Category cannot be empty") String category) {
		super();
		this.amount = amount;
		this.date = date;
		this.note = note;
		this.category = category;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
