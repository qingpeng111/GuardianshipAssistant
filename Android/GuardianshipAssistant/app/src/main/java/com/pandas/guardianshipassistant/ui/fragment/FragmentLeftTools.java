package com.pandas.guardianshipassistant.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pandas.guardianshipassistant.R;

public class FragmentLeftTools extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_left_tools,container,false);
        System.out.println("onCreateView");
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        System.out.println("onAttach");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated");
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        System.out.println("onStart");
    }
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("onPause");
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        System.out.println("onStop");
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        System.out.println("onDestory");
    }
    /*
     * 在Fragment与调用它的Activity分离时调用
     */
    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        System.out.println("onDetach");
    }
}
