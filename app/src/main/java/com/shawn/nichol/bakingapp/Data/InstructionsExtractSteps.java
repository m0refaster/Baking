package com.shawn.nichol.bakingapp.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstructionsExtractSteps {
    private static final String LOGTAG = "InstructionsExtractSteps";

    public static ArrayList<String> stepsIdList = new ArrayList<>();
    public static ArrayList<String> stepsShortDescriptionList = new ArrayList<>();
    public static ArrayList<String> stepsDescriptionList = new ArrayList<>();
    public static ArrayList<String> stepsVideoList = new ArrayList<>();

    public void stepsJson(String jsonString) {

        final String PARAM_ID = "id";
        final String PARAM_SHORT_DESCRIPTION = "shortDescription";
        final String PARAM_DESCRIPTION = "description";
        final String PARAM_VIDEO = "videoURL";

        Log.d(LOGTAG, jsonString);

        stepsIdList = new ArrayList<>();
        stepsShortDescriptionList = new ArrayList<>();
        stepsDescriptionList = new ArrayList<>();
        stepsVideoList = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;

            for(int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                stepsIdList.add(jsonObject.getString(PARAM_ID));
                stepsShortDescriptionList.add(jsonObject.getString(PARAM_SHORT_DESCRIPTION));
                stepsDescriptionList.add(jsonObject.getString(PARAM_DESCRIPTION));
                stepsVideoList.add(jsonObject.getString(PARAM_VIDEO));

                Log.d(LOGTAG, PARAM_ID + stepsIdList.get(i));
                Log.d(LOGTAG, PARAM_SHORT_DESCRIPTION + stepsShortDescriptionList.get(i));
                Log.d(LOGTAG, PARAM_DESCRIPTION + stepsDescriptionList.get(i));
                Log.d(LOGTAG, PARAM_VIDEO + stepsVideoList.get(i));

            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
