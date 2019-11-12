package com.shawn.nichol.bakingapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.nichol.bakingapp.Activities.InstructionsActivity;
import com.shawn.nichol.bakingapp.Adapters.RecipeListAdapter;
import com.shawn.nichol.bakingapp.Data.ExtractRecipeData;
import com.shawn.nichol.bakingapp.R;

import java.util.Objects;


public class RecipeListFragment extends Fragment {
    private final static String LogTag = "MyLog " + RecipeListFragment.class.getSimpleName();

    //
    public RecipeListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle onSavedInstanceState) {

        RecyclerView mRecyclerView;
        RecipeListAdapter mAdapter;

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        mRecyclerView = view.findViewById(R.id.rv_recipe);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(Objects.requireNonNull(getActivity()).getApplicationContext(),
                mRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.d(LogTag, "Clicked " + position);
                Log.d(LogTag, "Recipe: " + ExtractRecipeData.recipeList.get(position));

                Intent intent = new Intent(getActivity(), InstructionsActivity.class);

                intent.putExtra("recipe", ExtractRecipeData.recipeList.get(position));
                intent.putExtra("ingredients", ExtractRecipeData.ingredientsJSONList.get(position));
                intent.putExtra("steps", ExtractRecipeData.stepsJSONList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mAdapter = new RecipeListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
