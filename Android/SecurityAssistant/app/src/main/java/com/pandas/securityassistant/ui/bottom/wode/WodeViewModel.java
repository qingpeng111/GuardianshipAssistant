package com.pandas.securityassistant.ui.bottom.wode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WodeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WodeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("我的");
    }

    public LiveData<String> getText() {
        return mText;
    }
}