package Proxy;

public class CachedRecipeReader implements RecipeProvider {

    private RecipeReader recipeReader;

    public CachedRecipeReader(RecipeReader recipeReader,String fileName)
    {
        this.recipeReader= recipeReader;
    }

    @Override
    public Recipe getRecipeById(String id) throws Exception {
        if(recipeReader==null) {
            return recipeReader.getRecipeById(id);
        }
        else
            System.out.println("Unavailable content");
        return null;

    }

    @Override
    public Recipe getRecipeByName(String name) throws Exception {
        if(recipeReader!=null) {
            return recipeReader.getRecipeByName(name);
        }
        else
            System.out.println("Unavailable content");
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) throws Exception {
            recipeReader.updateRecipe(recipe);
    }
}
