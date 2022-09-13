package recipes.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.domain.User;
import recipes.use_case.CreateUserUseCase;
import recipes.use_case.GetAllUsersUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    public void createUser(User user) {
        createUserUseCase.execute(user);
    }

    public List<User> getAllUsers() {
        return getAllUsersUseCase.execute();
    }
}
