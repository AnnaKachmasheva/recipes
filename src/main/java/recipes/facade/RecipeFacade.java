package recipes.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.domain.Recipe;
import recipes.use_case.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeFacade {

    private final CreateRecipeUseCase createRecipeUseCase;
    private final GetRecipeByIdUseCase getRecipeByIdUseCase;
    private final DeleteRecipeUseCase deleteRecipeUseCase;
    private final UpdateRecipeUseCase updateRecipeUseCase;
    private final GetRecipesByCategoryOrNameUseCase getRecipesByCategoryOrNameUseCase;


    public Long createRecipe(Recipe recipe, String email) {
        return createRecipeUseCase.execute(recipe, email);
    }

    public Recipe getRecipeById(Long id) {
        return getRecipeByIdUseCase.execute(id);
    }

    public void deleteRecipeById(Long id, String usersEmail) {
        deleteRecipeUseCase.execute(id, usersEmail);
    }

    public void updateRecipe(Recipe recipe, String usersEmail) {
        updateRecipeUseCase.execute(recipe, usersEmail);
    }

    public List<Recipe> findRecipesByCategoryOrName(String category, String name) {
        return getRecipesByCategoryOrNameUseCase.execute(category, name);
    }
}
