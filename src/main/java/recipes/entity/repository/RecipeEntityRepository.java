package recipes.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.RecipeEntity;

import java.util.List;

@Repository
public interface RecipeEntityRepository extends CrudRepository<RecipeEntity, Long> {

    List<RecipeEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<RecipeEntity> findByNameContainingIgnoreCaseOrderByDateDesc(String name);

}
