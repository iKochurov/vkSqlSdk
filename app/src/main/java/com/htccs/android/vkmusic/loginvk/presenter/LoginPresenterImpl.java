package com.htccs.android.vkmusic.loginvk.presenter;

import android.app.Activity;
import android.content.Context;

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
    public void checkLogin() {
        VKSdk.login((Activity) context);
    }
}
