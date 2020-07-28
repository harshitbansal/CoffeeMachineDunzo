package com.dunzo;

import java.util.*;

// Each Beverage has beverageType and list of ingredients
public class Beverage {
	
	private final String name;

    private final List<Component> components;

    public Beverage(String name, List<Component> components) {
        this.name = name.toLowerCase();
        this.components = components;
    }


    public String getName() {
        return name;
    }
    
    
 /* assumption: any outlet can serve any beverage
    if only one beverage can be served by one outlet,
    then brew() should be synchronized
 */
  
   // brew time is 5 seconds which is fixed for all beverages
    public void brew() {
    	//Preparing beverage of type name, taking 5 sec to prepare
    	try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    	//Drink prepared
    }

    
	public List<Component> getComponents() {
		return this.components;
	}
    
}
