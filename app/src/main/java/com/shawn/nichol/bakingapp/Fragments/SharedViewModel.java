package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private int stepPosition;

    public void setStepPosition(int position) {

        stepPosition = position;
    }


    public int getStepPosition() {
        return stepPosition;
    }
}
