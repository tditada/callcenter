package com.almundo.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Teresa Celina di Tada
 *
 */
public class Dispatcher {
	
	private PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>();
	private ExecutorService pool;
	private static Integer MIN_SIZE = 10;
	private static Integer MAX_TIME_SECONDS = 60;
	
	/**
	 * Creates a dispatcher related to the Call Center with a fixed thread pool with fixed size.
	 * @param center - Call Center related to the dispatcher
	 */
	public Dispatcher(Callcenter center) {
		Integer size = center.getOperators() + center.getSupervisors() + center.getDirectors();
		this.pool = Executors.newFixedThreadPool(size > MIN_SIZE ? size : MIN_SIZE);
		for (int i = 0; i < center.getOperators(); i++) {
			employees.add(new Operator("Operator N°" + i, center));
		}
		for (int i = 0; i < center.getSupervisors(); i++) {
			employees.add(new Supervisor("Supervisor N°" + i, center));
		}
		for (int i = 0; i < center.getDirectors(); i++) {
			employees.add(new Director("Director N°" + i, center));
		}
	}

	/**
	 * Gets an employee from the queue based on their EmployeeRank and assigned it to the call.
	 * If none is available it will play waiting music.
	 * 
	 * @param call - Incoming call
	 * @return Call that was dispatched
	 * @throws InterruptedException 
	 * @throws EmptyCenterException 
	 */
	public void dispatchCall(Call call) throws EmptyCenterException, InterruptedException {
		Employee e = employees.poll();
		Integer time = 0;
		while (e == null && time <= MAX_TIME_SECONDS) {
			call.waitMusic(); 
			e = employees.poll(30, TimeUnit.SECONDS);
			time += 30;
		}
		
		if (time > MAX_TIME_SECONDS) {
			throw new EmptyCenterException("No employees");
		}
		call.assignedEmployee(e);
		pool.submit(call);
	}
	
	
	/**
	 * Returns an employee to the queue
	 * @param e - Employee available
	 */
	public void freeEmployee(Employee e) {
		employees.put(e);
	}
	
	/**
	 * Shuts down the pool that manage incoming calls.
	 */
	public void endShift() {
		if (this.pool != null) {
			pool.shutdown();
		}
	}

	public void addOperators(Integer toAdd, Callcenter center) {
		for (int i = 0; i < toAdd; i++) {
			employees.add(new Operator("New Operator " + i, center));
		}
	}
	
	public void addSupervisors(Integer toAdd, Callcenter center) {
		for (int i = 0; i < toAdd; i++) {
			employees.add(new Supervisor("New Supervisor " + i, center));
		}
	}
	public void addDirectors(Integer toAdd, Callcenter center) {
		for (int i = 0; i < toAdd; i++) {
			employees.add(new Director("New Director " + i, center));
		}
	}

}