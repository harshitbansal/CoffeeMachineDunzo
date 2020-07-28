package com.dunzo;

import java.util.List;

/* Display is to print ingredients
 * running in low quantity. I have set
 * a threshold of 10 currently in Inventory.java
 */

// Display could be improved a lot, but I have added basic functionality
public class Display{
	
	public void showDisplay(List<Ingredient> ingredients) {
		for(Ingredient ingredient:ingredients)
			System.out.println("DISPLAY: "+ingredient.getName()+" running low. Please refill!");
	}
	
}
