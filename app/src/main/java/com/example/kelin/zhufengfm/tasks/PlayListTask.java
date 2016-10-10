package com.example.kelin.zhufengfm.tasks;

import com.example.kelin.zhufengfm.client.ClientApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kelin on 2016/4/8.
 */
public class PlayListTask extends BaseTask {


    public PlayListTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

        if (params!=null&&params.length>=2) {

            long albumId = -1;
            long trackId = -1;
            try {
                albumId = Long.parseLong(params[0]);
                trackId = Long.parseLong(params[1]);

                String str = ClientApi.getPlayList(albumId, trackId);

                if (str != null) {
                    try {
                        ret.data = new JSONObject(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        ret.state = 9;
                    }
                }else {
                    ret.data = 8;
                }


            }catch (NumberFormatException e){
                ret.state = 7;
            }

        }else {

        }


        return ret;
    }



}
