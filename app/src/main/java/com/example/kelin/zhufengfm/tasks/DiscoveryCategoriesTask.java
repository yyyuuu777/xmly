package com.example.kelin.zhufengfm.tasks;

import android.os.AsyncTask;

import com.example.kelin.zhufengfm.client.ClientApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kelin on 2016/4/6.
 */
public class DiscoveryCategoriesTask extends BaseTask {


    public DiscoveryCategoriesTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {

        TaskResult result = new TaskResult();

        String str = ClientApi.getDiscoveryCategories();
        if (str != null) {
            try {
                result.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                result.state = 9;
            }

        }else {
            result.state = 8;
        }

        return result;
    }


}
