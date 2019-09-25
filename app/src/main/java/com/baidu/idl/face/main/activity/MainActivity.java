package com.baidu.idl.face.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.idl.face.main.db.DBManager;
import com.baidu.idl.face.main.activity.setting.SettingMainActivity;
import com.baidu.idl.face.main.utils.ConfigUtils;
import com.baidu.idl.face.main.listener.SdkInitListener;
import com.baidu.idl.face.main.manager.FaceSDKManager;
import com.baidu.idl.face.main.utils.ToastUtils;
import com.baidu.idl.face.main.utils.Utils;
import com.baidu.idl.facesdkdemo.R;

/**
 * 主功能页面，包含人脸检索入口，认证比对，功能设置，授权激活
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;

    private Button btnSearch;
    private Button btnIdentity;
    private Button btnAttribute;
    private Button btnSetting;
    private Button btnAuth;
    private Boolean isInitConfig;
    private Boolean isConfigExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo shangrong 增加配置信息初始化操作

        isConfigExit = ConfigUtils.isConfigExit();
        isInitConfig = ConfigUtils.initConfig();
        if (isInitConfig && isConfigExit) {
            Toast.makeText(MainActivity.this, "初始配置加载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "初始配置失败,将重置文件内容为默认配置", Toast.LENGTH_SHORT).show();
            ConfigUtils.modityJson();
        }

        mContext = this;
        initView();
        initLicense();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭数据库
        DBManager.getInstance().release();
    }

    /**
     * UI 相关VIEW 初始化
     */
    private void initView() {
        btnSearch = findViewById(R.id.btn_search);
        btnIdentity = findViewById(R.id.btn_identity);
        btnAttribute = findViewById(R.id.btn_attribute);
        btnSetting = findViewById(R.id.btn_setting);
        btnAuth = findViewById(R.id.btn_auth);
        TextView versionTv = findViewById(R.id.tv_version);
        versionTv.setText(String.format(" V %s", Utils.getVersionName(mContext)));
        btnSearch.setOnClickListener(this);
        btnIdentity.setOnClickListener(this);
        btnAttribute.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnAuth.setOnClickListener(this);
    }

    /**
     * 启动应用程序，如果之前初始过，自动初始化鉴权和模型（可以添加到Application 中）
     */
    private void initLicense() {
        if (FaceSDKManager.initStatus != FaceSDKManager.SDK_MODEL_LOAD_SUCCESS) {
            FaceSDKManager.getInstance().init(mContext, new SdkInitListener() {
                @Override
                public void initStart() {

                }

                @Override
                public void initLicenseSuccess() {

                }

                @Override
                public void initLicenseFail(int errorCode, String msg) {
                    // 如果授权失败，跳转授权页面
                    ToastUtils.toast(mContext, errorCode + msg);
                    startActivity(new Intent(mContext, FaceAuthActicity.class));
                }

                @Override
                public void initModelSuccess() {
                }

                @Override
                public void initModelFail(int errorCode, String msg) {

                }
            });
        }
    }

    /**
     * 点击事件跳转路径
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
            case R.id.btn_identity:
                // 【1：N 人脸搜索】 和 【1：1 人证比对】跳转判断授权和模型初始化状态
                if (FaceSDKManager.getInstance().initStatus == FaceSDKManager.SDK_UNACTIVATION) {
                    Toast.makeText(MainActivity.this, "SDK还未激活初始化，请先激活初始化", Toast.LENGTH_LONG).show();
                    return;
                } else if (FaceSDKManager.getInstance().initStatus == FaceSDKManager.SDK_INIT_FAIL) {
                    Toast.makeText(MainActivity.this, "SDK初始化失败，请重新激活初始化", Toast.LENGTH_LONG).show();
                    return;
                } else if (FaceSDKManager.getInstance().initStatus == FaceSDKManager.SDK_INIT_SUCCESS) {
                    Toast.makeText(MainActivity.this, "SDK正在加载模型，请稍后再试", Toast.LENGTH_LONG).show();
                    return;
                } else if (FaceSDKManager.getInstance().initStatus == FaceSDKManager.SDK_MODEL_LOAD_SUCCESS) {

                    switch (view.getId()) {
                        // 返回
                        case R.id.btn_search:
                            // 【1：N 人脸搜索】页面跳转
                            startActivity(new Intent(MainActivity.this, FaceMainSearchActivity.class));
                            break;
                        case R.id.btn_identity:
                            // 【1：1 人证比对】页面跳转
                            startActivity(new Intent(MainActivity.this, FaceIdCompareActivity.class));
                            break;
                        default:
                            break;

                    }
                }

                break;
            case R.id.btn_attribute:
                startActivity(new Intent(MainActivity.this, FaceAttributeRGBActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(MainActivity.this, SettingMainActivity.class));
                break;
            case R.id.btn_auth:
                // 跳转授权激活页面
                startActivity(new Intent(MainActivity.this, FaceAuthActicity.class));
                break;
        }
    }
}
