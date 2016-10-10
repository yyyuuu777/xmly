package com.example.kelin.zhufengfm.fragment.discovery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kelin.zhufengfm.R;
import com.example.kelin.zhufengfm.fragment.BaseFragment;
import com.example.kelin.zhufengfm.tasks.DiscoveryCategoriesTask;
import com.example.kelin.zhufengfm.tasks.TaskCallBack;
import com.example.kelin.zhufengfm.tasks.TaskResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryCategoryFragment extends BaseFragment implements TaskCallBack {


    public DiscoveryCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscoveryCategoriesTask task = new DiscoveryCategoriesTask(this);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.discovery_category_fragment, container, false);
    }

    @Override
    public String getFragmentTitle() {
        return "分类";
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            if (result.state==0) {

            }
        }
    }
}
