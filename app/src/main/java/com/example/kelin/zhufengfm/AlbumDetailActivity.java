package com.example.kelin.zhufengfm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kelin.zhufengfm.media.PlayService;

public class AlbumDetailActivity extends AppCompatActivity {

    private long mAlbumId;
    private long mTrackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumn_detail_activity);

        Intent intent = getIntent();
        //专辑Id
        mAlbumId = intent.getLongExtra(Constants.EXTRA_ALBUM_ID, -1);
        //曲目Id
        mTrackId = intent.getLongExtra(Constants.EXTRA_TRACK_ID, -1);

        //TODO:调用接口17显示基本的曲目列表，和一部分专家信息。。是否正在播放
        //  17主要是列表

        //TODO： 调用解耦18  显示专辑详情部分的用户信息，和详情信息
        //  18主要是详情

        //TODO： 接口20 ，将专辑ID和曲目ID传递给SERVICE 让服务加载新的播放列表

        if (mAlbumId != -1 && mTrackId != -1) {


            Intent service = new Intent(this, PlayService.class);

            service.putExtra(Constants.EXTRA_ALBUM_ID, mAlbumId);
            service.putExtra(Constants.EXTRA_TRACK_ID, mTrackId);

            service.putExtra(Constants.EXTRA_OPERATION, Constants.EXTRA_PLAYLIST);
            Log.d("ddd", "onCreate: Activity"+mAlbumId+"::"+mTrackId);
            startService(service);
        }

    }
}
