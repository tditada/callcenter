package com.almundo.model;

public class Operator extends Employee{
	
	public Operator(String name, Callcenter center) {
		super(name, center);
		this.rank = EmployeeRank.OPERATOR;
	}
	
}
