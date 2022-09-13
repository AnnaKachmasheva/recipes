package recipes.mapper.user;

import org.springframework.stereotype.Component;
import recipes.domain.User;
import recipes.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEntity2UserMapper {
    public User toUser(UserEntity userEntity) {
        if (userEntity == null)
            return null;
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        return user;
    }

    public List<User> toUserList(List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            users.add(toUser(userEntity));
        }
        return users;
    }
}