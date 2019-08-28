package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.Company;
import com.tw.apistackbase.Employee;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	//获得公司列表
	List<Employee> employees = new ArrayList<Employee>();
	List<Company> companys = new ArrayList<Company>();
	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public List<Company> getCompanys(){
		employees.add(new Employee(1,"alibaba1",20,"male",6000));
		employees.add(new Employee(2,"alibaba2",30,"male",8000));
		companys.add(new Company("001","alibaba",200,employees));
		companys.add(new Company("002","baidu",300,employees));
		return companys;
	}
	
	
	//获得某个特定的公司
	@GetMapping(path = "/{companyId}")
	public ResponseEntity<Company> getDetatilComapny(@PathVariable String companyId){
		
		for(Company company:companys) {
			if(company.getCompanyId().equals(companyId)) {
				return ResponseEntity.ok(company);
				
			}
			
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	//获得某个公司下所有的员工
	@GetMapping(path = "/{companyId}/employees")
	public ResponseEntity<List<Employee>> getCompanyEmployees(@PathVariable String companyId){
		List<Employee> employees = null;
		for(int i = 0;i < companys.size();i++) {
			if(companys.get(i).getCompanyId().equals(companyId)) {
				employees = companys.get(i).getEmployee();
				return ResponseEntity.ok(employees);
				
			}
			
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		
	}
		
	//分页查询
	@GetMapping(path = "")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Company>CompanysPage(@RequestParam int page,int pageSize){
		List<Company> companysPage = new ArrayList<Company>();
		int first = (page - 1) * pageSize + 1;
		int end = (page - 1) * pageSize + pageSize;
		if(end > companys.size()) {
			end = companys.size();	
		}
		for(int i = first-1;i < end;i++) {
			companysPage.add(companys.get(i));
		}
		
		return companysPage;
	}
		
//	//添加一个公司
	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Company> addCompany(@RequestBody Company addcompany){
			companys.add(addcompany);
		return companys;
		
	}
	
	
	//更新一个公司的基本信息
	
	@PutMapping(path = "/{companyId}")
	public ResponseEntity<Company> updateCompany(@PathVariable String companyId){
		
		for(Company company:companys) {
			if(company.getCompanyId().equals(companyId)) {
				company.setCompanyName("tenxun");
				company.setEmployeeNumber(500);
				return ResponseEntity.ok(company);
			}
			
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		
		
	}
	
	
	//删除某个特定的公司
	@DeleteMapping(path ="/{companyId}")
	public ResponseEntity<Company> deleteCompany(@PathVariable String companyId){
		for(Company company:companys) {
			if(company.getCompanyId().equals(companyId)) {
				companys.remove(company);
				return new ResponseEntity<Company>(HttpStatus.OK);
				
			}
			
		}
		
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		
	}
	
}
