package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.user.UserRepositoryAdapter;
import recipes.domain.User;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepositoryAdapter userRepositoryAdapter;

    public void execute(User user) {
        userRepositoryAdapter.createUser(user);
    }

}
