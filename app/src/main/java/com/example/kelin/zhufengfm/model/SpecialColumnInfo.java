package com.example.kelin.zhufengfm.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kelin on 2016/4/7.
 */
public class SpecialColumnInfo {


    /**
     * columnType : 1
     * specialId : 348
     * title : 5个如雷贯耳的脱口秀，高智商必听！
     * subtitle : 人生不应该只有眼前的苟且，还有诗和远方！
     * 生活从来就不应该只有简单的吃饭穿衣，安身立命，人生除了温饱，还应该有另一个精神的高度，最终决定这个高度的，一定是眼界和见识，以及你碰到一个什么样的人生导师
     * footnote : 5张专辑
     * coverPath : http://fdfs.xmcdn.com/group14/M04/4E/68/wKgDY1WyIl_jgde2AAG79hZ3Ml4209_mobile_small.jpg
     * contentType : 1
     */

    private int columnType;
    private long specialId;
    private String title;
    private String subtitle;
    private String footnote;
    private String coverPath;
    private String contentType;

    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            columnType = json.getInt("columnType");
            specialId = json.getLong("specialId");
            title = json.getString("title");
            subtitle = json.getString("subtitle");
            footnote = json.getString("footnote");
            coverPath = json.getString("coverPath");
            contentType = json.getString("contentType");
        }
    }

    public int getColumnType() {
        return columnType;
    }

    public long getSpecialId() {
        return specialId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getFootnote() {
        return footnote;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public String getContentType() {
        return contentType;
    }


}
