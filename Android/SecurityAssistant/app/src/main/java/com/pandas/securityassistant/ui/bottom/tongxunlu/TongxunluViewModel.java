package com.pandas.securityassistant.ui.bottom.tongxunlu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TongxunluViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TongxunluViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("通讯录");
    }

    public LiveData<String> getText() {
        return mText;
    }
}