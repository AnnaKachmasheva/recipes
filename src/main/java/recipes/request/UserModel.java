package recipes.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserModel {

    @Email
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$",
            message = "Email must be in the correct format")
    String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;
}
