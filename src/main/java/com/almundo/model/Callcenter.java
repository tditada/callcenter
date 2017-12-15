package com.almundo.model;

/**
 * @author Teresa Celina di Tada
 *
 */
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
	
	public void addOperators(Integer toAdd) {
		dispatcher.addOperators(toAdd, this);
		this.operators += toAdd;
	}
	
	/** Sends the incoming call to the dispatcher 
	 * 
	 * @see com.almundo.model.Dispatcher
	 * @return total numbers of call managed
	 * @throws InterruptedException - Call was interrupted
	 * @throws EmptyCenterException 
	 */
	public void manageCall() throws InterruptedException, EmptyCenterException {
		Call c = new Call(new Client("Client N" + calls));
		dispatcher.dispatchCall(c);
	}
	
	/**
	 * Ends a call and tells the dispatcher to free an employee
	 * @param e - Employee that was in a call that recently end
	 */
	public void callEnd(Employee e) {
		dispatcher.freeEmployee(e);
	}
	
	/**
	 * Close the shift for the day
	 */
	public void endShift() {
		this.dispatcher.endShift();
	}
	
}