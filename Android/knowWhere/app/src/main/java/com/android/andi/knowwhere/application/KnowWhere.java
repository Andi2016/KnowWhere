package com.android.andi.knowwhere.application;

import android.app.Application;
import android.content.Context;

import com.android.andi.knowwhere.utils.AppPreference;

/**
 * Created by Andi Xu on 4/4/18.
 */

public class KnowWhere extends Application{

    private Context mContext;
    private AppPreference mPreference;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = this;
    }

    public Context getApplicationContext(){
        return mContext;
    }

    public AppPreference getPreference(){
        if(mPreference == null){
            mPreference = new AppPreference();
        }

        return mPreference;
    }
}
