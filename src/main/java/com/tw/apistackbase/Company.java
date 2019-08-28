package com.tw.apistackbase;

import java.util.List;

public class Company {
	
	private String companyId;
	private String companyName;
	private int employeeNumber;
	private List<Employee> employee;
	
	
	
	public Company(String companyId, String companyName, int employeeNumber, List<Employee> employee) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.employeeNumber = employeeNumber;
		this.employee = employee;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
	

}
