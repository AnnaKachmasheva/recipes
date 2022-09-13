package recipes.adapter.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import recipes.domain.Recipe;
import recipes.facade.RecipeFacade;
import recipes.mapper.recipe.Recipe2RecipeRequestMapper;
import recipes.mapper.recipe.RecipeModel2RecipeMapper;
import recipes.request.RecipeIdRequest;
import recipes.request.RecipeModel;
import recipes.request.RecipeRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecipeAdapter {

    private final RecipeFacade recipeFacade;

    private final RecipeModel2RecipeMapper recipeModel2RecipeMapper;
    private final Recipe2RecipeRequestMapper recipe2RecipeRequestMapper;


    public RecipeIdRequest createRecipe(RecipeModel recipeModel, String email) {
        Recipe recipe = recipeModel2RecipeMapper.toRecipe(recipeModel);
        RecipeIdRequest recipeIdRequest = new RecipeIdRequest();
        recipeIdRequest.setId(recipeFacade.createRecipe(recipe, email));
        return recipeIdRequest;
    }

    public RecipeRequest getRecipeById(Long id) {
        Recipe recipe = recipeFacade.getRecipeById(id);
        return recipe2RecipeRequestMapper.toRecipeRequest(recipe);
    }

    public void deleteRecipeById(Long id, String usersEmail) {
        recipeFacade.deleteRecipeById(id, usersEmail);
    }

    public void updateRecipe(Long id, RecipeModel recipeModel, String usersEmail) {
        Recipe recipe = recipeModel2RecipeMapper.toRecipe(recipeModel);
        recipe.setId(id);
        recipeFacade.updateRecipe(recipe, usersEmail);
    }

    public List<RecipeRequest> findRecipesByCategoryOrName(String category, String name) {
        List<Recipe> recipes = recipeFacade.findRecipesByCategoryOrName(category, name);
        return recipe2RecipeRequestMapper.toRecipeRequestList(recipes);
    }
}
