package com.assignment.expense_tracker.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Filter_dto {

	
	@Positive(message = "Amount must be positive")
	private Double amount;
	
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate date;
	
	
	@NotBlank(message = "Category cannot be empty")
	private String category;
}
