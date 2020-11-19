package com.pandas.securityassistant.ui.left.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 分享 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}