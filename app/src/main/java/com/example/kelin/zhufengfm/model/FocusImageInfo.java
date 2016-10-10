package com.example.kelin.zhufengfm.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kelin on 2016/4/7.
 */
public class FocusImageInfo {

    /**
     * id : 3597
     * shortTitle : 中国每天2亿人乘电梯，安全吗？
     * longTitle : 中国每天2亿人乘电梯，安全吗？
     * pic : http://fdfs.xmcdn.com/group15/M06/52/77/wKgDaFW16vfD9nFSAAHBRSk6xm4459_android_large.jpg
     * type : 2
     * uid : 30495264
     * albumId : 2814299
     * isShare : false
     * is_External_url : false
     */

    private long id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private long uid;
    private long albumId;
    private boolean isShare;
    private boolean is_External_url;

    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {
            id = json.optLong("id");
            shortTitle = json.optString("shortTitle");
            longTitle = json.getString("longTitle");
            pic = json.optString("pic");
            type = json.optInt("type");
            uid = json.optLong("uid");
            albumId = json.optLong("albumId",-1);
            isShare = json.optBoolean("isShare");
            is_External_url = json.optBoolean("is_External_url");
        }
    }

    public long getId() {
        return id;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getPic() {
        return pic;
    }

    public int getType() {
        return type;
    }

    public long getUid() {
        return uid;
    }

//    public long getAlbumId() {
//        return albumId;
//    }

    public boolean isShare() {
        return isShare;
    }

    public boolean is_External_url() {
        return is_External_url;
    }

}
