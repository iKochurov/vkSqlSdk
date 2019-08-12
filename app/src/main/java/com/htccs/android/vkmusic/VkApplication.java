package com.htccs.android.vkmusic;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class VkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
