package com.zonghong.dict;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.waw.hr.mutils.bean.WordTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MAPP extends Application {

    public static MAPP mapp;

    private Activity currentActivity;

    private List<WordTypeBean> levelList = new ArrayList<>();

    private int currentLevelIndex = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mapp = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                setCurrentActivity(activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    public List<WordTypeBean> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<WordTypeBean> levelList) {
        this.levelList = levelList;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void setCurrentLevelIndex(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

}
