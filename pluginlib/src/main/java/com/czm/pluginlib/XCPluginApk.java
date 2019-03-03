package com.czm.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created by caizhiming on 2018/3/3.
 * APk 解析加载信息类
 */

public class XCPluginApk {

    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;

    public XCPluginApk(PackageInfo packageInfo, Resources resources,DexClassLoader dexClassLoader) {
        mPackageInfo = packageInfo;
        mResources = resources;
        mAssetManager = resources.getAssets();
        mDexClassLoader = dexClassLoader;
    }
}
