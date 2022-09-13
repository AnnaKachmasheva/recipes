package recipes.use_case;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.recipe.RecipeRepositoryAdapter;
import recipes.domain.Recipe;

@Service
@RequiredArgsConstructor
public class GetRecipeByIdUseCase {

    private final RecipeRepositoryAdapter recipeRepositoryAdapter;

    public Recipe execute(Long id) {
        return recipeRepositoryAdapter.getRecipeById(id);
    }

}
