package com.shawn.nichol.bakingapp.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.shawn.nichol.bakingapp.Data.RecipeData;
import com.shawn.nichol.bakingapp.R;

public class RecipeActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Create Fragment Manager
        mFragmentManager = getSupportFragmentManager();

        // Gets JSON data, pass FragmentManger to constructor of RecipeData
        RecipeData data = new RecipeData(mFragmentManager);
        data.execute();
    }

}
