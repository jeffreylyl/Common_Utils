package com.app.utils.file;

import android.content.Context;

/**
 * 常量类
 */
public class Constant {


    public final static String getFileProviderName(Context context){
        return context.getPackageName()+".fileprovider";
    }
 }