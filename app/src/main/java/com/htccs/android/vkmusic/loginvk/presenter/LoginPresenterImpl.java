package com.htccs.android.vkmusic.loginvk.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.htccs.android.vkmusic.loginvk.view.LoginView;
import com.vk.sdk.VKSdk;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;

    private Context context;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void showLogin() {
        loginView.checkLogin();
    }

    @Override
    public void setOnClickButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VKSdk.login((Activity) context);

            }
        });
    }

}
