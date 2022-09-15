package recipes.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import recipes.adapter.user.UserAdapter;
import recipes.request.UserModel;
import recipes.response.UserResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserAdapter userAdapter;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void registerUser(@Valid @RequestBody UserModel userModel) {
        userAdapter.createUser(userModel);
    }

    @GetMapping("/users")
    public Iterable<UserResponse> getUsers() {
        return userAdapter.getAllUsers();
    }

}
