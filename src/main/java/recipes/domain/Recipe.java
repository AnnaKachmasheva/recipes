package recipes.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Recipe {

    Long id;
    String name;
    LocalDateTime date;
    String category;
    String description;
    List<String> ingredients;
    List<String> directions;
    User creator;

}