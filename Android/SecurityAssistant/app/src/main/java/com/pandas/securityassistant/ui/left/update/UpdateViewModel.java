package com.pandas.securityassistant.ui.left.update;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpdateViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UpdateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 更新fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}