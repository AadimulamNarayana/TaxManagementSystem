package com.employee.mangement.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.mangement.dto.TaxDto;
import com.employee.mangement.entity.Employee;
import com.employee.mangement.repository.EmployeeRepository;


@Transactional
@Service
public class EmployeeService {
	
	
	@Qualifier
    private EmployeeRepository empRepository;
    
    
    private List<Employee> employees = new ArrayList<>();
    
    public EmployeeService() {
        // Initialize some sample employees for demonstration
        Employee emp1 = new Employee("81001", "Sachin", "Joshi", "sachin.joshi@gmail.com", Arrays.asList("9999999999", "8888888888", "7777777777"), LocalDate.of(2022, 1, 15), BigDecimal.valueOf(5000));
        Employee emp2 = new Employee("81002", "Vijay", "Narayan", "vijju298@gmail.com.com", Arrays.asList("6666666666", "5555555555", "4444444444"), LocalDate.of(2022, 6, 20),BigDecimal.valueOf(8000));
        Employee emp3 = new Employee("81003", "Sravan", "Kumar","sravan9090@gmail.com.com",Arrays.asList("1111111111", "2222222222", "3333333333"),  LocalDate.of(2021, 3, 10), BigDecimal.valueOf(10000));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
    }

    public List<TaxDto> calculateTaxDeductions() {
    	
       // List<Employee> employees = employeeRepository.findAll();
        List<TaxDto> taxDeductions = new ArrayList<>();

        for (Employee employee : employees) {
            TaxDto taxDeduction = calculateTaxDeduction(employee);
            taxDeductions.add(taxDeduction);
        }

        return taxDeductions;
    }

    private TaxDto calculateTaxDeduction(Employee employee) {
        TaxDto taxDeduction = new TaxDto();
        taxDeduction.setEmployeeId(employee.getEmployeeId());
        taxDeduction.setFirstName(employee.getFirstName());
        taxDeduction.setLastName(employee.getLastName());
        taxDeduction.setEmail(employee.getEmail());
        taxDeduction.setSalary(employee.getMonthlySalary());
        taxDeduction.setDoj(employee.getDoj());
        taxDeduction.setPhoneNumbers(employee.getPhoneNumbers());
        
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        
        Map<Integer, BigDecimal> salaryPerYear  =  calculateMonthsSalaryPerYear();

        BigDecimal monthlySalary = employee.getMonthlySalary();
        BigDecimal taxAmount = BigDecimal.ZERO;
        BigDecimal cessAmount = BigDecimal.ZERO;
        
        BigDecimal yearlySalary = salaryPerYear.get(currentYear);

        if (yearlySalary.compareTo(BigDecimal.valueOf(250000)) > 0) {
            if (yearlySalary.compareTo(BigDecimal.valueOf(500000)) <= 0) {
                taxAmount = yearlySalary.subtract(BigDecimal.valueOf(250000)).multiply(BigDecimal.valueOf(0.05));
            } else if (yearlySalary.compareTo(BigDecimal.valueOf(1000000)) <= 0) {
                taxAmount = BigDecimal.valueOf(12500).add(yearlySalary.subtract(BigDecimal.valueOf(500000)).multiply(BigDecimal.valueOf(0.10)));
            } else {
                taxAmount = BigDecimal.valueOf(87500).add(yearlySalary.subtract(BigDecimal.valueOf(1000000)).multiply(BigDecimal.valueOf(0.20)));
            }
        }

        if (taxAmount.compareTo(BigDecimal.ZERO) > 0) {
            cessAmount = taxAmount.multiply(BigDecimal.valueOf(0.04));   // Cess is 4% of tax amount
        }

        taxDeduction.setTaxAmount(taxAmount);
        taxDeduction.setCessAmount(cessAmount);
        
        
        return taxDeduction;
    }
    
    
    public Map<Integer, BigDecimal> calculateMonthsSalaryPerYear() {
    	
       // List<Employee> employees = employeeRepository.findAll();
        int totalAbsents = 24;
        // Group employees by year and sum salary for each year
        Map<Integer, BigDecimal> salaryPerYear = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDoj().getYear(),
                        Collectors.mapping(Employee::getMonthlySalary, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        // Calculate months of salary drawn for each year
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        for (int year = 2000; year <= currentYear; year++) {
            BigDecimal totalSalary = salaryPerYear.getOrDefault(year, BigDecimal.ZERO);
            int monthsWorked = (year == currentYear) ? currentMonth : 12;
            int totaldays = monthsWorked *30;
            int woringDays = totaldays - totalAbsents;
            float months = woringDays/30;
            @SuppressWarnings("deprecation")
			BigDecimal monthsSalary = totalSalary.divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(months));
            
            salaryPerYear.put(year, monthsSalary);
        }
        
        return salaryPerYear;
    }

}
