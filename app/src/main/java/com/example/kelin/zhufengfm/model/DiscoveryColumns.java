package com.example.kelin.zhufengfm.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelin on 2016/4/7.
 */
public class DiscoveryColumns extends DiscoveryRecommendItem {

    private List<DiscoveryColumnsInfo> mInfos;

    public List<DiscoveryColumnsInfo> getInfos() {
        return mInfos;
    }

    public void parseJson(JSONObject json) throws JSONException {
        super.parseJson(json);
        if (json != null) {
            mInfos = new ArrayList<>();
            JSONArray list = json.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                DiscoveryColumnsInfo info = new DiscoveryColumnsInfo();
                info.parseJson(list.getJSONObject(i));
                mInfos.add(info);
            }
        }

    }


}
