package recipes.rest.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.adapter.recipe.RecipeAdapter;
import recipes.request.RecipeIdRequest;
import recipes.request.RecipeModel;
import recipes.request.RecipeRequest;
import recipes.security.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeAdapter recipeAdapter;


    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeIdRequest> createRecipe(@Valid @RequestBody RecipeModel recipeModel,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        RecipeIdRequest recipeIdRequest = recipeAdapter.createRecipe(recipeModel, userDetails.getUsername());
        return ResponseEntity.ok(recipeIdRequest);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable Long id,
                             @Valid @RequestBody RecipeModel recipeModel,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recipeAdapter.updateRecipe(id, recipeModel, userDetails.getUsername());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipeById(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recipeAdapter.deleteRecipeById(id, userDetails.getUsername());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeRequest> getRecipeById(@PathVariable Long id) {
        RecipeRequest request = recipeAdapter.getRecipeById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeRequest>> findRecipesByCategoryOrName(@RequestParam(required = false) String category,
                                                                           @RequestParam(required = false) String name) {
        List<RecipeRequest> recipeRequestList = recipeAdapter.findRecipesByCategoryOrName(category, name);
        return ResponseEntity.ok(recipeRequestList);
    }

}
