package com.pandas.guardianshipassistant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.activity.bottom.MySetting;
import com.pandas.guardianshipassistant.ui.activity.bottom.PersonPage;

public class FragmentBottomMy extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_my, container, false);
        return view;
    }

    //获取点击的
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myJumpActivity();
    }

    private void myJumpActivity() {
        View btnPersonPage = getActivity().findViewById(R.id.btn_my_person_page);
        View   btnSetting =   getActivity().findViewById(R.id.btn_my_setting);
        btnPersonPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonPage.class);
                startActivity(intent);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySetting.class);
                startActivity(intent);
            }
        });


    }
}
