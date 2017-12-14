package com.almundo.app;

import java.util.concurrent.ExecutionException;

import com.almundo.model.Callcenter;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
    	Callcenter c = new Callcenter(1, 1, 1);
    	try {
    		for (int i = 0; i < 10; i++) {
    			c.manageCall();
    		}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	
    }
}
