package com.htccs.android.vkmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (VKSdk.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, PostsActivity.class);
            startActivity(intent);
        } else {
            VKSdk.login(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешно!", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(MainActivity.this, PostsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(VKError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Косяк!", Toast.LENGTH_SHORT);
                toast.show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}


