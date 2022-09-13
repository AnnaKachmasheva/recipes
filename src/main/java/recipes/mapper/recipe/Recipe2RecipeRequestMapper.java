package recipes.mapper.recipe;

import org.springframework.stereotype.Component;
import recipes.domain.Recipe;
import recipes.request.RecipeRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class Recipe2RecipeRequestMapper {

    public RecipeRequest toRecipeRequest(Recipe recipe) {
        if (recipe == null)
            return null;

        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setName(recipe.getName());
        recipeRequest.setCategory(recipe.getCategory());
        recipeRequest.setDate(recipe.getDate());
        recipeRequest.setDescription(recipe.getDescription());
        recipeRequest.setIngredients(listToArray(recipe.getIngredients()));
        recipeRequest.setDirections(listToArray(recipe.getDirections()));

        return recipeRequest;

    }

    private String[] listToArray(List<String> list) {
        return list.toArray(String[]::new);
    }
    
    public List<RecipeRequest> toRecipeRequestList(List<Recipe> recipes) {
        List<RecipeRequest> recipeRequestList = new ArrayList<>(recipes.size());
        for (Recipe recipe : recipes) {
            recipeRequestList.add(toRecipeRequest(recipe));
        }
        return recipeRequestList;
    }

}
