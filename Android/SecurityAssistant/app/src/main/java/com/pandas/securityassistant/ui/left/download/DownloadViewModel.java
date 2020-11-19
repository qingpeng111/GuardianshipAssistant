package com.pandas.securityassistant.ui.left.download;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DownloadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DownloadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 下载 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}