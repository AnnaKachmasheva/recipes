package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.recipe.RecipeRepositoryAdapter;
import recipes.domain.Recipe;

@Service
@RequiredArgsConstructor
public class UpdateRecipeUseCase {

    private final RecipeRepositoryAdapter recipeRepositoryAdapter;

    public void execute(Recipe recipe, String usersEmail) {
        recipeRepositoryAdapter.updateRecipe(recipe, usersEmail);
    }
}
