package com.pandas.securityassistant.ui.bottom.baohu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaohuViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BaohuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("保护");
    }

    public LiveData<String> getText() {
        return mText;
    }
}