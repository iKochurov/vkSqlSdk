package com.htccs.android.vkmusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.model.VkAudioArray;

import java.util.Collections;


public class MainActivity extends android.app.Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешно!", Toast.LENGTH_SHORT);
                toast.show();
                VKRequest request = VKApi.users().get(VKParameters.from(VKApi.audio()));
            }
            @Override
            public void onError(VKError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Косяк!", Toast.LENGTH_SHORT);
                toast.show();             }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


