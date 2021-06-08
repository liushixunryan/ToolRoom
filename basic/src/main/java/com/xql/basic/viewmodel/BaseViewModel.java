package com.xql.basic.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}