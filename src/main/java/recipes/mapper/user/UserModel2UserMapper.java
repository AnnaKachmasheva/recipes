package recipes.mapper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import recipes.domain.User;
import recipes.request.UserModel;

@Component
@RequiredArgsConstructor
public class UserModel2UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(UserModel userModel) {
        if (userModel == null)
            return null;

        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        return user;
    }
}
