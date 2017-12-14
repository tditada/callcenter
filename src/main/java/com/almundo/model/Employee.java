package com.almundo.model;

public class Employee implements Comparable<Employee>{
	
	private String name;
	protected EmployeeRank rank;
	private boolean free;
	private Callcenter center;
	
	public Employee(String name, Callcenter center) {
		this.name = name;
		this.center = center;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public EmployeeRank getRank() {
		return rank;
	}
	
	public void endCall() {
		this.free = true;
		center.callEnd(this);
	}
	
	public void enterCall() {
		this.free = false;
	}
	
	public int compareTo(Employee e) {
		return rank.compareTo(e.getRank());
	}
	
}
