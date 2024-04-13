package com.employee.mangement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import lombok.Data;


@Data
public class TaxDto {
	
	
	@NotBlank(message = "EmployeeId is mandatory")
    private String employeeId;

    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    private String lastName; 

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "At least one phone number is required")
    private List<String> phoneNumbers;

    @NotNull(message = "DOJ is mandatory")
    @Past(message = "DOJ must be in the past")
    private LocalDate doj;

    @NotNull(message = "Salary is mandatory")
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;
    
    
     private BigDecimal taxAmount;
     private BigDecimal cessAmount;
     private BigDecimal yearlySalary;

     
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(BigDecimal cessAmount) {
		this.cessAmount = cessAmount;
	}

	public BigDecimal getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(BigDecimal yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
