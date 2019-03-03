package com.czm.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by caizhiming on 2018/3/3.
 */

public interface IPlugin {

    int FROM_INTERNAL = 0;//内部跳转
    int FROM_EXTERNAL = 1;//外部跳转

    void attach(Activity activity);

    void onCreate(Bundle bundle);
    void onStart();
    void onRestart();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
