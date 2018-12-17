package com.shawn.nichol.bakingapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
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


public class RecipeListFragment extends Fragment {
    private final static String LOGTAG = "RecipeRecipeListFragment";

    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle onSavedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recipe);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(),
                mRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.d(LOGTAG, "Clicked " + position);
                Log.d(LOGTAG, "Recipe: " + ExtractRecipeData.recipeList.get(position));

                Intent intent = new Intent(getActivity(), InstructionsActivity.class);

                intent.putExtra("recipe", ExtractRecipeData.recipeList.get(position));
                intent.putExtra("ingredients", ExtractRecipeData.ingredientsJSONList.get(position));
                intent.putExtra("steps", ExtractRecipeData.stepsJSONList.get(position));
//                intent.putExtra("image", ExtractRecipeData.imageJSONList.get(position));
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
