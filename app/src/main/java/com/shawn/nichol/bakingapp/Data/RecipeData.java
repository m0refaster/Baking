package com.shawn.nichol.bakingapp.Data;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.shawn.nichol.bakingapp.Fragments.RecipeListFragment;
import com.shawn.nichol.bakingapp.R;
import com.shawn.nichol.bakingapp.Utils.NetworkUtils;

import java.net.URL;

public class RecipeData extends AsyncTask<String, Void, String> {

    private static final String LogTag = "MyLog " + RecipeData.class.getSimpleName();
    private final FragmentManager mFragmentManager;

    public RecipeData(FragmentManager fm) {
        mFragmentManager = fm;
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            URL bakingRequestUrl = NetworkUtils.buildRecipeUrl();
            return NetworkUtils.getResponseFromHttpUrl(bakingRequestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Log.d(LogTag, "Recipe list " + ExtractRecipeData.recipeList);
        ExtractRecipeData data = new ExtractRecipeData();
        data.recipeName(result);

        RecipeListFragment mRecipeFragment = new RecipeListFragment();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.recipe_place_holder, mRecipeFragment)
                .commit();


    }
}

