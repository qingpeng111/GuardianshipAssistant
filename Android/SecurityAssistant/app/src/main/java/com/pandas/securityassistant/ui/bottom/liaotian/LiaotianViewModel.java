package com.pandas.securityassistant.ui.bottom.liaotian;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiaotianViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LiaotianViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("主页");
    }

    public LiveData<String> getText() {
        return mText;
    }
}