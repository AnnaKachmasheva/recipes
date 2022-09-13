package recipes.mapper.recipe;

import org.springframework.stereotype.Component;
import recipes.domain.Recipe;
import recipes.entity.RecipeEntity;

@Component
public class Recipe2RecipeEntityMapper {

    public RecipeEntity toRecipeEntity(Recipe recipe) {
        if (recipe == null)
            return null;

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setName(recipe.getName());
        recipeEntity.setCategory(recipe.getCategory());
        recipeEntity.setDescription(recipe.getDescription());

        String ingredientsStr = String.join(";", recipe.getIngredients());
        recipeEntity.setIngredients(ingredientsStr);

        String directionsStr = String.join(";", recipe.getDirections());
        recipeEntity.setDirections(directionsStr);

        return recipeEntity;

    }
}
