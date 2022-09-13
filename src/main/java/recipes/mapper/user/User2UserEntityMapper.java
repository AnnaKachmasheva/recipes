package recipes.mapper.user;

import org.springframework.stereotype.Component;
import recipes.domain.User;
import recipes.entity.UserEntity;

@Component
public class User2UserEntityMapper {

    public UserEntity toUserEntity(User user) {
        if (user == null)
            return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        return userEntity;
    }
}
