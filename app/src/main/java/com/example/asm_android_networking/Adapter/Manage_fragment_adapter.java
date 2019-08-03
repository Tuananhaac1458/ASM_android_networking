package com.example.asm_android_networking.Adapter;


import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class Manage_fragment_adapter extends FragmentPagerAdapter {

private final List<Fragment> mFragmentList = new ArrayList<>();
private final  List<String> mStringTitleFragment = new ArrayList<>();
   // Context context;
public void addFragment(Fragment fragment, String title){
    mFragmentList.add(fragment);
    mStringTitleFragment.add(title);
}
    public Manage_fragment_adapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    // set title for tablayout

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mStringTitleFragment.get(position);
//    }
    @Override
    public Fragment getItem(int position) {
        clearStack(position-1);
        return mFragmentList.get(position);

    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void clearStack(int po) {
        //Here we are clearing back stack fragment entries
      if (po == 0){
          mFragmentList.get(po).onDestroy();
      }
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
