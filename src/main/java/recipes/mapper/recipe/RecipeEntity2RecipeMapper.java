package recipes.mapper.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import recipes.domain.Recipe;
import recipes.entity.RecipeEntity;
import recipes.mapper.user.UserEntity2UserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RecipeEntity2RecipeMapper {

    private final UserEntity2UserMapper userEntity2UserMapper;

    public Recipe toRecipe(RecipeEntity recipeEntity) {
        if (recipeEntity == null)
            return null;

        Recipe recipe = new Recipe();
        recipe.setId(recipeEntity.getId());
        recipe.setName(recipeEntity.getName());
        recipe.setCategory(recipeEntity.getCategory());
        recipe.setDate(recipeEntity.getDate());
        recipe.setDescription(recipeEntity.getDescription());
        recipe.setIngredients(strToList(recipeEntity.getIngredients()));
        recipe.setDirections(strToList(recipeEntity.getDirections()));
        recipe.setCreator(userEntity2UserMapper.toUser(recipeEntity.getCreator()));

        return recipe;
    }

    private List<String> strToList(String str) {
        return Arrays.stream(str.split(";")).toList();
    }

    public List<Recipe> toRecipeList(List<RecipeEntity> recipeEntities) {
        List<Recipe> recipes = new ArrayList<>(recipeEntities.size());
        for (RecipeEntity recipeEntity : recipeEntities) {
            recipes.add(toRecipe(recipeEntity));
        }
        return recipes;
    }
}