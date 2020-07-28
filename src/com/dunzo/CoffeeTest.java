package com.dunzo;

import java.util.Arrays;
import java.util.List;

/* this class comprises of functional test cases
 * run in CoffeeMachineTest
 */

public class CoffeeTest {
	private CoffeeMachine coffeeMachine;
	
	CoffeeTest(CoffeeMachine coffeeMachine){
		this.coffeeMachine = coffeeMachine;
	}
	
	public void test1() throws Exception {
		List<String> coffeeRequests = Arrays.asList("hot_tea","hot_coffee","black_tea");
		for(String coffeeType:coffeeRequests)
			coffeeMachine.brewCoffee(coffeeType);
	}
	
	public void test2() throws Exception {
		List<String> coffeeRequests = Arrays.asList("hot_tea","hot_tea");
		for(String coffeeType:coffeeRequests)
			coffeeMachine.brewCoffee(coffeeType);
	}
	
	public void test3() throws Exception {
		List<String> coffeeRequests = Arrays.asList("green_tea","hot_tea");
		for(String coffeeType:coffeeRequests)
			coffeeMachine.brewCoffee(coffeeType);
	}
	
	public void test4() throws Exception {
		List<String> coffeeRequests = Arrays.asList("unknown_tea","unknown_coffee");
		for(String coffeeType:coffeeRequests)
			coffeeMachine.brewCoffee(coffeeType);
	}
	
}
