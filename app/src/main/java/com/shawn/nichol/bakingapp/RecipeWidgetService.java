package com.shawn.nichol.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class RecipeWidgetService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_UPDATE_PLANT_WIDGETS = "com.shawn.nichol.bakingapp.action.update_plant_widgets";
    private String updateRecipe;
    private String updateIngredients;

    public RecipeWidgetService() {
        super("RecipeWidgetService");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateRecipe = intent.getStringExtra("recipe");
        updateIngredients = intent.getStringExtra("ingredients");
        Log.d("Widget", updateRecipe);
        Log.d("Widget", updateIngredients);
        handleActionUpdateRecipe(updateRecipe, updateIngredients);
        return super.onStartCommand(intent, flags, startId);
    }

    public static void startActionRecipe(Context context) {
        Intent intent = new Intent (context, RecipeWidgetService.class);
        intent.setAction(ACTION_UPDATE_PLANT_WIDGETS);
        context.startService(intent);
    }





    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_PLANT_WIDGETS.equals(action)){
                final String recipe = updateRecipe;
                final String ingredients = updateIngredients;
                handleActionUpdateRecipe(recipe, ingredients);
            }
        }
    }

    private void handleActionUpdateRecipe(String recipe, String updateIngredients) {
        String LOGTAG = "Widget";
        Log.d(LOGTAG, "handleActionUpdateRecipe " + recipe + " " + updateIngredients);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        BakingWidgetProvider.updateRecipeWidget(this, appWidgetManager, recipe, appWidgetIds);
    }

}
