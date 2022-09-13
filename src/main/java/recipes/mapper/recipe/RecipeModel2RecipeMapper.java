package recipes.mapper.recipe;

import org.springframework.stereotype.Component;
import recipes.domain.Recipe;
import recipes.request.RecipeModel;

import java.util.Arrays;
import java.util.List;

@Component
public class RecipeModel2RecipeMapper {

    public Recipe toRecipe(RecipeModel recipeModel) {
        if (recipeModel == null)
            return null;

        Recipe recipe = new Recipe();
        recipe.setName(recipeModel.getName());
        recipe.setCategory(recipeModel.getCategory());
        recipe.setDescription(recipeModel.getDescription());
        recipe.setIngredients(arrayToList(recipeModel.getIngredients()));
        recipe.setDirections(arrayToList(recipeModel.getDirections()));

        return recipe;

    }

    private List<String> arrayToList(String[] array) {
        return Arrays.asList(array);
    }

}