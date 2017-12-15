package com.almundo.model;

/**
 * @author Teresa Celina di Tada
 *
 */
public class Supervisor extends Employee{
	
	public Supervisor(String name, Callcenter center) {
		super(name, center);
		this.rank = EmployeeRank.SUPERVISOR;
	}
	
}
