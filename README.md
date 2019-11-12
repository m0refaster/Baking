# Baking

Contains a list of backing recipes, each recipe contains a list of ingredients and step by step
instructions, some instruction contain videos.

## Recipe List

Retrieves a list of backing recipes from a URL, user is able to select a recipe for step by step
instructions.

The following class are used to retrieve a list of backing recipes from a URL.
* **RecipeActivity**
* **RecipeData**, calls the **RecipeListFragment** with the data returned from **NetworkUtils**
* **NetworkUtils**, retrieves the URI data from the website.
* **ExtractRecipeData**, Extras the recipe data from JSON
* RecipeListFragment, displays the baking recipes in a recyclerview, allowing the user to select a recipe.
* RecipeListAdapter, used for recyclerview.

## InstructionsActivity

Displays a list of ingredients, and step by step instructions for the selected recipe. This activity
has a two panel tablet display, to show steps and videos

* **InstructionsActivity**, Determines the if the screen is big enough for two panes,
                            parses the JSON data passed from the RecipeActivity.
* **InstructionsFragment**, Displays the list of ingredients and steps.
* **StepsFragment**,  Displays text instructions and loads a video in EXO player if one is available.
* **SharedViewModel**, holds selected step position.
* **InstructionsExtractIngredients**, extracts instructions
* **InstructionsExtractSteps**, extracts steps

## Dependencies

* ButterKnife
* CardView
* Espresso
* Exo Player
* LifeCycle
* RecyclerView


## Udacity
This project was completed as part of the Udacity advanced android nano degree program.


