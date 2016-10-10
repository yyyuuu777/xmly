package com.example.kelin.zhufengfm.fragment.discovery;


import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kelin.zhufengfm.AlbumDetailActivity;
import com.example.kelin.zhufengfm.Constants;
import com.example.kelin.zhufengfm.R;
import com.example.kelin.zhufengfm.adapter.ImageAdapter;
import com.example.kelin.zhufengfm.adapter.RecommendAdapter;
import com.example.kelin.zhufengfm.fragment.BaseFragment;
import com.example.kelin.zhufengfm.media.PlayService;
import com.example.kelin.zhufengfm.model.DiscoveryColumns;
import com.example.kelin.zhufengfm.model.DiscoveryRecommendItem;
import com.example.kelin.zhufengfm.model.FocusImages;
import com.example.kelin.zhufengfm.model.RecommendAlbumInfo;
import com.example.kelin.zhufengfm.model.RecommendAlbums;
import com.example.kelin.zhufengfm.model.SpecialColumn;
import com.example.kelin.zhufengfm.tasks.DiscoveryRecommendTask;
import com.example.kelin.zhufengfm.tasks.TaskCallBack;
import com.example.kelin.zhufengfm.tasks.TaskResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现  推荐部分
 */
public class DiscoveryRecommendFragment extends BaseFragment implements TaskCallBack, View.OnClickListener {

    private RecommendAdapter mAdapter;

    private List<DiscoveryRecommendItem> mItems;

    private FocusImages mFocusImages;
    private ImageAdapter mImageAdapter;
    private ViewPager mPager;

    public DiscoveryRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        mFocusImages = new FocusImages();
        mAdapter = new RecommendAdapter(getContext(),mItems);
        mAdapter.setOnClickListener(this);
        DiscoveryRecommendTask task = new DiscoveryRecommendTask(this);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.discovery_recommend_fragment, container, false);

        ListView listView = (ListView) ret.findViewById(R.id.discovery_recommend_list);

        listView.setAdapter(mAdapter);

        mPager = new ViewPager(getContext());
        mPager.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                550));

        mImageAdapter = new ImageAdapter(getContext(),mFocusImages.getImages());

        mPager.setAdapter(mImageAdapter);
        listView.addHeaderView(mPager);

        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "推荐";
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int state = result.state;
            if (state==0) {
                Object data = result.data;
                if (data != null) {

                    if (data instanceof JSONObject) {
                        JSONObject jsonObject = (JSONObject) data;

                        try {

                            //获取小编推荐
                            JSONObject object = jsonObject.getJSONObject("editorRecommendAlbums");
                            RecommendAlbums albums = new RecommendAlbums();
                            albums.parseJson(object);
                            mItems.add(albums);

                            //获得轮播图片
                            object = jsonObject.getJSONObject("focusImages");

                            mFocusImages.parseJson(object);

                            //获取精品听单

                            object = jsonObject.getJSONObject("specialColumn");
                            SpecialColumn sColumn = new SpecialColumn();
                            sColumn.parseJson(object);
                            Log.d("ddd", "onTaskFinished: "+sColumn);
                            mItems.add(sColumn);

                            //获取发现新奇

                            object = jsonObject.getJSONObject("discoveryColumns");
                            DiscoveryColumns columns = new DiscoveryColumns();
                            columns.parseJson(object);
                            mItems.add(columns);

                            //获取听新闻
                            object = jsonObject.getJSONObject("hotRecommends");
                            JSONArray list = object.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                albums = new RecommendAlbums();
                                albums.parseJson(list.getJSONObject(i));
                                mItems.add(albums);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter.notifyDataSetChanged();
                        mImageAdapter.notifyDataSetChanged();
                    }

                }
            }else if (state==8) {
                Snackbar.make(getView(),"网络无响应",Snackbar.LENGTH_SHORT).show();
            }else if (state==9) {
                Snackbar.make(getView(),"服务器数据错误",Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * ListView中专辑Item的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        RecommendAlbumInfo info = (RecommendAlbumInfo) tag;

        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);

        intent.putExtra(Constants.EXTRA_ALBUM_ID,info.getAlbumId());
        intent.putExtra(Constants.EXTRA_TRACK_ID,info.getTrackId());

        startActivity(intent);



    }
}
