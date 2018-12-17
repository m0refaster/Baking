package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> stepPosition = new MutableLiveData<>();

    public void setStepPosition(String position) {
        stepPosition.setValue(position);
    }

    public MutableLiveData getStepPosition() {
        return stepPosition;
    }
}
