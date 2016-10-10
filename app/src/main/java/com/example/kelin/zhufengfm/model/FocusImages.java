package com.example.kelin.zhufengfm.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelin on 2016/4/7.
 */
public class FocusImages {

    private List<FocusImageInfo> mImages = new ArrayList<>();

    public List<FocusImageInfo> getImages() {
        return mImages;
    }

    public void parseJson(JSONObject json) throws JSONException {
        FocusImageInfo image = null;
        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {
            JSONObject object = list.getJSONObject(i);
            image = new FocusImageInfo();
            image.parseJson(object);
            mImages.add(image);
        }
    }



}
