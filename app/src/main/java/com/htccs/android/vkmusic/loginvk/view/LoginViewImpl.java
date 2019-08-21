package com.htccs.android.vkmusic.loginvk.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.loginvk.presenter.LoginPresenter;

public class LoginViewImpl implements LoginView {

    private LoginPresenter loginPresenter;
    private Button button;

    public LoginViewImpl(View view) {

        button = view.findViewById(R.id.button_login);
    }

    public void checkLogin() {

        loginPresenter.setOnClickButton(button);
    }

    public void showResult(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Вход!")
                .setMessage("Ошибка!")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.loginPresenter = presenter;
    }
}
