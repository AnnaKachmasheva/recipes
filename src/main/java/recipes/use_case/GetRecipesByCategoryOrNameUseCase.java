package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.recipe.RecipeRepositoryAdapter;
import recipes.domain.Recipe;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRecipesByCategoryOrNameUseCase {

    private final RecipeRepositoryAdapter recipeRepositoryAdapter;

    public List<Recipe> execute(String category, String name) {
        return recipeRepositoryAdapter.getRecipesByCategoryOrName(category, name);
    }

}
