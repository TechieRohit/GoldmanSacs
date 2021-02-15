package com.example.goldmansacs.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BaseViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> progressStatus = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> progressStatus() {
        return progressStatus;
    }

    public LiveData<String> error() {
        return error;
    }

}
