package recipes.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import recipes.domain.User;
import recipes.facade.UserFacade;
import recipes.mapper.user.User2UserResponseMapper;
import recipes.mapper.user.UserModel2UserMapper;
import recipes.request.UserModel;
import recipes.response.UserResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final UserFacade userFacade;
    private final UserModel2UserMapper userModel2UserMapper;
    private final User2UserResponseMapper user2UserResponseMapper;

    public void createUser(UserModel userModel) {
        User user = userModel2UserMapper.toUser(userModel);
        userFacade.createUser(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> userList = userFacade.getAllUsers();
        return user2UserResponseMapper.toUserResponseList(userList);
    }
}
