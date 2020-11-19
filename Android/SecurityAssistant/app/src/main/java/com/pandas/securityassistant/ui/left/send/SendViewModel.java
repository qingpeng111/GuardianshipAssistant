package com.pandas.securityassistant.ui.left.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 发送 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}