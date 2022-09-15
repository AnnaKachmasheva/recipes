package recipes.rest.recipe;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import recipes.request.RecipeModel;
import recipes.rest.user.UserController;
import recipes.rest.user.UserUtilTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
class RecipeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private UserController userController;
//
//    @InjectMocks
//    private RecipeController recipeController;
//
//    private static Stream<Arguments> provideArgumentsForCreateRecipe() {
//        return Stream.of(
//                Arguments.of(RecipeUtilTest.RECIPES[0], 200),
//                Arguments.of(RecipeUtilTest.RECIPES[1], 200),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[0], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[1], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[2], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[3], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[4], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[5], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[6], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[7], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[8], 400),
//                Arguments.of(RecipeUtilTest.INCORRECT_RECIPES[9], 400)
//        );
//    }
//
//    @Order(1)
//    @ParameterizedTest(name = "Create recipe {0}. Expected result code {1}.")
//    @MethodSource("provideArgumentsForCreateRecipe")
//    @WithMockUser(username = "LoginTest2@test.com", password = "Test2222222")
//    void createRecipeAuthUser(RecipeModel recipe, int statusCode) throws Exception {
//
//        String JSON_LOGIN_PASS = RecipeUtilTest.recipeToJson(recipe);
//
//        MockHttpServletResponse response = this.mockMvc.perform(
//                        post(RecipeUtilTest.API_RECIPE_NEW)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(JSON_LOGIN_PASS))
//                .andReturn()
//                .getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(statusCode);
//    }
}
