package com.example.kelin.zhufengfm.media;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.kelin.zhufengfm.Constants;
import com.example.kelin.zhufengfm.tasks.PlayListTask;
import com.example.kelin.zhufengfm.tasks.TaskCallBack;
import com.example.kelin.zhufengfm.tasks.TaskResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PlayService extends Service implements TaskCallBack, MediaPlayer.OnPreparedListener {

    private MediaPlayer mPlayer;

    public PlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(this);
        Log.d("ddd", "onCreate: ddddddddd");
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result.state==0) {
            Object data = result.data;

            if (data != null) {
                if (data instanceof JSONObject) {
                    //简化解析，只解析一个

                    JSONObject jsonObject = (JSONObject) data;

                    try {
                        JSONArray array = jsonObject.getJSONArray("data");

                        int length = array.length();

                        if (length>0) {

                            JSONObject playListItem = array.getJSONObject(0);
                            String playUrl32 = playListItem.getString("playUrl32");

                            playMusic(playUrl32);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }

    }

    /**
     * 进行播放音频
     * @param url
     */
    private void playMusic(String url){
        if (url != null) {
            Log.d("ddd", "playMusic: 播放");

        // 1.播放之前应该停止播放中的内容，重置播放器的状态

        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        //清空所有的状态，成为初始状态，才能设置新的地址
        mPlayer.reset();

        // 2.设置MediaPlayer数据源

            try {
                mPlayer.setDataSource(url);

                // 3.设置好数据源要进行缓冲  预先加载网络数据

                //  mPlayer.prepare();//是会阻塞的代码，不要再主线程使用

                // 异步代码，准备成功调用onPrepare()的回调方法
                mPlayer.prepareAsync();

                // 4.在onCreate()设置onPrepareListener()


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    /**
     * 当MediaPlayer数据准备完成的时候调用
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public class Controller extends Binder{
        /**
         * 继续播放
         */
        public void play(){

        }

        /**
         * 暂停
         */
        public void pause(){

        }

        /**
         * 停止
         */
        public void stop(){

        }

        /**
         * 上一首
         */
        public void prev(){

        }

        /**
         * 下一首
         */
        public void next(){

        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Controller();
    }

    /**
     * 试运行在主线程的
     * 因此执行长时间操作必须在子线程中完成
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {

            //TODO:处理请求
            int op = intent.getIntExtra(Constants.EXTRA_OPERATION, -1);
            switch (op){
                case Constants.EXTRA_PLAYLIST:

                    //专辑Id
                    long aId = intent.getLongExtra(Constants.EXTRA_ALBUM_ID, -1);
                    //曲目Id
                    long tId = intent.getLongExtra(Constants.EXTRA_TRACK_ID, -1);

                    Log.d("ddd", "onStartCommand: service"+aId+":"+tId);
                    PlayListTask task = new PlayListTask(this);
                    task.execute(Long.toString(aId),Long.toString(tId));
                    break;
            }


        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.release();
            mPlayer = null;
        }

        super.onDestroy();
    }
}
