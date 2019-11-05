package ChefClient.Domain;

import Shared.Burger;

import java.util.Arrays;
public class Recipe {

/**
 * A class representing a burger recipe
 */

    private String id;
    private String name;
    private String[] ingredients;

    /**
     * A constructor
     * @param id the recipe id
     * @param name the recipe name (burger name)
     * @param ingredients an array of ingredients in the form of a var-arg
     */
    public Recipe(String id, String name, String... ingredients)
    {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    /**
     * Getter for recipe id
     * @return the recipe id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Getter for recipe name (burger name)
     * @return the recipe name (burger name)
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for recipe ingredients
     * @return an array of ingredients
     */
    public String[] getIngredients()
    {
        return ingredients;
    }

    /**
     * Creating the burger with the same name as the recipe
     * @return a new Shared.Burger object with the same name as the recipe
     */
    public Burger createBurger()
    {
        return new Burger(name);
    }

    /**
     * A string representation of a recipe
     * @return a string representation of a recipe
     */
    @Override public String toString()
    {
        return "Recipe{" + "id='" + id + '\'' + ", name='" + name + '\''
                + ", ingrediences=" + Arrays.toString(ingredients) + '}';
    }
}
