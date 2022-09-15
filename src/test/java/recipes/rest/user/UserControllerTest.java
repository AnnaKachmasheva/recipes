package recipes.rest.user;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import recipes.request.UserModel;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static recipes.rest.user.UserUtilTest.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;


    @Test
    @Order(1)
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    private static Stream<Arguments> provideArgumentsForRegistration() {
        return Stream.of(
                Arguments.of(MAIN_LOGIN_PASS, 200),
                Arguments.of(ADDITIONAL_LOGIN_PASS_1, 200),
                Arguments.of(ADDITIONAL_LOGIN_PASS_2, 200),
                Arguments.of(INCORRECT_LOGIN_CORRECT_PASS_1, 400),
                Arguments.of(INCORRECT_LOGIN_CORRECT_PASS_2, 400),
                Arguments.of(CORRECT_LOGIN_INCORRECT_PASS_1, 400),
                Arguments.of(CORRECT_LOGIN_INCORRECT_PASS_2, 400),
                Arguments.of(MAIN_LOGIN_PASS, 400)
        );
    }

    @Order(2)
    @ParameterizedTest(name = "Create user with login and password {0}. Expected result code {1}.")
    @MethodSource("provideArgumentsForRegistration")
    void postRegisterTest(String[] loginAndPass, int statusCode) throws Exception {

        String JSON_LOGIN_PASS = UserUtilTest.userToJson(loginAndPass);

        MockHttpServletResponse response = this.mockMvc.perform(
                        post(UserUtilTest.API_REGISTER)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON_LOGIN_PASS))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(statusCode);
    }

    @Test
    @Order(3)
    void getAllUsersTest() throws Exception {

        UserModel user1 = UserModel.builder()
                .email(MAIN_LOGIN_PASS[0])
                .password(MAIN_LOGIN_PASS[1])
                .build();

        UserModel user2 = UserModel.builder()
                .email(ADDITIONAL_LOGIN_PASS_1[0])
                .password(ADDITIONAL_LOGIN_PASS_1[1])
                .build();

        UserModel user3 = UserModel.builder()
                .email(ADDITIONAL_LOGIN_PASS_2[0])
                .password(ADDITIONAL_LOGIN_PASS_2[1])
                .build();

        List<UserModel> users = List.of(user1, user2, user3);

        List<String> expectedEmails = UserUtilTest.getListEmails(users);

        String JSON_LOGIN_PASS_1 = UserUtilTest.userToJson(MAIN_LOGIN_PASS);
        String JSON_LOGIN_PASS_2 = UserUtilTest.userToJson(ADDITIONAL_LOGIN_PASS_1);
        String JSON_LOGIN_PASS_3 = UserUtilTest.userToJson(ADDITIONAL_LOGIN_PASS_2);

        this.mockMvc.perform(
                post(UserUtilTest.API_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_LOGIN_PASS_1));

        this.mockMvc.perform(
                post(UserUtilTest.API_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_LOGIN_PASS_2));

        this.mockMvc.perform(
                post(UserUtilTest.API_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_LOGIN_PASS_3));

        MockHttpServletResponse response = this.mockMvc.perform(
                        get(API_GET_ALL_USERS))
                .andReturn()
                .getResponse();

        var responseContent = response.getContentAsString();
        List<String> responseUserEmails = UserUtilTest.getListEmails(UserUtilTest.getUsersListFromResponse(responseContent));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseUserEmails).isEqualTo(expectedEmails);
    }

}