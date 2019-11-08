package ChefClient.Proxy;



import ChefClient.Domain.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class reading a recipe from a file and updating a recipe in the file. <br>
 * Each line in the file represents one recipe and must contain an id, a semicolon
 * e name, a semicolon and a list of ingredients separated by semicolons. <br>
 *   Example:<br>
 * <code>1; Hamburger; 4 inch bun; Tomato; Onion; Beef; Lettuce; Cucumber; Ketchup</code>
 */
public class RecipeReader implements RecipeProvider, Serializable
{
    private String filename;

    /**
     * A constructor
     * @param filename the file path
     */
    public RecipeReader(String filename)
    {
        this.filename = filename;
    }

    /**
     * Load a recipe from file with a given id.
     *
     * @param id recipe id
     * @return the recipe with the specified id or null if the file do not contain a recipe with this id
     * @throws FileNotFoundException if the file cannot be found
     * @throws IllegalStateException if the file format is not correct
     */
    @Override public synchronized Recipe getRecipeById(String id)
            throws FileNotFoundException, IllegalStateException
    {
        String line = null;
        try
        {
            Recipe recipe = null;
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while (in.hasNext())
            {
                line = in.nextLine(); // Read a line
                String[] token = line.split(";");
                String recipeId = token[0].trim();
                if (recipeId.equalsIgnoreCase(id))
                {
                    recipe = convertToRecipe(line);
                    break;
                }
            }
            in.close();
            return recipe;
        }
        catch (FileNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Wrong formatted file: " + line);
        }
    }

    /**
     * Load a recipe from file with a given name.
     *
     * @param name recipe name
     * @return the recipe with the specified name or null if the file do not contain a recipe with this name
     * @throws FileNotFoundException if the file cannot be found
     * @throws IllegalStateException if the file format is not correct
     */
    @Override public synchronized Recipe getRecipeByName(String name)
            throws FileNotFoundException, IllegalStateException
    {
        String line = null;
        try
        {
            Recipe recipe = null;
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while (in.hasNext())
            {
                line = in.nextLine(); // Read a line
                String[] token = line.split(";");
                String recipeName = token[1].trim();
                if (recipeName.equalsIgnoreCase(name))
                {
                    recipe = convertToRecipe(line);
                    break;
                }
            }
            in.close();
            return recipe;
        }
        catch (FileNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Wrong formatted file: " + line);
        }
    }

    /**
     * Update or add a recipe. If the parameter recipe has the same id as an
     * existing recipe the recipe in the file is replaced otherwise the
     * recipe is added to the file as a new recipe.
     *
     * @param recipe the updated recipe or a new recipe
     * @throws FileNotFoundException if the file cannot be found
     * @throws IllegalStateException if the file format is not correct
     */
    @Override public synchronized void updateRecipe(Recipe recipe)
            throws FileNotFoundException, IllegalStateException
    {
        String line = null;
        try
        {
            ArrayList<Recipe> recipes = new ArrayList<>();
            File file = new File(filename);
            Scanner in = new Scanner(file);
            boolean updated = false;
            while (in.hasNext())
            {
                line = in.nextLine(); // Read a line
                String[] token = line.split(";");
                String recipeId = token[0].trim();
                if (recipeId.equalsIgnoreCase(recipe.getId()))
                {
                    recipes.add(recipe);
                    updated = true;
                }
                else
                {
                    recipes.add(convertToRecipe(line));
                }
            }
            if (!updated)
            {
                recipes.add(recipe);
            }
            in.close(); // Close the file

            PrintWriter out = new PrintWriter(file);
            for (int i = 0; i < recipes.size(); i++)
            {
                out.println(convertFromRecipe(recipes.get(i)));
            }
            out.close();
        }
        catch (FileNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Wrong formatted file: " + line);
        }
    }

    private String convertFromRecipe(Recipe recipe)
    {
        String recipeId = recipe.getId();
        String name = recipe.getName();
        String ingredientsString = "";
        String[] ingredients = recipe.getIngredients();
        for (int j = 0; j < ingredients.length; j++)
        {
            ingredientsString += ingredients[j];
            if (j < ingredients.length - 1)
            {
                ingredientsString += "; ";
            }
        }
        return (recipeId + "; " + name + "; " + ingredientsString);
    }

    private Recipe convertToRecipe(String recipeString)
    {
        String[] token = recipeString.split(";");
        String recipeId = token[0].trim();
        String name = token[1].trim();
        String[] ingredients = new String[token.length - 2];
        for (int i = 2; i < token.length; i++)
        {
            ingredients[i - 2] = token[i].trim();
        }
        return new Recipe(recipeId, name, ingredients);
    }
}

