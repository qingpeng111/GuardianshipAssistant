package com.pandas.securityassistant.ui.bottom.liaotian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pandas.securityassistant.R;

public class LiaotianFragment extends Fragment {
    public LiaotianFragment() {
    }
//    private LiaotianViewModel mLiaotianViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

//        mLiaotianViewModel =ViewModelProviders.of(this).get(LiaotianViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_bottom_liaotian, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        mLiaotianViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//                System.out.println("s: "+s);
//            }
//        });
//        return root;
        return inflater.inflate(R.layout.fragment_bottom_liaotian, container, false);
    }
}