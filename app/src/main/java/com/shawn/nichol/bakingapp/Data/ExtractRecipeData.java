package com.shawn.nichol.bakingapp.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExtractRecipeData {
    private final static String LOGTAG = "RecipeExtractRecipeData";
    // TODO remove, only for testing purposes
    public static ArrayList<String> fillerList = new ArrayList<>();

    // JSON array lists
    public static ArrayList<String> recipeList = new ArrayList<>();
    public static ArrayList<String> ingredientsJSONList = new ArrayList<>();
    public static ArrayList<String> stepsJSONList = new ArrayList<>();
    public static ArrayList<String> servingsJSONList = new ArrayList<>();
    public static ArrayList<String> imageJSONList = new ArrayList<>();

    final String PARAM_NAME = "name";
    final String PARAM_INGREDIENTS = "ingredients";
    final String PARAM_STEPS = "steps";
    final String PARAM_SERVERINS = "servins";
    final String PARAM_IMAGE = "image";

    public void recipeName(String jsonString) {

        recipeList = new ArrayList<>();
        ingredientsJSONList = new ArrayList<>();
        stepsJSONList = new ArrayList<>();
        servingsJSONList = new ArrayList<>();
        imageJSONList = new ArrayList<>();

        Log.d(LOGTAG, "RecipeList " + recipeList);

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                recipeList.add(jsonObject.getString(PARAM_NAME));
                ingredientsJSONList.add(jsonObject.getString(PARAM_INGREDIENTS));
                stepsJSONList.add(jsonObject.getString(PARAM_STEPS));
//                servingsJSONList.add(jsonObject.getString(PARAM_SERVERINS));
//                imageJSONList.add(jsonObject.getString(PARAM_IMAGE));
            }

        Log.d(LOGTAG, PARAM_NAME + recipeList);
        Log.d(LOGTAG, PARAM_INGREDIENTS + ingredientsJSONList);
        Log.d(LOGTAG, PARAM_STEPS + stepsJSONList);
        Log.d(LOGTAG, PARAM_SERVERINS + servingsJSONList);
        Log.d(LOGTAG, PARAM_IMAGE + imageJSONList);

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
