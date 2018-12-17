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

    private String LOGTAG = "RecipeData";
    FragmentManager mFragmentManager;

    public RecipeData(FragmentManager fm) {
        mFragmentManager = fm;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL movieRequestUrl = NetworkUtils.buildRecipeUrl();
            return NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(LOGTAG, "Recipe list " + ExtractRecipeData.recipeList);
        ExtractRecipeData data = new ExtractRecipeData();
        data.recipeName(result);


        RecipeListFragment mRecipeFragment = new RecipeListFragment();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.recipe_place_holder, mRecipeFragment)
                .commit();



    }


}

