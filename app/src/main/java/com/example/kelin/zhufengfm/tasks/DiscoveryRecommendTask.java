package com.example.kelin.zhufengfm.tasks;

import com.example.kelin.zhufengfm.client.ClientApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kelin on 2016/4/6.
 */

/**
 * 获取发现模块中推荐栏目的数据
 * 调用接口11
 */
public class DiscoveryRecommendTask extends BaseTask {


    public DiscoveryRecommendTask(TaskCallBack callBack) {
        super(callBack);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();




        String str = ClientApi.getDiscoveryRecommends(true, true);
        if (str != null) {

            //TODO:处理获得的数据

            try {
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                ret.state = 9;// 代表json解析失败
            }

        } else {
            ret.state = 8; // 代表网络请求为空
        }
        return ret;
    }


}
