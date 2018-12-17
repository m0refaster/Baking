package com.shawn.nichol.bakingapp.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstructionsExtractIngredients {
    private static final String LOGTAG = "InstructionsExtractIngredients";

    public static ArrayList<String> ingredientList = new ArrayList<>();

    final String PARAM_QUANTITY = "quantity";
    final String PARAM_MEASURE = "measure";
    final String PARAM_INGREDIENTS = "ingredient";
    final String PARAM_STEPS = "steps";

    public void ingredientsJson(String jsonString) {
        Log.d(LOGTAG, jsonString);

        ingredientList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;

            for(int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                ingredientList.add(jsonObject.getString(PARAM_INGREDIENTS) + " " +
                jsonObject.getString(PARAM_QUANTITY) + " " +
                jsonObject.getString(PARAM_MEASURE));

                Log.d(LOGTAG, ingredientList.get(i));
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

}
