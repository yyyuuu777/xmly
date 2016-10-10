package com.example.kelin.zhufengfm.fragment.discovery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kelin.zhufengfm.R;
import com.example.kelin.zhufengfm.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryAnchorFragment extends BaseFragment {


    public DiscoveryAnchorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.discovery_anchor_fragment, container, false);
    }

    @Override
    public String getFragmentTitle() {
        return "主播";
    }
}
