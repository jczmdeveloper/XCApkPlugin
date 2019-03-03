package com.czm.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Created by caizhiming on 2018/3/3.
 * 代理Activity:管理插件Activity的生命周期
 */

public class XCProxyActivity extends Activity{

    private String mClassName;
    private XCPluginApk mPluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = XCPluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if(mPluginApk == null){
            throw new RuntimeException("请先加载插件Apk");
        }
        try {
            //clazz 就是Activity的实例对象，但是该对象没有生命周期，没有上下文环境
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if(object instanceof IPlugin) {
                mIPlugin = (IPlugin) object;
                mIPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        if(mPluginApk != null) {
            return mPluginApk.mResources;
        } else {
            return super.getResources();
        }
    }

    @Override
    public AssetManager getAssets() {
        if(mPluginApk != null) {
            return mPluginApk.mAssetManager;
        }else {
            return super.getAssets();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if(mPluginApk != null) {
            return mPluginApk.mDexClassLoader;
        }else {
            return super.getClassLoader();
        }
    }
}
