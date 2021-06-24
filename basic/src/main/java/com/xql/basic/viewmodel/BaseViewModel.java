package com.xql.basic.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    protected final String TAG = "sansuiban";
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}