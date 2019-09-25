package com.baidu.idl.face.main.activity;

import android.os.Bundle;

import org.openni.OpenNI;

public class BaseOrbbecActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeOpenNI();
    }

    /**
     *
     */
    public void initializeOpenNI() {
        // 设置SDK Log 日志是否输出
        OpenNI.setLogAndroidOutput(true);
        // 设置Log日志输出级别
        OpenNI.setLogMinSeverity(0);
        // 初始化SDK
        OpenNI.initialize();
    }
}
