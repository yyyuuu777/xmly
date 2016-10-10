package com.example.kelin.zhufengfm;

/**
 * Created by kelin on 2016/4/5.
 */
public final class Constants {

    private Constants(){

    }
    //约定startService的时候特定的操作参数，每一次startService都要设定
    //服务就根据
    public static final int EXTRA_PLAYLIST = 1;

    public static final String EXTRA_OPERATION = "op";

    public static final String SP_NAME = "app";

    public static final String SP_KEY_TUTORIAL_SHOWN = "tutorial.shown";

    public static final String EXTRA_ALBUM_ID = "albumId";

    public static final String EXTRA_TRACK_ID = "trackId";
}
