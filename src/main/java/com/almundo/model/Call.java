package com.almundo.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Teresa Celina di Tada
 *
 */
public class Call implements Runnable {
	
	private static int MIN_DURATION = 5;
	private static int MAX_DURATION = 10;
	private Client client;
	private Employee assigned;
	
	public Call(Client client) {
		this.client = client;
	}
	
	
	/**
	 * Gets Employee in call
	 * @return employee asigned
	 */
	public Employee getEmployee() {
		return assigned;
	}
	
	/**
	 * Assigned an employee to the incoming call
	 * @param e - Employee appointed
	 */
	public void assignedEmployee(Employee e) {
		this.assigned = e;
	}
	
	/**
	 * Plays music when every employee is busy
	 */
	public void waitMusic() {
		System.out.println("Playing wait music to " + client.getName());
	}

	
	/** Actual Call - Sleeps for a random number of seconds between MIN_DURATION and MAX_DURATION
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		assigned.enterCall();
		long duration = ThreadLocalRandom.current().nextLong(MIN_DURATION, MAX_DURATION);
		System.out.println(client.getName() + " in call with " + assigned.getName());
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(duration));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(client.getName() + " call end");
		assigned.endCall();
	}
	
}