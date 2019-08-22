package com.htccs.android.vkmusic.loginvk.view;

import android.content.Context;

import com.htccs.android.vkmusic.loginvk.presenter.LoginPresenter;

public interface LoginView {

    void setPresenter(LoginPresenter presenter);

    void showResult(Context context);
}
