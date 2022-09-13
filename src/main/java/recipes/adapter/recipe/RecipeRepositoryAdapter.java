package recipes.adapter.recipe;

import recipes.domain.Recipe;

import java.util.List;

public interface RecipeRepositoryAdapter {

    Long createRecipe(Recipe recipe, String email);

    Recipe getRecipeById(Long id);

    void deleteRecipeById(Long id, String usersEmail);

    void updateRecipe(Recipe recipe, String usersEmail);

    List<Recipe> getRecipesByCategoryOrName(String category, String name);
}
