package com.app.utils.file;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FileUtils {
    private static final String TAG="FileUtils";
    private static String DEFAULT_DISK_CACHE_DIR = "common_util_cache";
    public static File getFileCacheDir(Context context, File file) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File mCacheDir = new File(cacheDir,DEFAULT_DISK_CACHE_DIR);
            if (!mCacheDir.mkdirs() && (!mCacheDir.exists() || !mCacheDir.isDirectory())) {
                return file;
            }else {
                return new File(mCacheDir, file.getName());
            }
        }
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return file;
    }

    public static void delete(String path) {
        try {
            if(path == null) {
                return ;
            }
            File file = new File(path);
            if(!file.delete()) {
                file.deleteOnExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
