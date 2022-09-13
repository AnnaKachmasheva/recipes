package recipes.adapter.user;

import recipes.domain.User;

import java.util.List;

public interface UserRepositoryAdapter {

    void createUser(User user);

    List<User> findAllUsers();

}