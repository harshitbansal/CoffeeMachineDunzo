package com.dunzo;

import java.util.*;

/* Multithreading in implemented
 * Every thread execute prepareAndDispense
 * which either prepare drink or throw
 * exception 
 */

public class CoffeeService implements Runnable {

	Beverage beverage;
	
	public CoffeeService(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public void run(){
		try {
			prepareAndDispense(beverage);
		}
		catch(Exception e) {
			System.out.print(beverage.getName()+" can't be prepared because ");
			System.out.println(e.getMessage());
		}
	}
	
	public void prepareAndDispense(Beverage beverage) throws Exception {
		
        this.consumeIngredients(beverage);
        beverage.brew();
        // No exception means beverage is prepared. We return the same instance because its immutable.
        // Otherwise a clone should have been returned.
        System.out.println(beverage.getName() + " is prepared");
    }

	/* it is synchronized on CoffeeService.class so as to avoid race conditions between threads
	 * accessing inventory at the same time
	 */
    private void consumeIngredients(Beverage beverage) throws Exception {
    	synchronized(CoffeeService.class) {
    		List<Component> requiredComponents = beverage.getComponents();

    		// Check all required ingredients are present in sufficient quantity
    		for (Component required : requiredComponents) {
    			Inventory.getInventory().checkIngredientQuantity(required);
    		}

    		// Consume all required ingredients in the quantity as required to prepare the beverage
    		for (Component required : requiredComponents) {
    			Inventory.getInventory().consumeIngredient(required);
    		}
    	}
    	
    }
}
