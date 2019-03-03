package com.czm.xcapkplugin;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by caizhiming on 2018/3/3.
 */

public class ApkHelpUtil {
    /**
     * 模拟下载Apk过程：讲Assets目录下的apk copy 到 app缓存目录下
     * @param context
     * @param fileName
     * @return
     */
    public static String mockDownloadApk(Context context, String fileName) {
        try{
            File cachedDir = context.getCacheDir();
            if(!cachedDir.exists()) {
                cachedDir.mkdirs();
            }
            File outFile = new File(cachedDir, fileName) ;
            if( !outFile.exists()){
                boolean success = outFile.createNewFile();
                if(success) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount = 0;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context,"下载Apk成功",Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } else {
                //这里省略了 apk的MD5比对校验步骤。
                Toast.makeText(context,"Apk文件已经存在",Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
