package recipes.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recipes")
public class RecipeEntity extends AbstractClassEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String directions;

    @ManyToOne
    @JoinColumn(name = "users_recipes_id", nullable = false)
    private UserEntity creator;

}
