package com.example.kelin.zhufengfm.model;

/**
 * Created by kelin on 2016/4/6.
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  发现模块   推荐部分  公共抽象的数据结构
 *  定义这个类，就是要让listView能够显示不同类型格式的布局
 *  实际的数据结构需要继承这个类
 */
public class DiscoveryRecommendItem {
    private String title;
    private boolean hasMore;

    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            title = json.getString("title");
            hasMore = json.optBoolean("hasMore",false);
        }
    }

    public String getTitle() {
        return title;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}
