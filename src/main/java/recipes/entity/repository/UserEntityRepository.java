package recipes.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);

    List<UserEntity> findAll();

}
