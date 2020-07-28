package com.dunzo;


import java.util.*;

/* Central inventory of all items
 * INGREDIENT_THRESHOLD = 10 to display
 * warning. It has methods to check if ingredient 
 * available, consume ingredient, handle multithreading.
 * Method to refill ingredient is also available
 */
public class Inventory {

	public static Map<Ingredient,Integer> inventoryMap=new HashMap<>();
	
	private static final Inventory inventory = new Inventory();
	private static final int INGREDIENT_THRESHOLD = 10;
	private Display display = new Display();
	private Inventory() {
		
	}
	
	public static Inventory getInventory() {
		return inventory;
	}

	public void checkIngredientQuantity(Component component) throws Exception {
		if(!inventoryMap.containsKey(component.getIngredient()))
			throw new Exception(component.getIngredient().getName()+" not available!");
		if(inventoryMap.get(component.getIngredient())<component.getQuantity()) {
			notifyDisplay();
			throw new Exception(component.getIngredient().getName()+" not in sufficient quantity!");
			
		}
	}
	
	public void notifyDisplay() {
		List<Ingredient> list = new ArrayList<>();
		for(Map.Entry<Ingredient,Integer> entry:inventoryMap.entrySet()) {
			if(entry.getValue()<INGREDIENT_THRESHOLD)
				list.add(entry.getKey());
		}
		if(!list.isEmpty())
			display.showDisplay(list);
	}
	
	public void consumeIngredient(Component component) throws Exception{
		synchronized(Inventory.class)
		{
		  if(inventoryMap.get(component.getIngredient())<component.getQuantity())
		   	throw new Exception("Component not in sufficient quantity!");
		
		  inventoryMap.put(component.getIngredient(),inventoryMap.get(component.getIngredient())-component.getQuantity());
		}
		//notifyDisplay();
	}
	
	
	/* synchronized on Inventory.class to avoid race conditions
	 */
	public void refill(List<Component> components) {
		synchronized(Inventory.class)
		{
		  for(Component component: components)
		  {
			  inventoryMap.put(component.getIngredient(),
					  inventoryMap.getOrDefault(component.getIngredient(),0) + component.getQuantity());
		  }
		}
	}
	
    
	
}
