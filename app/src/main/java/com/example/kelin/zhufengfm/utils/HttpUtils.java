package com.example.kelin.zhufengfm.utils;

/**
 * Created by kelin on 2016/4/6.
 */

import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 网络请求的工具类
 */

public final class HttpUtils {
    public static final int TIMEOUT_CONNECT = 3000;
    //数据读取的超时时间
    public static final int TIMEOUT_READ = 5000;
    public static final String USER_AGENT = "ting_4.1.7(" + Build.MODEL + "," + Build.VERSION.SDK_INT + ")";

    private  HttpUtils() {
    }

    /**
     * get请求
     */
    public static byte[] doGet(String url){

        byte[] ret = null;

        if (url != null) {
            HttpURLConnection conn = null;
            InputStream in = null;

            try {
                URL u = new URL(url);
                conn = ((HttpURLConnection) u.openConnection());

                // 1. 请求的方法,常见的方法get post put  delete  head
                conn.setRequestMethod("GET");

                // 2. 超时设置
                conn.setConnectTimeout(TIMEOUT_CONNECT);
                conn.setReadTimeout(TIMEOUT_READ);

                // 3. 网络请求的附加信息 HTTP头字段
                // User-Agent 简称UA，代表客户端的类型
                //通常浏览器都会发送该字段
                //听书要求的格式：
                //  ting_应用程序版本号（手机的型号，手机的api级别）
                conn.setRequestProperty(
                        "User-Agent",
                        USER_AGENT);

                // 3.2 请求内容压缩

//                conn.setRequestProperty("Accept-Encoding","gzip");

                conn.connect();

                int code = conn.getResponseCode();
                // 200
                // 301,302,307
                // 304
                // 404
                // 405
                // 500
                //[200,300)成功
                //[300,400)大部分重定向，是缓存内容没改变
                //[400,500)客户端错误，请求失败
                //[500,600)服务器错误

                if (code== HttpURLConnection.HTTP_OK) {

                    //关于服务器与客户端之间数据的压缩
                    //1.在请请求发送http头字段的时候，需要添加在段
                    // Accept-Encoding 对应数值是“gzip”
                    //
                    // 服务器就会根据这个内容压缩json数据

                    //TODO: 检测服务器的返回数据时候经过了压缩



                    in = conn.getInputStream();

                    String encoding = conn.getContentEncoding();

                    if ("gzip".equals(encoding)) {
                        in = new GZIPInputStream(in);
                    }

                    ret = StreanUtil.readStream(in);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreanUtil.close(in);
            }

        }


        return ret;
    }
}

