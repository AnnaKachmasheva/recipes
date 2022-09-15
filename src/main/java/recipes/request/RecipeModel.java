package recipes.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RecipeModel {

    @NotBlank
    String name;

    @NotBlank
    String category;

    @NotBlank
    String description;

    @NotNull
    @Size(min = 1)
    String[] ingredients;

    @NotNull
    @Size(min = 1)
    String[] directions;

}
