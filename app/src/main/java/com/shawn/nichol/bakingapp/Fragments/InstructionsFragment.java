package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shawn.nichol.bakingapp.Activities.InstructionsActivity;
import com.shawn.nichol.bakingapp.Adapters.InstructionsAdapter;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractIngredients;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractSteps;
import com.shawn.nichol.bakingapp.R;

public class InstructionsFragment extends Fragment {
    private static final String LOGTAG = "InstructionsFragment";

    public InstructionsActivity mInstructionsActivity;
    private boolean mTwoPaness;

    private RecyclerView mRecyclerView;
    private InstructionsAdapter mAdapter;

    private String mAllIngredients;

    private SharedViewModel model;

    // Empty constructor
    public InstructionsFragment() {
    }



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle onSavedInstanceState) {

        mInstructionsActivity = (InstructionsActivity) getActivity();
        mTwoPaness = mInstructionsActivity.mTwoPane ;


        View view = inflater.inflate(R.layout.fragment_instructions, container, false);

        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_instructions_rv_fragments);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(),
                mRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.d(LOGTAG, "Step " + (position + 1) + " " +
                        InstructionsExtractSteps.stepsShortDescriptionList.get(position));

                model.setStepPosition(position);


                FragmentManager mFragmentManager = getFragmentManager();

                StepsFragment mStepsFragment = new StepsFragment();

                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

                if(mTwoPaness == true) {
                    mFragmentTransaction
                            .replace(R.id.steps_place_holder, mStepsFragment)
                            // Puts InstructionsFragment on back stack, when back button is press it will
                            // reload that fragment instead of going back to the RecipeActivity.
                            .addToBackStack(null)
                            .commit();
                } else {

                    mFragmentTransaction
                            .replace(R.id.instructions_place_holder, mStepsFragment)
                            // Puts InstructionsFragment on back stack, when back button is press it will
                            // reload that fragment instead of going back to the RecipeActivity.
                            .addToBackStack(null)
                            .commit();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        mAdapter = new InstructionsAdapter();
        mRecyclerView.setAdapter(mAdapter);





        return view;
    }

    /**
     * onViewCreated: I found this to be the only way to update the TextView in fragment_instructions.xml
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        TextView mLoadIngredients = (TextView)getView().findViewById(R.id.ingredients_tv_fragment);

        // Set ingredients to screen
        for(int i = 0; i < InstructionsExtractIngredients.ingredientList.size(); i++) {
            if(i == 0) {
                mAllIngredients = "- " + InstructionsExtractIngredients.ingredientList.get(i);
            } else {
                mAllIngredients = mAllIngredients + "\n - " + InstructionsExtractIngredients.ingredientList.get(i);
            }
        }
        Log.d(LOGTAG, mAllIngredients);
        mLoadIngredients.setText(mAllIngredients);
    }






}
