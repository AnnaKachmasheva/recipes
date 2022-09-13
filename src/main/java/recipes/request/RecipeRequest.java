package recipes.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecipeRequest {

    String name;
    String category;
    LocalDateTime date;
    String description;
    String[] ingredients;
    String[] directions;

}
