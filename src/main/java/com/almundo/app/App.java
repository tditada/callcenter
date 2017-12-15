package com.almundo.app;
import com.almundo.model.Callcenter;
import com.almundo.model.EmptyCenterException;

public class App 
{
    public static void main(String[] args) throws InterruptedException, EmptyCenterException
    {
    	Callcenter c = new Callcenter(1, 1, 1);
		for (int i = 0; i < 10; i++) {
			c.manageCall();
		}
    	
    }
}
