package com.dunzo;

import java.util.*;
import java.util.concurrent.*;

// Only single instance of CoffeeMachine
public class CoffeeMachine {

	List<Beverage> beverages;
	ExecutorService executor;
	
	// While creating the coffeeMachine instance, it takes number of outlets, inventory
	// and list of beverages it can serve
	public CoffeeMachine(int numberOfOutlets, List<Component> inventory, List<Beverage> beverages) {
		refill(inventory);
		this.beverages=beverages;
		executor = Executors.newFixedThreadPool(numberOfOutlets);
	}
	
	// Request to brew coffee of type beverageType
	public void brewCoffee(String beverageType) throws Exception{
		for(Beverage beverage:beverages) {
			if(beverage.getName().equals(beverageType)) {
				brewCoffee(beverage);
				return;
			}
		}
		throw new Exception("Invalid Request! This beverage is not available");
	}
	
	public void refill(List<Component> inventory) {
		Inventory.getInventory().refill(inventory);
	}
	
	public void brewCoffee(Beverage beverage) throws Exception{
		CoffeeService coffeeService = new CoffeeService(beverage);
		executor.execute(coffeeService);
	}
	
	
}
