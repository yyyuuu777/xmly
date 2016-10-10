package com.example.kelin.zhufengfm.adapter;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.kelin.zhufengfm.model.FocusImageInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kelin on 2016/4/7.
 */
public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<FocusImageInfo> mImages;
    private Pools.Pool<ImageView> pool;

    public ImageAdapter(Context context, List<FocusImageInfo> mImages) {
        this.mContext = context;
        this.mImages = mImages;

        pool = new Pools.SimplePool<>(3);
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mImages != null) {
            count = mImages.size();
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = pool.acquire();
        if (imageView == null) {
            imageView = new ImageView(mContext);
        }
        Picasso.with(mContext).load(mImages.get(position).getPic()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }


}

