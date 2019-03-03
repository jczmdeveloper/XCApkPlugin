package com.czm.xcapkplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.czm.pluginlib.XCPluginManager;
import com.czm.pluginlib.XCProxyActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XCPluginManager.getInstance().init(this);
        findViewById(R.id.btn_loadapk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apkPath = ApkHelpUtil.mockDownloadApk(view.getContext(),"plugin.apk");
                //加载apk，初始化XCPluginAPk
                XCPluginManager.getInstance().loadApk(apkPath);
            }
        });
        findViewById(R.id.btn_launch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, XCProxyActivity.class);
                intent.putExtra("className","com.czm.pluginapk.PluginActivity");
                startActivity(intent);
            }
        });
    }
}
