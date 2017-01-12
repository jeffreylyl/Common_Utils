package com.app.utils;

import android.content.Context;

public class StringUtil
{
    private static StringUtil instance;
    private Context mContext;

    private StringUtil(Context paramContext)
    {
        this.mContext = paramContext;
    }

    public static StringUtil getInstance(Context paramContext)
    {
        if (instance == null)
            instance = new StringUtil(paramContext);
        return instance;
    }

    public String getString(int paramInt)
    {
        return this.mContext.getResources().getString(paramInt);
    }
}