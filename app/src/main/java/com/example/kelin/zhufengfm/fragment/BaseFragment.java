package com.example.kelin.zhufengfm.fragment;

import android.support.v4.app.Fragment;



/**
 * 设计该基础类定义一系类带标题的Fragment
 */
public class BaseFragment extends Fragment {

    /**
     * 要求fragment返回自己的标题。默认标题是一样的
     * 只在获取标题的时候才调用这个方法
     * @return
     */
    public String getFragmentTitle(){
        return "No Title";
    }

}
