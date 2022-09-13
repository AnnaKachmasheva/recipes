package recipes.adapter.recipe;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.Recipe;
import recipes.entity.RecipeEntity;
import recipes.entity.UserEntity;
import recipes.entity.repository.RecipeEntityRepository;
import recipes.entity.repository.UserEntityRepository;
import recipes.mapper.recipe.Recipe2RecipeEntityMapper;
import recipes.mapper.recipe.RecipeEntity2RecipeMapper;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Data
@RequiredArgsConstructor
@Component
public class RecipeRepositoryAdapterImpl implements RecipeRepositoryAdapter {

    private final RecipeEntityRepository recipeEntityRepository;
    private final UserEntityRepository userEntityRepository;

    private final Recipe2RecipeEntityMapper recipe2RecipeEntityMapper;
    private final RecipeEntity2RecipeMapper recipeEntity2RecipeMapper;


    @Override
    public Long createRecipe(Recipe recipe, String email) {
        var userEntityOptional = userEntityRepository.findUserEntityByEmail(email);

        if (userEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        RecipeEntity recipeEntity = recipe2RecipeEntityMapper.toRecipeEntity(recipe);
        recipeEntity.setDate(LocalDateTime.now());
        recipeEntity.setCreator(userEntityOptional.get());

        recipeEntityRepository.save(recipeEntity);
        log.info("Created recipe_entity: {}.", recipeEntity);

        return recipeEntity.getId();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        var recipeEntityOptional = recipeEntityRepository.findById(id);

        if (recipeEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        RecipeEntity recipeEntity = recipeEntityOptional.get();
        log.info("Find recipe_entity: {}.", recipeEntity);

        return recipeEntity2RecipeMapper.toRecipe(recipeEntity);
    }

    @Override
    public void deleteRecipeById(Long id, String usersEmail) {
        Recipe recipe = getRecipeById(id);

        if (!recipe.getCreator().getEmail().equals(usersEmail))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        recipeEntityRepository.deleteById(recipe.getId());
        log.info("Deleted recipe: {}.", recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe, String usersEmail) {
        Recipe recipeForUpdate = getRecipeById(recipe.getId());

        if (!recipeForUpdate.getCreator().getEmail().equals(usersEmail))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        var creatorOptional = userEntityRepository.findUserEntityByEmail(usersEmail);
        if (creatorOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserEntity creator = creatorOptional.get();

        recipeForUpdate.setName(recipe.getName());
        recipeForUpdate.setCategory(recipe.getCategory());
        recipeForUpdate.setDescription(recipe.getDescription());
        recipeForUpdate.setIngredients(recipe.getIngredients());
        recipeForUpdate.setDirections(recipe.getDirections());

        RecipeEntity recipeEntity = recipe2RecipeEntityMapper.toRecipeEntity(recipeForUpdate);
        recipeEntity.setId(recipe.getId());
        recipeEntity.setDate(LocalDateTime.now());
        recipeEntity.setCreator(creator);

        recipeEntityRepository.save(recipeEntity);
        log.info("Updated recipe: {}.", recipeEntity);
    }

    @Override
    public List<Recipe> getRecipesByCategoryOrName(String category, String name) {
        if (category == null && name == null || category != null && name != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        List<RecipeEntity> recipeEntities;
        if (category != null) {
            recipeEntities = recipeEntityRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        } else {
            recipeEntities = recipeEntityRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
        }
        log.info("Find {} recipes: {}.", recipeEntities.size(), recipeEntities);

        return recipeEntity2RecipeMapper.toRecipeList(recipeEntities);
    }
}
