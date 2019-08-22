package com.htccs.android.vkmusic.loginvk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.loginvk.presenter.LoginPresenter;
import com.htccs.android.vkmusic.loginvk.presenter.LoginPresenterImpl;
import com.htccs.android.vkmusic.loginvk.view.LoginView;
import com.htccs.android.vkmusic.loginvk.view.LoginViewImpl;

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getSimpleName();

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vkLoginView = inflater.inflate(R.layout.fragment_vk_login, container, false);
        LoginView loginView = new LoginViewImpl(vkLoginView);
        loginView.showResult(getContext());
        LoginPresenter loginPresenter = new LoginPresenterImpl(loginView, vkLoginView.getContext());
        loginView.setPresenter(loginPresenter);

        return vkLoginView;
    }
}