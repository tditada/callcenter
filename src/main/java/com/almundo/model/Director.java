package com.almundo.model;

public class Director extends Employee{
	
	public Director(String name, Callcenter center) {
		super(name, center);
		this.rank = EmployeeRank.DIRECTOR;
	}
	
}
