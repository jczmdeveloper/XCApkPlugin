package com.czm.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by caizhiming on 2018/3/3.
 */

public class XCPluginActivity extends Activity implements IPlugin {

    private int mFrom = FROM_INTERNAL;
    private Activity mActivity;

    @Override
    public void attach(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void setContentView(View view) {
        if(mFrom == FROM_INTERNAL) {
            super.setContentView(view);
        }else {
            mActivity.setContentView(view);
        }

    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        }else {
            mActivity.setContentView(layoutResID);
        }
    }
    @Override
    public void onCreate(Bundle bundle) {
        if(bundle != null) {
            mFrom = bundle.getInt("FROM");
        }
        if(mFrom == FROM_INTERNAL){
            super.onCreate(bundle);
            mActivity = this;
        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INTERNAL) {
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mFrom == FROM_INTERNAL) {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INTERNAL) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INTERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INTERNAL) {
            super.onDestroy();
        }
    }
}
