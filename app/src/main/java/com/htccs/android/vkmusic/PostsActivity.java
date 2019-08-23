package com.htccs.android.vkmusic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.htccs.android.vkmusic.listgroup.GroupsListFragment;
import com.htccs.android.vkmusic.loginvk.LoginFragment;
import com.htccs.android.vkmusic.picturewall.PictureFragment;
import com.htccs.android.vkmusic.wallgroup.WallGroupFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class PostsActivity extends AppCompatActivity implements FragmentInteraction, FragmentInteractionPicture {

    private final static String GROUPS = "Сообщества";
    private final static String LOGIN = "Вход";
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (VKSdk.isLoggedIn()) {
            displayGroupListFragment();
        } else {
            VKSdk.login(this);
        }
    }

    private void displayLoginFragment() {
        setTitle(LOGIN);
        Fragment fragment = LoginFragment.newInstance();

        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, LoginFragment.TAG)
                .commit();
    }

    public void displayGroupListFragment() {
        setTitle(GROUPS);
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragment = GroupsListFragment.newInstance();
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, GroupsListFragment.TAG)
                .commit();
    }


    @Override
    public void onGroupItemCliked(String groupId, String title) {
        setTitle(title);
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragment = WallGroupFragment.newInstance(groupId);
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, WallGroupFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            setTitle(GROUPS);
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onClickPicture(String urlPicture) {
        Fragment fragment = fragmentManager.findFragmentByTag(PictureFragment.TAG);
        if (fragment == null) {
            fragment = PictureFragment.newInstance(urlPicture);
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, PictureFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {

            public void onResult(VKAccessToken res) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешно!", Toast.LENGTH_SHORT);
                toast.show();
                displayGroupListFragment();
            }

            public void onError(VKError error) {
                displayLoginFragment();
            }
        });
    }
}

