package com.almundo.model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
	
	private PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>();
	private ExecutorService pool;
	
	public Dispatcher(Callcenter c) {
		this.pool = Executors.newFixedThreadPool(c.getOperators() + c.getSupervisors() + c.getDirectors());
		for (int i = 0; i < c.getOperators(); i++) {
			employees.add(new Operator("Operator N°" + i, c));
		}
		for (int i = 0; i < c.getSupervisors(); i++) {
			employees.add(new Supervisor("Supervisor N°" + i, c));
		}
		for (int i = 0; i < c.getDirectors(); i++) {
			employees.add(new Director("Director N°" + i, c));
		}
	}
	

	public void dispatchCall(Call c) throws InterruptedException, ExecutionException { 
		Employee e = employees.poll();
		while (e == null) {
			c.waitMusic(); 
			e = employees.poll(30, TimeUnit.SECONDS);
		}
		c.assignedEmployee(e); 
		pool.submit(c);
	}
	
	public void freeEmployee(Employee e) {
		employees.put(e);
	}
	
	public void endShift() {
		if (this.pool != null) {
			pool.shutdown();
		}
	}

}