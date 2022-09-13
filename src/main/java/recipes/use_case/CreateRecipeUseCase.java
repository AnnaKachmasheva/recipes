package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.recipe.RecipeRepositoryAdapter;
import recipes.domain.Recipe;

@Service
@RequiredArgsConstructor
public class CreateRecipeUseCase {

    private final RecipeRepositoryAdapter recipeRepositoryAdapter;

    public Long execute(Recipe recipe, String email) {
        return recipeRepositoryAdapter.createRecipe(recipe, email);
    }

}
