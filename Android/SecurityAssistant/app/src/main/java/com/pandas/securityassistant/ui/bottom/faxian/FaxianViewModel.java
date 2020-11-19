package com.pandas.securityassistant.ui.bottom.faxian;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaxianViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FaxianViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("发现");
    }

    public LiveData<String> getText() {
        return mText;
    }
}