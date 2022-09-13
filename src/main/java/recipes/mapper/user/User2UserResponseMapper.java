package recipes.mapper.user;

import org.springframework.stereotype.Component;
import recipes.domain.User;
import recipes.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class User2UserResponseMapper {

    public UserResponse toUserResponse(User user) {
        if (user == null)
            return null;

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());

        return userResponse;
    }

    public List<UserResponse> toUserResponseList(List<User> userList) {
        List<UserResponse> userResponses = new ArrayList<>(userList.size());
        for (User user : userList) {
            userResponses.add(toUserResponse(user));
        }
        return userResponses;
    }
}

