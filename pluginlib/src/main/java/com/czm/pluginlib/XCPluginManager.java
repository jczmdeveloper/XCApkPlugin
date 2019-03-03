package com.czm.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by caizhiming on 2018/3/3.
 * Apk插件管理类
 */

public class XCPluginManager {
    private static volatile XCPluginManager instance = new XCPluginManager();

    private Context mContext;
    private XCPluginApk mPluginApk;

    public static XCPluginManager getInstance() {
        return instance;
    }

    private XCPluginManager() {
    }
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public XCPluginApk getPluginApk(){
        return mPluginApk;
    }

    /**
     * 加载apk
     * @param apkPath
     */
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES
                |PackageManager.GET_PROVIDERS );

        if(packageInfo == null) return ;
        //创建DexClassLoader
        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        //创建
        AssetManager am = createAssetManager(apkPath);
        //创建Resources
        Resources resources = createResources(am);
        mPluginApk = new XCPluginApk(packageInfo,resources,dexClassLoader);
    }

    /**
     * 获取到插件中的Resource
     */
    private Resources createResources(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    /**
     * 获取插件的AssetManager
     */
    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**'
     * 创建DexClassLoader
     */
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

}
