package com.shawn.nichol.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.shawn.nichol.bakingapp.Activities.RecipeActivity;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractIngredients;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    private static final String LOGTAG = "Widget";
//    private static String mRecipeName = "";



    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        String recipe, int appWidgetId) {

        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, RecipeActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);

        views.setTextViewText(R.id.recipe_appwidget_text, recipe);


        StringBuilder mAllIngredients = new StringBuilder();
        for(int i = 0; i < InstructionsExtractIngredients.ingredientList.size(); i++) {
            if(i == 0) {
                mAllIngredients = new StringBuilder("- " + InstructionsExtractIngredients.ingredientList.get(i));
            } else {
                mAllIngredients.append("\n - ").append(InstructionsExtractIngredients.ingredientList.get(i));

            }
        }
        views.setTextViewText(R.id.widget_list_view_text, mAllIngredients.toString());

        Log.d(LOGTAG, mAllIngredients.toString());

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.recipe_appwidget_text, pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);




    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId) {
        RecipeWidgetService.startActionRecipe(context);
    }

    public static void updateRecipeWidget(Context context, AppWidgetManager appWidgetManager,
                                          String recipe, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d(LOGTAG, "updateRecipeWidget " + recipe);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        mRecipeName = intent.getStringExtra("recipe");
//        Log.d("Widget", "TEST " + mRecipeName);
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        ComponentName componentName = new ComponentName((context.getPackageName()),
//                RecipeWidgetService.class.getName());
//        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
//
//        onUpdate(context, appWidgetManager, appWidgetIds);
    }
}

