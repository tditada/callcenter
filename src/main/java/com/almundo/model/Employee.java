package com.almundo.model;

/**
 * @author Teresa Celina di Tada
 *
 */
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
	
	/**
	 * Finish a call
	 */
	public void endCall() {
		this.setFree(true);
		center.callEnd(this);
	}
	
	/**
	 * Stars a call
	 */
	public void enterCall() {
		this.setFree(false);
	}
	
	public int compareTo(Employee e) {
		return rank.compareTo(e.getRank());
	}

	private boolean isFree() {
		return free;
	}

	private void setFree(boolean free) {
		this.free = free;
	}
	
}
