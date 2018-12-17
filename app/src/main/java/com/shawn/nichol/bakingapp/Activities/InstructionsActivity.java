package com.shawn.nichol.bakingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.shawn.nichol.bakingapp.Data.InstructionsExtractIngredients;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractSteps;
import com.shawn.nichol.bakingapp.Fragments.InstructionsFragment;
import com.shawn.nichol.bakingapp.R;

public class InstructionsActivity extends AppCompatActivity {

    private static final String LOGTAG = "InstructionsActivity";

    // Keys for data passed by Intent
    private String mRecipeKey;
    private String mIngredientsKey;
    private String mStepsKey;
    private String mImageKey;

    public int mStepPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //TODO viewmodel test


        Intent mainIntent = getIntent();

        mRecipeKey = mainIntent.getStringExtra("recipe");
        mIngredientsKey = mainIntent.getStringExtra("ingredients");
        mStepsKey = mainIntent.getStringExtra("steps");

        // Sets title on Screen
        setTitle(mRecipeKey + " Instructions");

        // Parse JSON for Ingredients
        InstructionsExtractIngredients instructionsJSON = new InstructionsExtractIngredients();
        instructionsJSON.ingredientsJson(mIngredientsKey);

        InstructionsExtractSteps stepsJSON = new InstructionsExtractSteps();
        stepsJSON.stepsJson(mStepsKey);



        // TODO will need move fragment manager over to recyler view to display data.
        FragmentManager mFragmentManager = getSupportFragmentManager();

        InstructionsFragment mRecipeFragment = new InstructionsFragment();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.instructions_place_holder, mRecipeFragment)
                .commit();



    }


}



