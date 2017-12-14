package com.almundo.model;

import java.util.concurrent.ExecutionException;

public class Callcenter {
	
	private Dispatcher dispatcher;
	private Integer operators;
	private Integer supervisors;
	private Integer directors;
	private Integer calls = 0;
	
	public Callcenter(Integer operators, Integer supervisors, Integer directors) {
		this.operators = operators;
		this.supervisors = supervisors;
		this.directors = directors;
		this.dispatcher = new Dispatcher(this);
	}

	public Integer getOperators() {
		return operators;
	}
	
	public Integer getSupervisors() {
		return supervisors;
	}
	
	public Integer getDirectors() {
		return directors;
	}
	
	public int manageCall() throws InterruptedException, ExecutionException {
		Call c = new Call(new Client("Client N" + calls));
		dispatcher.dispatchCall(c);
		return this.calls++;
	}
	
	public void callEnd(Employee e) {
		dispatcher.freeEmployee(e);
	}
	
	public void endShift() {
		this.dispatcher.endShift();
	}
	
}