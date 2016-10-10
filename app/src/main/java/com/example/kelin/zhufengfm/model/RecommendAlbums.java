package com.example.kelin.zhufengfm.model;

/**
 * Created by kelin on 2016/4/6.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 小编推荐和听新闻。。。
 */
public class RecommendAlbums extends DiscoveryRecommendItem {

    private List<RecommendAlbumInfo> albums;

    /**
     * 用于解析小编推荐和热门推荐中的子类的的内容
     * @param json
     */
    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            super.parseJson(json);

            JSONArray array = json.getJSONArray("list");
            albums = new ArrayList<>();
            RecommendAlbumInfo info = null;
            int len = array.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                 info = new RecommendAlbumInfo();

                info.parseJson(jsonObject);

                albums.add(info);
            }
            info = null;

        }
    }

    public List<RecommendAlbumInfo> getAlbums() {
        return albums;
    }
}
