package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModel;

class SharedViewModel extends ViewModel {
    private int stepPosition;


    public void setStepPosition(int position) {

        stepPosition = position;
    }


    public int getStepPosition() {
        return stepPosition;
    }
}
