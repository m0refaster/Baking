package com.shawn.nichol.bakingapp.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExtractRecipeData {
    private final static String LOGTAG = "RecipeExtractRecipeData";

    // JSON array lists
    public static ArrayList<String> recipeList = new ArrayList<>();
    public static ArrayList<String> ingredientsJSONList = new ArrayList<>();
    public static ArrayList<String> stepsJSONList = new ArrayList<>();


    public void recipeName(String jsonString) {

        final String PARAM_NAME = "name";
        final String PARAM_INGREDIENTS = "ingredients";
        String PARAM_STEPS = "steps";

        recipeList = new ArrayList<>();
        ingredientsJSONList = new ArrayList<>();
        stepsJSONList = new ArrayList<>();

        Log.d(LOGTAG, "RecipeList " + recipeList);

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                recipeList.add(jsonObject.getString(PARAM_NAME));
                ingredientsJSONList.add(jsonObject.getString(PARAM_INGREDIENTS));
                stepsJSONList.add(jsonObject.getString(PARAM_STEPS));
            }

        Log.d(LOGTAG, PARAM_NAME + recipeList);
        Log.d(LOGTAG, PARAM_INGREDIENTS + ingredientsJSONList);
        Log.d(LOGTAG, PARAM_STEPS + stepsJSONList);

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
