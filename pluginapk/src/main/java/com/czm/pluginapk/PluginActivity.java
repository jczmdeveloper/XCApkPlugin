package com.czm.pluginapk;

import android.os.Bundle;

import com.czm.pluginlib.XCPluginActivity;

/**
 * Created by caizhiming on 2018/3/3.
 */

public class PluginActivity extends XCPluginActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
    }
}
