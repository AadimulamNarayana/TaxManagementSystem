package com.employee.mangement.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.mangement.dto.TaxDto;
import com.employee.mangement.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService empService;
    
   
    @GetMapping("/taxDeductions")
    public ResponseEntity<List<TaxDto>> getYearlyTaxDeductions() {
        List<TaxDto> taxDeductions = empService.calculateTaxDeductions();
        return ResponseEntity.ok(taxDeductions);
    }
     
    @GetMapping("/lossOfPay")
    public String calculateLossOfPay(@RequestParam Float salary) {
    	DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(salary/30);
    }
}
