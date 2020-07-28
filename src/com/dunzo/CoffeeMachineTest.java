package com.dunzo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.parser.*;
import org.json.simple.*;

/* this class creates instance of CoffeeMachine
 * as per input file and run tests as specified
 * in CoffeeTest.java
 */
public class CoffeeMachineTest {
	private static List<Component> inventory;
	private static List<Beverage> beverages;
	private static long numOutlets;
	private static CoffeeMachine coffeeMachine ;
	
	public static void main(String[] args) throws Exception {
		inventory = new ArrayList<>();
		beverages = new ArrayList<>();
		createInventory();
		coffeeMachine= new CoffeeMachine((int)numOutlets, inventory, beverages);
		CoffeeTest coffeeTest = new CoffeeTest(coffeeMachine);
		try {
		// Comment or uncomment tests as per your convenience
		coffeeTest.test1();
		//coffeeTest.test2();
		//coffeeTest.test3();
		//coffeeTest.test4();
		}
		
		// coffeeMachine.refill(newInventory) can be called to refill.
		// It will add the new quantities into the coffeeMachine
		// I have not taken any example here, but could be done easily
		// by creating a new object of newInventory
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/* this method reads all the input from file coffee.json
	 * and populate those values in inventory and beverages
	 * list. It assumes format as specified in coffee.json
	 * or ​https://www.npoint.io/docs/e8cd5a9bbd1331de326a
	 */
	
	@SuppressWarnings("unchecked")
	public static void createInventory() {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader("coffee.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject input = (JSONObject) obj;
            //System.out.println(input);
             
            JSONObject machine = (JSONObject)input.get("machine");
            
            numOutlets = (long)((JSONObject)machine.get("outlets")).get("count_n");

            JSONObject total_items = (JSONObject)machine.get("total_items_quantity");
            for (Iterator<Object> iterator = total_items.keySet().iterator(); iterator.hasNext();) {
            	String ingredient = (String)iterator.next();
            	long quantity = (long)total_items.get(ingredient);
            	inventory.add(new Component(new Ingredient((String)ingredient),(int)quantity)); 
            }
            
            
            JSONObject beverages_json = (JSONObject)machine.get("beverages");
            for (Iterator<Object> iterator = beverages_json.keySet().iterator(); iterator.hasNext();) {
            	String beverageName = (String)iterator.next();
            	JSONObject beverage = (JSONObject)beverages_json.get(beverageName);
            	List<Component> components = new ArrayList<>();
            	for (Iterator<Object> iterator1 = beverage.keySet().iterator(); iterator1.hasNext();) {
            		String ingredient = (String)iterator1.next();
            		long quantity = (long)beverage.get(ingredient);
            		Component component = new Component(new Ingredient(ingredient),(int)quantity);
            		components.add(component);
            	}
            	beverages.add(new Beverage(beverageName,components));
            }
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
}
