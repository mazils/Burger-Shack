package ChefClient.Proxy;

import ChefClient.Domain.Recipe;

/**
 * Interface to get and update a recipe
 */
public interface RecipeProvider
{
    /**
     * Get a recipe with a given id.
     *
     * @param id recipe id
     * @return the recipe with the specified id or null if the recipe cannot be found
     * @throws Exception if something goes wrong
     */
    public Recipe getRecipeById(String id) throws Exception;
    /**
     * Get a recipe with a given name.
     *
     * @param name recipe name
     * @return the recipe with the specified name or null if the recipe cannot be found
     * @throws Exception if something goes wrong
     */
    public Recipe getRecipeByName(String name) throws Exception;
    /**
     * Update or add a recipe. If the parameter recipe has the same id as an
     * existing recipe the recipe should be replaced otherwise the
     * recipe should be added as a new recipe.
     *
     * @param recipe the updated or new recipe
     * @throws Exception if something goes wrong
     */
    public void updateRecipe(Recipe recipe) throws Exception;
}

