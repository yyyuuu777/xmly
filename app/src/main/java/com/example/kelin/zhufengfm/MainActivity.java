package com.example.kelin.zhufengfm;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kelin.zhufengfm.fragment.CustomFragment;
import com.example.kelin.zhufengfm.fragment.DiscoveryFragment;
import com.example.kelin.zhufengfm.fragment.DownLoadTingFragment;
import com.example.kelin.zhufengfm.fragment.PersonalFragment;
import com.example.kelin.zhufengfm.media.PlayService;
import com.example.kelin.zhufengfm.utils.HttpUtils;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private DiscoveryFragment  mDiscoveryFragment;
    private CustomFragment mCustomFragment;
    private DownLoadTingFragment mDownLoadTingFragment;
    private PersonalFragment  mPersonalFragment;

    private Fragment[] mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------
        /**
         * 启动音乐播放服务
         */
        Intent intent = new Intent(this, PlayService.class);
        startService(intent);


        //------------------------------------------

        // TODO: fragment是否进行replace操作

        //采用fragment显示和隐藏的方式管理第一级的fragment
        // 默认添加到容器中，之后，切换RadioButton，进行显示和隐藏

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        mDiscoveryFragment = new DiscoveryFragment();
        mCustomFragment = new CustomFragment();
        mDownLoadTingFragment = new DownLoadTingFragment();
        mPersonalFragment = new PersonalFragment();

        mFragments = new Fragment[4];
        mFragments[0] = mDiscoveryFragment;
        mFragments[1] = mCustomFragment;
        mFragments[2] = mDownLoadTingFragment;
        mFragments[3] = mPersonalFragment;

        for (Fragment fragment : mFragments) {
            transaction.add(R.id.main_fragment_container,fragment);
            transaction.hide(fragment);
        }
        transaction.show(mFragments[0]);

        transaction.commit();

        //----------------------------------------------

        /**
         * 设置导航按钮的图标
         */
        RadioGroup tabBar = (RadioGroup) findViewById(R.id.main_tab_bar);

        if (tabBar != null) {
            tabBar.setOnCheckedChangeListener(this);
        }

        int[] rids = new int[]{
                R.id.main_tab_item_discovery,
                R.id.main_tab_item_custom,
                R.id.main_tab_item_download,
                R.id.main_tab_item_personal
        };
        for (int i = 0; i < rids.length; i++) {
            RadioButton button = (RadioButton) findViewById(rids[i]);
            Resources res = getResources();

            Drawable drawable = res.getDrawable(R.mipmap.ic_launcher);
            drawable.setBounds(0,0,60,60);

            button.setCompoundDrawables(null,drawable,null,null);
        }

    }

    private AlertDialog mExitDialog;
    /**
     * 回退键
     */
    @Override
    public void onBackPressed() {

        if (mExitDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            mExitDialog = builder
                    .setTitle("温馨提示")
                    .setMessage("确认退出珠峰听书吗？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(MainActivity.this, PlayService.class);
                            stopService(intent);
                            finish();
                        }
                    })
                    .setNeutralButton("最小化", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .create();
        }

        mExitDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitDialog = null;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        // 第一步，隐藏所有的fragment
        // 第二部，显示切换的fragment
        for (Fragment fragment : mFragments) {
            transaction.hide(fragment);
        }
        Fragment fragment = null;
        switch (checkedId){
            case R.id.main_tab_item_discovery:
                fragment = mFragments[0];
                break;
            case R.id.main_tab_item_custom:
                fragment = mFragments[1];
                break;
            case R.id.main_tab_item_download:
                fragment = mFragments[2];
                break;
            case R.id.main_tab_item_personal:
                fragment = mFragments[3];
                break;
        }
        if (fragment != null) {
            transaction.show(fragment);
        }

        transaction.commit();
    }
}
