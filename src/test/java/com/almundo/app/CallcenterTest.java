package com.almundo.app;

import com.almundo.model.Call;
import com.almundo.model.Callcenter;
import com.almundo.model.Client;
import com.almundo.model.Dispatcher;
import com.almundo.model.EmployeeRank;
import com.almundo.model.EmptyCenterException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CallcenterTest  extends TestCase {

    public CallcenterTest(String testName){
        super(testName);
    }

    public static Test suite(){
        return new TestSuite(CallcenterTest.class);
    }
	
	public void testAsignOperatorFirst() throws InterruptedException, EmptyCenterException {
		Callcenter center = new Callcenter(1, 1, 1);
		Dispatcher dispatcher = new Dispatcher(center);
		Call call = dispatcher.dispatchCall(new Call(new Client("Client OP")));
        assertEquals(call.getEmployee().getRank(), EmployeeRank.OPERATOR);
    }
	
	public void testAsignSupervisorWhenNoOperator() throws InterruptedException, EmptyCenterException {
		Callcenter center = new Callcenter(0, 1, 1);
		Dispatcher dispatcher = new Dispatcher(center);
		Call call = dispatcher.dispatchCall(new Call(new Client("Client SUP")));
        assertEquals(call.getEmployee().getRank(), EmployeeRank.SUPERVISOR);
    }
	
	public void testAsignDirectorWhenNoOperatorNorSupervisor() throws InterruptedException, EmptyCenterException {
		Callcenter center = new Callcenter(0, 0, 1);
		Dispatcher dispatcher = new Dispatcher(center);
		Call call = dispatcher.dispatchCall(new Call(new Client("Client DIR")));
        assertEquals(call.getEmployee().getRank(), EmployeeRank.DIRECTOR);
    }
	
	public void testCallHaveEmployeeNoException() throws InterruptedException {
		Callcenter center = new Callcenter(1, 0, 0);
		try {
			center.manageCall();
		} catch(EmptyCenterException e ) {
		     System.out.println(e.getMessage());
		     fail();
		}
	}
	
	public void testOneCallThreeEmployeesNoException() {
		Callcenter c = new Callcenter(1, 1, 1);
		try {
			c.manageCall();
		} catch (Exception e) {
			fail();
		}
    }
	
	/**
	 * When there is no employee to get assigned to the call,
	 * the dispatcher plays waiting music for a fixed amount of time
	 * until someone gets free or the max waiting time is achieved.
	 * 
	 */
	public void tenCallsThreeEmployeesNoException() {
		Callcenter c = new Callcenter(1, 1, 1);
		try {
			for (int i = 0; i < 10; i++) {
				c.manageCall();
			}
		} catch (Exception e) {
			fail();
		}
    }
	
	public void testTenCallsTenEmployeesNoException() {
		Callcenter c = new Callcenter(8, 2, 1);
		try {
			for (int i = 0; i < 10; i++) {
				c.manageCall();
			}
		} catch (Exception e) {
			fail();
		}
    }
	
	/**
	 * When there are more calls than employees
	 * calls waits for someone to get the call until a fixed 
	 * max time in seconds is achieved.
	 */
	public void testMoreCallsThanEmployeesNoException() {
		Callcenter c = new Callcenter(2, 1, 1);
		try {
			for (int i = 0; i < 10; i++) {
				c.manageCall();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
    }
	
	/**
	 * When there is no employee and max time is achieved
	 * we get an EmtpyCenterException telling us there are
	 * no employees available
	 */
	public void testCallNoEmployee() throws InterruptedException {
		Callcenter center = new Callcenter(0, 0, 0);
		try {
			center.manageCall();
		} catch(EmptyCenterException e ) {
		     assertEquals("No employees", e.getMessage());
		}
	}
}
