package com.pandas.securityassistant.ui.bottom.tongxunlu;

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

public class TongxunluFragment extends Fragment {
    public TongxunluFragment() {
    }
//    private TongxunluViewModel mTongxunluViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

//        mTongxunluViewModel =ViewModelProviders.of(this).get(TongxunluViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_bottom_tongxunlu, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        mTongxunluViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//                System.out.println("s: "+s);
//            }
//        });
//        return root;
        return inflater.inflate(R.layout.fragment_bottom_tongxunlu, container, false);
    }
}