package com.example.bottomnavigationactivity.bottomNavigationUI.home;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Bundle> mBundle;

    public HomeViewModel() {
        mBundle = new MutableLiveData<>();
        mBundle.setValue(new Bundle());
    }


    public MutableLiveData<Bundle> getBundle(){
        return mBundle;
    }



}