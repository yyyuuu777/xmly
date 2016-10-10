package com.example.kelin.zhufengfm.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelin on 2016/4/7.
 */
public class SpecialColumn extends DiscoveryRecommendItem {

    private List<SpecialColumnInfo> mInfos;

    public List<SpecialColumnInfo> getInfos() {
        return mInfos;
    }

    public void parseJson(JSONObject json) throws JSONException {
        super.parseJson(json);

        mInfos = new ArrayList<>();
        SpecialColumnInfo info = null;
        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            info = new SpecialColumnInfo();
            info.parseJson(jsonObject);
            mInfos.add(info);
        }
        info = null;
    }
}
