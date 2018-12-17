package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.nichol.bakingapp.R;

public class StepsFragment extends Fragment {
    private static final String LOGTAG = "StepsFragment";

    // Requires an empty constructor
    public StepsFragment() {
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        final SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        model.getStepPosition();

        Log.d(LOGTAG, "THIS " + model);

        return view;
    }
}
