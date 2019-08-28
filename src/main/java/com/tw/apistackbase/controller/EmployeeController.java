package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/employees")
public class EmployeeController {
	//获得员工列表
	List<Employee> employees = new ArrayList<Employee>();
	List<Company> companys = new ArrayList<Company>();
	//Company[] companys = new Company[2];
	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees(){
		employees.add(new Employee(1,"alibaba1",20,"male",6000));
		employees.add(new Employee(2,"alibaba2",30,"male",8000));
		companys.add(new Company("001","alibaba",200,employees));
		companys.add(new Company("002","baidu",300,employees));
		return employees;
	}
	
	
	//获得某个特定员工
	@GetMapping(path = "/{employeeId}")
	public ResponseEntity<Employee> getDetatilEmployee(@PathVariable int employeeId){
		
		for(Employee employee:employees) {
			if(employee.getId() == employeeId) {
				return ResponseEntity.ok(employee);
				
			}
			
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	//#分页查询，page等于1，pageSize等于5
		@GetMapping(path = "")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<Employee>employeesPage(@RequestParam int page,int pageSize){
			List<Employee> employeesPage = new ArrayList<Employee>();
			int first = (page - 1) * pageSize + 1;
			int end = (page - 1) * pageSize + pageSize;
			if(end > employees.size()) {
				end = employees.size();	
			}
			for(int i = first-1;i < end;i++) {
				employeesPage.add(employees.get(i));
			}
			
			return employeesPage;
		}
		
	//查询所有男性员工
	@GetMapping(path = "")
	public ResponseEntity<List<Employee>> getMaleEmployees(@PathVariable String gender){
		List<Employee> employees = null;
		for(int i = 0;i < employees.size();i++) {
			if(employees.get(i).getGender().equals(gender)) {
				employees.add(employees.get(i));
				return ResponseEntity.ok(employees);
				
			}
			
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		
	}
	
//	//添加一名员工
	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Employee> addEmployee(@RequestBody Employee addEmployee){
		employees.add(addEmployee);
		return employees;
		
	}
	
	
	//update一名员工
	
	@PutMapping(path = "/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId){
		
		for(Employee employee:employees) {
			if(employee.getId() == employeeId) {
				employee.setAge(29);;
				employee.setName("wang");;
				return ResponseEntity.ok(employee);
			}
			
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
		
	}
	
	
	//delete一名员工
	@DeleteMapping(path ="/{employeeId}")
	public ResponseEntity<Employee> deleteCompany(@PathVariable int employeeId){
		for(Employee employee:employees) {
			if(employee.getId()== employeeId) {
				employees.remove(employee);
				return new ResponseEntity<Employee>(HttpStatus.OK);
				
			}
			
		}
		
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
	}
	
}
