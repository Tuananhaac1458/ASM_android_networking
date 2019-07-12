package com.example.asm_android_networking.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Manage_fragment_adapter extends FragmentPagerAdapter {

private final List<Fragment> mFragmentList = new ArrayList<>();
private final  List<String> mStringTitleFragment = new ArrayList<>();

public void addFragment(Fragment fragment, String title){
    mFragmentList.add(fragment);
    mStringTitleFragment.add(title);
}
    public Manage_fragment_adapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStringTitleFragment.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
