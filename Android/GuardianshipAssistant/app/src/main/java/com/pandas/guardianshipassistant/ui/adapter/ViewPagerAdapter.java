package com.pandas.guardianshipassistant.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments ){
        super(fm);
        this.fragments=fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}