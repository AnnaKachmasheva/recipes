package recipes.rest.recipe;

import recipes.request.RecipeModel;

import java.util.Arrays;

public class RecipeUtilTest {

    final static String API_RECIPE = "/api/recipe/";
    final static String API_RECIPE_NEW = "/api/recipe/new";
    final static String API_RECIPE_SEARCH = "/api/recipe/search/";

    final String CATEGORY = "category";
    final String NAME = "name";

    static final RecipeModel[] RECIPES = {
            // 0
            new RecipeModel(
                    "Fresh Mint Tea /Test",
                    "beverage /Test",
                    "Light, aromatic and refreshing beverage, ... /Test",
                    new String[]{"boiled water", "honey", "fresh mint leaves /Test"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again /Test"}
            ),
            // 1
            new RecipeModel(
                    "Warming Ginger Tea /Test",
                    "beverage /Test",
                    "Ginger tea is a warming drink for cool weather, ... /Test",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey /Test"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy /Test"}
            ),
            // 2
            new RecipeModel(
                    "ice-cream",
                    "Dessert",
                    "-",
                    new String[]{"--", "---", "-"},
                    new String[]{"----", "---"}
            ),
            // 3
            new RecipeModel(
                    "tea r 4",
                    "DesserT",
                    "---",
                    new String[]{"-", "----", "-"},
                    new String[]{"----", "--", "--"}
            ),
            // 4
            new RecipeModel(
                    "recipe ice-cream 5",
                    "Desser",
                    "---",
                    new String[]{"-", "--", "-"},
                    new String[]{"-", "--", "---"}
            ),
            // 5
            new RecipeModel(
                    "--",
                    "DeSSert",
                    "---",
                    new String[]{"---", "-", "--"},
                    new String[]{"---", "-"}
            ),
            // 6
            new RecipeModel(
                    "ICE-CREAM",
                    "desserT",
                    "----",
                    new String[]{"-", "-", "--"},
                    new String[]{"---", "--", "--"}
            ),
            // 7
            new RecipeModel(
                    "---",
                    "dessert",
                    "--",
                    new String[]{"-", "----"},
                    new String[]{"-----", "-", "---"}
            ),
            // 8
            new RecipeModel(
                    "9 recipe Tea test",
                    "-",
                    "----",
                    new String[]{"-", "-", "----"},
                    new String[]{"-----", "-", "--"}
            ),
            // 9
            new RecipeModel(
                    "10 ice recipe test",
                    "-",
                    "--",
                    new String[]{"----", "--;", "---"},
                    new String[]{"--", "-"}
            ),
            // 10
            new RecipeModel(
                    "11 ice-creamrecipe test",
                    "veryDessert",
                    "-",
                    new String[]{"-", "--"},
                    new String[]{"-----", "-", "---"}
            ),
            // 11
            new RecipeModel(
                    "cream",
                    "BEVerage",
                    "--",
                    new String[]{"---", "-", "-"},
                    new String[]{"-", "--"}
            ),
            // 12
            new RecipeModel(
                    "ice-cre",
                    "---Dessert",
                    "-",
                    new String[]{"---", "-"},
                    new String[]{"----", "-", "-"}
            ),
            // 13
            new RecipeModel(
                    "ice-cream",
                    "DESSERT",
                    "-",
                    new String[]{"----", "--"},
                    new String[]{"-", "--", "-"}
            ),
            // 14
            new RecipeModel(
                    "15 recipe test ice-CREAM",
                    "Dessert",
                    "-",
                    new String[]{"-", "---'", "-----"},
                    new String[]{"---", "-"}
            )
    };

    final static RecipeModel[] INCORRECT_RECIPES = {
            //0
            new RecipeModel(
                    null,
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //1
            new RecipeModel(
                    "Fresh Mint Tea",
                    null,
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //2
            new RecipeModel(
                    "Warming Ginger Tea",
                    "beverage",
                    null,
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
            ),
            //3
            new RecipeModel(
                    "Fresh Mint Tea",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    null,
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //4
            new RecipeModel(
                    "Warming Ginger Tea",
                    "beverage",
                    "Ginger tea is a warming drink for cool weather, ...",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    null
            ),
            //5
            new RecipeModel(
                    "  ",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //6
            new RecipeModel(
                    "Fresh Mint Tea",
                    "  ",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //7
            new RecipeModel(
                    "Warming Ginger Tea",
                    "beverage",
                    "  ",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
            ),
            //8
            new RecipeModel(
                    "Fresh Mint Tea",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //9
            new RecipeModel(
                    "Warming Ginger Tea",
                    "beverage",
                    "Ginger tea is a warming drink for cool weather, ...",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{}
            )
    };

    final static RecipeModel[] RECIPES_CATEGORY_DESSERT_REVERSED = new RecipeModel[]
            {RECIPES[14], RECIPES[13], RECIPES[7], RECIPES[6], RECIPES[5], RECIPES[3], RECIPES[2]};

    final static RecipeModel[] RECIPES_NAME_CONTAINS_ICE_CREAM_REVERSED = new RecipeModel[]
            {RECIPES[14], RECIPES[13], RECIPES[10], RECIPES[6], RECIPES[4], RECIPES[2]};


    static String recipeToJson(RecipeModel recipe) {
        return "{\"name\":\"" + recipe.getName() +
                "\",\"category\":\"" + recipe.getCategory() +
                "\",\"description\":\"" + recipe.getDescription() +
                "\",\"ingredients\":\"" + Arrays.toString(recipe.getIngredients()) +
                "\",\"directions\":\"" + Arrays.toString(recipe.getDirections()) +
                "\"}";
    }


//    public static String listUsersToStr(List<User> users) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("[");
//
//        for (User user : users) {
//            builder.append("{")
//                    .append("\"email\":\"")
//                    .append(user.getEmail())
//                    .append("\",\"password\":\"")
//                    .append(user.getPassword())
//                    .append(".\"},");
//        }
//
//        if (!builder.toString().equals("[]"))
//            builder.deleteCharAt(builder.toString().length() - 1);
//
//        builder.append("]");
//        return builder.toString();
//    }
}
