package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.recipe.RecipeRepositoryAdapter;

@Service
@RequiredArgsConstructor
public class DeleteRecipeUseCase {

    private final RecipeRepositoryAdapter recipeRepositoryAdapter;

    public void execute(Long id, String usersEmail) {
        recipeRepositoryAdapter.deleteRecipeById(id, usersEmail);
    }
}
