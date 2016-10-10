package com.example.kelin.zhufengfm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kelin.zhufengfm.fragment.BaseFragment;

import java.util.List;

/**
 * Created by kelin on 2016/4/5.
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public CommonPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getFragmentTitle();
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        int count = 0;

        if (mFragments !=null) {
            count = mFragments.size();
        }

        return count;
    }
}
