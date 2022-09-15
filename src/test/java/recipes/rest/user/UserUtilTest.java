package recipes.rest.user;


import com.google.gson.Gson;
import lombok.Data;
import recipes.request.UserModel;

import java.util.Arrays;
import java.util.List;

@Data
public class UserUtilTest {

    final static String API_REGISTER = "/api/register";
    final static String API_GET_ALL_USERS = "/api/users";

    final static String[] MAIN_LOGIN_PASS = {"LoginTest2@test.com", "Test2222222"};
    final static String[] ADDITIONAL_LOGIN_PASS_1 = {"LoginTest1@test.com", "Test1111"};
    final static String[] ADDITIONAL_LOGIN_PASS_2 = {"LoginTest3@test.com", "Test33333333333"};
    final static String[] INCORRECT_LOGIN_CORRECT_PASS_1 = {"Test3@testcom", "Test3333"};
    final static String[] INCORRECT_LOGIN_CORRECT_PASS_2 = {"Test4test.com", "Test44444444"};
    final static String[] CORRECT_LOGIN_INCORRECT_PASS_1 = {"Test5@test.com", "Test555"};
    final static String[] CORRECT_LOGIN_INCORRECT_PASS_2 = {"Test6@test.com", "        "};
    final static String[] UNREGISTERED_LOGIN_PASS = {"abc@test.com", "password99"};


    static String userToJson(String[] loginAndPass) {
        return "{\"email\":\"" + loginAndPass[0] +
                "\",\"password\":\"" + loginAndPass[1] +
                "\"}";
    }

    static List<UserModel> getUsersListFromResponse(String response) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(response, UserModel[].class));
    }

    static List<String> getListEmails(List<UserModel> users) {
        return users.stream().map(UserModel::getEmail).toList();
    }
}