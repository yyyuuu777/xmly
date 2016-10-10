package com.example.kelin.zhufengfm.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kelin.zhufengfm.R;
import com.example.kelin.zhufengfm.adapter.CommonPagerAdapter;
import com.example.kelin.zhufengfm.fragment.discovery.DiscoveryAnchorFragment;
import com.example.kelin.zhufengfm.fragment.discovery.DiscoveryCategoryFragment;
import com.example.kelin.zhufengfm.fragment.discovery.DiscoveryRadioFragment;
import com.example.kelin.zhufengfm.fragment.discovery.DiscoveryRatingFragment;
import com.example.kelin.zhufengfm.fragment.discovery.DiscoveryRecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {


    private ViewPager mPager;

    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.discovery_fragment, container, false);
//        1.获取tabBar

        TabLayout tabBar = (TabLayout) ret.findViewById(R.id.discovery_tab_bar);

        // TODO: TabLayout 与 ViewPager联动

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new DiscoveryRecommendFragment());
        fragments.add(new DiscoveryCategoryFragment());
        fragments.add(new DiscoveryRadioFragment());
        fragments.add(new DiscoveryRatingFragment());
        fragments.add(new DiscoveryAnchorFragment());

        mPager = (ViewPager) ret.findViewById(R.id.discovery_fragment_viewpager);
        CommonPagerAdapter adapter = new CommonPagerAdapter(getChildFragmentManager(),fragments);
        mPager.setAdapter(adapter);

        tabBar.setupWithViewPager(mPager);
        return ret;
    }


}
