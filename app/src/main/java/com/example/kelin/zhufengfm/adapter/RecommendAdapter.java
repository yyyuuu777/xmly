package com.example.kelin.zhufengfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelin.zhufengfm.Constants;
import com.example.kelin.zhufengfm.R;
import com.example.kelin.zhufengfm.media.PlayService;
import com.example.kelin.zhufengfm.model.DiscoveryColumns;
import com.example.kelin.zhufengfm.model.DiscoveryColumnsInfo;
import com.example.kelin.zhufengfm.model.DiscoveryRecommendItem;
import com.example.kelin.zhufengfm.model.RecommendAlbumInfo;
import com.example.kelin.zhufengfm.model.RecommendAlbums;
import com.example.kelin.zhufengfm.model.SpecialColumn;
import com.example.kelin.zhufengfm.model.SpecialColumnInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kelin on 2016/4/6.
 */

/**
 * 发现  推荐 列表adapter
 */
public class RecommendAdapter extends BaseAdapter {

    public static final int TYPE_COUNT = 3;
    public static final int TYPE_RECOMMEND = 0;
    public static final int TYPE_SPECIAL = 1;
    public static final int TYPE_DISCOVERY = 2;

    private Context mContext;
    private List<DiscoveryRecommendItem> mItems;

    private View.OnClickListener mOnClickListener;

    public RecommendAdapter(Context context, List<DiscoveryRecommendItem> items) {
        mContext = context;
        mItems = items;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;

        DiscoveryRecommendItem item = mItems.get(position);
        if (item instanceof RecommendAlbums) {
            ret = TYPE_RECOMMEND;
        } else if (item instanceof SpecialColumn) {
            ret = TYPE_SPECIAL;
        } else if (item instanceof DiscoveryColumns) {
            ret = TYPE_DISCOVERY;
        }
        return ret;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mItems != null) {
            count = mItems.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (getItemViewType(position)) {
            case TYPE_RECOMMEND:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.discovery_recommend_album_item, parent, false);
                }
                RecommendViewHolder holder = (RecommendViewHolder) convertView.getTag();
                if (holder == null) {
                    holder = new RecommendViewHolder(convertView);
                    convertView.setTag(holder);
                }
                setRecommendContent(position, holder);
                break;

            //-----------------------------------------------------------------

