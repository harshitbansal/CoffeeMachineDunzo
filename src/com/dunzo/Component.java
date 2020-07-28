package com.dunzo;

/* Every beverage is made of list of components
 * Each component has ingredient and its quantity
 */
public class Component {
	private final Ingredient ingredient;

    private final int quantity;

    public Component(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
