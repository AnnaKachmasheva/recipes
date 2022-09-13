package recipes.use_case;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.adapter.user.UserRepositoryAdapter;
import recipes.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {
    private final UserRepositoryAdapter userRepositoryAdapter;

    public List<User> execute() {
        return userRepositoryAdapter.findAllUsers();
    }
}
