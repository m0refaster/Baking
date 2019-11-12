package com.shawn.nichol.bakingapp.Data;

import android.util.Log;

import com.shawn.nichol.bakingapp.Activities.InstructionsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstructionsExtractIngredients {
    private static final String LogTag = "MyLog " + InstructionsActivity.class.getSimpleName();

    public static ArrayList<String> ingredientList = new ArrayList<>();


    public void ingredientsJson(String jsonString) {
        Log.d(LogTag, jsonString);

        ingredientList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;

            for(int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                String PARAM_QUANTITY = "quantity";
                String PARAM_MEASURE = "measure";
                String PARAM_INGREDIENTS = "ingredient";
                ingredientList.add(jsonObject.getString(PARAM_INGREDIENTS) + " " +
                jsonObject.getString(PARAM_QUANTITY) + " " +
                jsonObject.getString(PARAM_MEASURE));

                Log.d(LogTag, ingredientList.get(i));
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

}