            case TYPE_SPECIAL:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.discovery_recommend_special_item, parent, false);
                }
                SpecialViewHolder specialHolder = (SpecialViewHolder) convertView.getTag();
                if (specialHolder == null) {
                    specialHolder = new SpecialViewHolder(convertView);
                    convertView.setTag(specialHolder);
                }

                setSpecialContent(position, specialHolder);

                break;

            //-----------------------------------------------------------------

            case TYPE_DISCOVERY:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.discovery_recommend_discovery_item, parent, false);
                }
                DiscoveryViewHolder discoveryHolder = (DiscoveryViewHolder) convertView.getTag();
                if (discoveryHolder == null) {
                    discoveryHolder = new DiscoveryViewHolder(convertView);
                    convertView.setTag(discoveryHolder);
                }

                setDiscoveryContent(position, discoveryHolder);
                break;
        }
        return convertView;
    }

    private void setRecommendContent(int position, RecommendViewHolder holder) {
        RecommendAlbums items = (RecommendAlbums) (mItems.get(position));
        holder.mHeader.setText(items.getTitle());
        for (int i = 0; i < 3; i++) {
            RecommendAlbumInfo info = items.getAlbums().get(i);
            Picasso.with(mContext).load(info.getCoverLarge()).into(holder.mImageViews[i]);
            holder.mTitles[i].setText(info.getTitle());
            holder.mTags[i].setText(info.getTags());
            holder.mImageViews[i].setOnClickListener(mOnClickListener);
            holder.mImageViews[i].setTag(info);
        }
    }

    private void setSpecialContent(int position, SpecialViewHolder holder) {
        SpecialColumn items = (SpecialColumn) mItems.get(position);
        holder.mHeader.setText(items.getTitle());
        List<SpecialColumnInfo> infos = items.getInfos();
        for (int i = 0; i < 2; i++) {
            Picasso.with(mContext).load(infos.get(i).getCoverPath()).into(holder.mImageViews[i]);
            holder.mTitles[i].setText(infos.get(i).getTitle());
            holder.mSubTitles[i].setText(infos.get(i).getSubtitle());
            holder.mFootnotes[i].setText(infos.get(i).getFootnote());
        }
    }

    private void setDiscoveryContent(int position, DiscoveryViewHolder holder) {
        DiscoveryColumns columns = (DiscoveryColumns) mItems.get(position);
        holder.mHeader.setText(columns.getTitle());
        List<DiscoveryColumnsInfo> infos = columns.getInfos();
        for (int i = 0; i < 4; i++) {
            Picasso.with(mContext).load(infos.get(i).getCoverPath()).into(holder.mImages[i]);
            holder.mTitle[i].setText(infos.get(i).getTitle());
            holder.mSubTitle[i].setText(infos.get(i).getSubtitle());
        }
    }

    private static class RecommendViewHolder {
        public TextView mHeader;
        public TextView[] mTitles = new TextView[3];
        public TextView[] mTags = new TextView[3];
        public ImageView[] mImageViews = new ImageView[3];

        public RecommendViewHolder(View itemView) {

            mHeader = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_header);

            mImageViews[0] = (ImageView) itemView.findViewById(R.id.discovery_recommend_album_item_image1);
            mImageViews[1] = (ImageView) itemView.findViewById(R.id.discovery_recommend_album_item_image2);
            mImageViews[2] = (ImageView) itemView.findViewById(R.id.discovery_recommend_album_item_image3);

            mTitles[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_title1);
            mTitles[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_title2);
            mTitles[2] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_title3);

            mTags[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_tags1);
            mTags[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_tags2);
            mTags[2] = (TextView) itemView.findViewById(R.id.discovery_recommend_album_item_tags3);

        }
    }

    public static class SpecialViewHolder {
        public TextView mHeader;
        public ImageView[] mImageViews = new ImageView[2];
        public TextView[] mTitles = new TextView[2];
        public TextView[] mSubTitles = new TextView[2];
        public TextView[] mFootnotes = new TextView[2];

        public SpecialViewHolder(View itemView) {

            mHeader = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_head);

            mImageViews[0] = (ImageView) itemView.findViewById(R.id.discovery_recommend_special_item_image1);
            mImageViews[1] = (ImageView) itemView.findViewById(R.id.discovery_recommend_special_item_image2);

            mTitles[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_title1);
            mTitles[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_title2);

            mSubTitles[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_longTitle1);
            mSubTitles[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_longTitle2);

            mFootnotes[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_footnote1);
            mFootnotes[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_special_item_footnote2);

        }
    }

    public static class DiscoveryViewHolder {
        public TextView mHeader;
        public ImageView[] mImages = new ImageView[4];
        public TextView[] mTitle = new TextView[4];
        public TextView[] mSubTitle = new TextView[4];

        public DiscoveryViewHolder(View itemView) {
            mHeader = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_head);

            mImages[0] = (ImageView) itemView.findViewById(R.id.discovery_recommend_discovery_image1);
            mImages[1] = (ImageView) itemView.findViewById(R.id.discovery_recommend_discovery_image2);
            mImages[2] = (ImageView) itemView.findViewById(R.id.discovery_recommend_discovery_image3);
            mImages[3] = (ImageView) itemView.findViewById(R.id.discovery_recommend_discovery_image4);

            mTitle[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_title1);
            mTitle[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_title2);
            mTitle[2] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_title3);
            mTitle[3] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_title4);

            mSubTitle[0] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_subtitle1);
            mSubTitle[1] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_subtitle2);
            mSubTitle[2] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_subtitle3);
            mSubTitle[3] = (TextView) itemView.findViewById(R.id.discovery_recommend_discovery_subtitle4);

        }

    }

//    View.OnClickListener listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String a = "";
//            RecommendAlbumInfo tag = (RecommendAlbumInfo) v.getTag();
//            if (tag != null) {
//                a = "" + tag.getAlbumId() + "  "+tag.getTracks();
//
//                Intent intent = new Intent(mContext, PlayService.class);
//                intent.putExtra(Constants.EXTRA_ALBUM_ID,tag.getAlbumId());
//                intent.putExtra(Constants.EXTRA_TRACK_ID,tag.getTrackId());
//                mContext.startService(intent);
//                Snackbar.make(v, "启动服务" + a, Snackbar.LENGTH_SHORT).show();
//            }else {
//
//                Snackbar.make(v, "" + a, Snackbar.LENGTH_SHORT).show();
//            }
//        }
//    };
}
