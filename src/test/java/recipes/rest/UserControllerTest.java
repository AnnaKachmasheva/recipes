package recipes.rest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static recipes.rest.UtilTest.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

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

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("provideArgumentsForRegistration")
    void postRegisterTest(String[] loginAndPass, int statusCode) throws Exception {
        final String JSON_LOGIN_PASS = "{\"email\":\"" + loginAndPass[0] + "\",\"password\":\"" + loginAndPass[1] + "\"}";

        MockHttpServletResponse response = this.mockMvc.perform(
                        post(UtilTest.API_REGISTER)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON_LOGIN_PASS))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(statusCode);
    }

//    @Order(3)
//    @Test
//    void getAllUsersTest() throws Exception {
//
//        MockHttpServletResponse response = this.mockMvc.perform(
//                        get(API_GET_ALL_USERS))
//                .andReturn()
//                .getResponse();
//
//    }
}