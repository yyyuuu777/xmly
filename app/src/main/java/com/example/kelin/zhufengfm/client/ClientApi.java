package com.example.kelin.zhufengfm.client;

/**
 * Created by kelin on 2016/4/6.
 */

import com.example.kelin.zhufengfm.utils.HttpUtils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * 工具类
 * 内部就是所有要调用的接口网络请求
 * 每一个接口，实现一个方法
 * 便于统一管理网址，参数和内容
 * 对于一部任务而言，只要调用方法就行了，不需要考虑网址的问题
 */
public final class ClientApi {

    //  通常软件上线之前都是使用测试服务器。
    //  上线的时候就需要使用正式服务器的地址
    public static final String API_POINT = "http://mobile.ximalaya.com/mobile";

    private ClientApi() {
    }

    /**
     * 获取发现部分的推荐内容
     * 对应接口11
     * @return
     */
    public static String getDiscoveryRecommends(boolean includeActivity,boolean includeSpecial){
        String ret = null;
        // 1. 拼接网址
        StringBuilder sb = new StringBuilder(API_POINT);
        sb.append("/discovery/v1/recommends");
        sb.append("?channel=and-f6");
        sb.append("&device=android");
        sb.append("&includeActivity=").append(includeActivity);
        sb.append("&includeSpecial=").append(includeSpecial);
        sb.append("&scale=2");
        sb.append("&version=4.1.7.1");

        String url = sb.toString();
        // 2. 请求

        byte[] data = HttpUtils.doGet(url);

        // 3. 返回结果

        if (data != null) {
            try {
                ret = new String(data,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 获取发现部分的分类
     * 接口 12
     * @return
     */
    public static String getDiscoveryCategories(){
        String ret = null;

        StringBuilder sb = new StringBuilder(API_POINT);
        sb.append("/discovery/v1/categories");

        sb.append("?device=android");
        sb.append("&picVersion=10");
        sb.append("&scale=2");

        String url = sb.toString();

        byte[] data = HttpUtils.doGet(url);

        if (data != null) {
            try {
                ret = new String(data,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

//    分类4. 专辑 203355 中 第一页共 20条信息的播放列表信息
//
//    URL:
//
//    http://mobile.ximalaya.com/mobile/others/ca/album/track/203355/true/1/20?device=android&pageSize=20&albumId=203355&isAsc=true

    /**
     * 专辑详情的部分
     * 对应的接口  17
     * @param track
     * @param pageNum
     * @param pageSize
     * @param isAsc
     * @return
     */
    public static String getAlbumDetails(long track,int pageNum,int pageSize,boolean isAsc){
        String ret = null;
        StringBuilder sb = new StringBuilder(API_POINT);
        sb.append("/others/ca/album/track/"+track);
        sb.append("/").append(isAsc).append("/1/").append(pageSize);
        sb.append("?device=android");
        sb.append("&pageSize=").append(pageSize);
        sb.append("&albumId=").append(track);
        sb.append("&isAsc=").append(isAsc);

        String url = sb.toString();

        byte[] bytes = HttpUtils.doGet(url);

        try {
            ret = new String(bytes,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String getPlayList(long albumId,long trackId){
        String ret = null;

        StringBuilder sb = new StringBuilder(API_POINT);
//        http://mobile.ximalaya.com/mobile/playlist/album?device=android&albumId=392497&trackId=8060450
        sb.append("/playlist/album");
        sb.append("?device=android");
        sb.append("&albumId=").append(albumId);
        sb.append("&trackId=").append(trackId);

        String url = sb.toString();

        byte[] bytes = HttpUtils.doGet(url);

        if (bytes != null) {
            try {
                ret = new String(bytes,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        return ret;
    }
}
